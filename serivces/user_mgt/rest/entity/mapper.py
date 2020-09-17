from datetime import datetime
from contextlib import contextmanager
from sqlalchemy import create_engine
from sqlalchemy.sql import text
from sqlalchemy.exc import SQLAlchemyError
from user_mgt.rest.entity.user import User

from py_common.errors.user_mgt_errors import DataMappingError
from user_mgt import LOG

ENGINE = create_engine('mysql://root:admin@localhost/magic_user', echo=True)


@contextmanager
def open_conn():
    conn = None
    try:
        conn = ENGINE.connect()
        yield conn
    except SQLAlchemyError as sql_error:
        LOG.exception("Get connect of database error.")
        raise DataMappingError(str(sql_error))
    finally:
        if hasattr(conn, "close"):
            conn.close()


class UserMapper(User):
    def __init__(self, **user_info):
        super().__init__(**user_info)
        self.user_name = self.user_info.get("user_name")
        self.mobile = self.user_info.get("mobile", "")
        self.email = self.user_info.get("email", "")

    def save(self):
        unencrypt_pwd = self.user_info.get("password")

        encrypt_pwd = self.encrypt(unencrypt_pwd)
        now = datetime.now()
        sql = "INSERT INTO t_user (user_name,password,mobile,email," \
              "created_time,login_time,last_login_time,login_count)  " \
              "VALUES ('%s','%s','%s','%s','%s','%s','%s','%s')"
        sql = sql % (self.user_name, encrypt_pwd, self.mobile, self.email,
                       now, now, now, '0')
        with open_conn() as conn:
            conn.execute(text(sql))
            _id = conn.execute(text(
                "select id from t_user WHERE user_name='%s'"% self.user_name
            )).fetchone().values()[0]
        return _id


    def is_validate(self):
        pass
