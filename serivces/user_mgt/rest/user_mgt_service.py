"""
entry of sso service, provided user authentication and token generate.
"""

from flask import make_response, session, request
from flask_login import login_required
from user_mgt.rest.app_config import APP


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
    params = request.form
    user_name = params.get("user_name")
    password = params.get("password")
    email = params.get("email")


@APP.route("/magicbox/v1/usermgt/user", methods=("POST",))
def register():
    pass




if __name__ == '__main__':
    APP.run()



