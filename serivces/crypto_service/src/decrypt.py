
import base64
from Crypto.Cipher import AES



class AES128Decrypt(object):
    def __init__(self):
        self._root_ket = get_root_ket()
        self.__key = base64.b64decode(self._root_ket)

    def padding(self):
        pass


    def decrypt(self, encrypt_str):
        unpad = lambda s: s[0:-s[-1]]
        cipher = AES.new(self.__key, AES.MODE_ECB)
        decrData = unpad(cipher.decrypt(encrypt_str))
        return decrData.decode('utf-8')
