from datetime import datetime
from contextlib import contextmanager

from flask import session
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
        self.id = self.user_info.get("id", "")
        self.password = self.user_info.get("password")

    def save(self):
        assert self.password is not None
        encrypt_pwd = self.encrypt(self.password)
        now = datetime.now()
        sql = "INSERT INTO t_user (user_name,password,mobile,email," \
              "created_time,login_time,last_login_time,login_count)  " \
              "VALUES ('%s','%s','%s','%s','%s','%s','%s','%s')"
        sql = sql % (self.user_name, encrypt_pwd, self.mobile, self.email,
                       now, now, now, '0')
        with open_conn() as conn:
            conn.execute(text(sql))
            res = conn.execute(text(
                "select id from t_user WHERE user_name='%s'"% self.user_name
            )).fetchone()
        return res["id"]


    def is_validate(self):
        sql = "select id,password,mobile,email from t_user " \
              "WHERE user_name='%s'" % self.user_name
        try:
            with open_conn() as conn:
                res = conn.execute(text(sql)).fetchone()
        except DataMappingError:
            LOG.exception("Validate user: %s failed." % self.user_name)
            return False
        decrypt_pwd = self.decrypt(res["password"])
        if decrypt_pwd == self.password:
            self.mobile = res["mobile"]
            self.email = res["email"]
            self.id = res["id"]
            return True
        return False

    def set_user2session(self):
        session["user_name"] = self.user_name
        session["mobile"] = self.mobile
        session["email"] = self.email
        session["id"] = self.id



