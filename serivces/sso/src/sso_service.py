"""
entry of sso service, provided user authentication and token generate.
"""


from flask import Flask, make_response


APP = Flask(__name__)


@APP.route("/test", methods=["get"])
def rest_test():
    return make_response("ok", 200)



if __name__ == '__main__':
    APP.run()



