"""
entry of sso service, provided user authentication and token generate.
"""

from flask import make_response, session, request
from flask_login import login_required

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
        session["user_name"] = params.get("user_name")
        session["mobile"] = params.get("mobile")
        session["email"] = params.get("email")
        return


@APP.route("/magicbox/v1/usermgt/user", methods=("POST",))
def register():
    params = request.json
    user_mapper = UserMapper(**params)
    try:
        user_id = user_mapper.save()
        session["id"] = user_id
        session["user_name"] = params.get("user_name")
        session["mobile"] = params.get("mobile")
        session["email"] = params.get("email")
        return make_response("success", 200)
    except DataMappingError:
        return make_response("some thing wrong in the backend", 500)


if __name__ == '__main__':
    APP.run()



