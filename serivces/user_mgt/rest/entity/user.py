
from flask_login import UserMixin
from crypto_service.crypto import AES128Encrypt, AES128Decrypt


class User(UserMixin):
    def __init__(self, **user_info):
        self.user_info = user_info

    def get_id(self):
        return self.user_info.get("id")

    def get_user_info(self):
        return self.user_info

    @staticmethod
    def encrypt(pwd):
        return AES128Encrypt().encrypt(pwd)

    @staticmethod
    def decrypt(pwd):
        return AES128Decrypt().decrypt(pwd)

