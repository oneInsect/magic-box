"""
entry of sso service, provided user authentication and token generate.
"""

from flask import make_response, session
from user_mgt.rest.app_config import APP


@APP.route("/index", methods=["get"])
def index():
    session['username'] = 'zhangvalue'
    return make_response("ok", 200)

@APP.route("/index1", methods=["get"])
def index1():
    value = session['username']
    return make_response(value, 200)


if __name__ == '__main__':
    APP.run()



