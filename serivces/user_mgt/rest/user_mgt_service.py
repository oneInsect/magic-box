"""
entry of sso service, provided user authentication and token generate.
"""

from flask import make_response, session, request

from py_common.errors.user_mgt_errors import DataMappingError
from user_mgt.rest.app_config import APP
from user_mgt.rest.entity import UserMapper
from user_mgt import LOG


@APP.route("/magicbox/", methods=["get"])
def index():
    session['username'] = 'zhangvalue'
    return make_response("ok", 200)

@APP.route("/index1", methods=["get"])
def index1():
    value = session['username']
    return make_response(value, 200)


@APP.route("/magicbox/v1/usermgt/session", methods=("POST",))
def login():
    params = request.json
    user_mapper = UserMapper(**params)
    if user_mapper.is_validate():
        user_mapper.set_user2session()
        return make_response("success", 200)
    return make_response("auth failed", 403)


@APP.route("/magicbox/v1/usermgt/user", methods=("POST",))
def register():
    params = request.json
    user_mapper = UserMapper(**params)
    try:
        user_mapper.save()
    except DataMappingError:
        LOG.exception("register user failed.")
        return make_response("some thing wrong in the backend", 500)
    user_mapper.set_user2session()
    return make_response("success", 200)

if __name__ == '__main__':
    APP.run()



