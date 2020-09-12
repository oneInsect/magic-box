
from base64 import b64decode
from Crypto.Cipher import AES
from .util_funcs import get_root_ket, get_iv


class CryptoBase(object):
    def __init__(self):
        self.root_key = b64decode(get_root_ket())
        self.mode = AES.MODE_CBC
        self.iv = get_iv()

    @staticmethod
    def pad(decrypt_str):
        bs = 16
        return decrypt_str + (bs - len(decrypt_str) % bs) * chr(bs - len(decrypt_str) % bs)

    @staticmethod
    def unpad(decrypt_str):
        return decrypt_str[0:-decrypt_str[-1]]

