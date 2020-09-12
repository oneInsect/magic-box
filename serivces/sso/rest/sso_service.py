"""
entry of sso service, provided user authentication and token generate.
"""

from flask import make_response
from .app_config import APP


@APP.route("/test", methods=["get"])
def rest_test():
    return make_response("ok", 200)



if __name__ == '__main__':
    APP.run()



