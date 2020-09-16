
from sqlalchemy import create_engine
from user_mgt.rest.entity.user import User

ENGINE = create_engine('sqlite:///:memory:', echo=True)

def conn_factory():
    return ENGINE.connect()



class UserMapper(User):
    pass

