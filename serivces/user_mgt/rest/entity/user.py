
from flask_login import UserMixin
from crypto_service.crypto import AES128Encrypt, AES128Decrypt


class User(UserMixin):
    def __init__(self):
        pass



