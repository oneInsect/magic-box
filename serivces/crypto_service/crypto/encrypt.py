
from Crypto.Cipher import AES
from base64 import b16encode
from .crypto_base import CryptoBase


class AES128Encrypt(CryptoBase):
    def __init__(self):
        super().__init__()


    def encrypt(self, text):
        aes = AES.new(self.root_key, self.mode, self.iv)
        encrypted_text = b16encode(aes.encrypt(self.pad(text)))
        return encrypted_text.decode('utf8')