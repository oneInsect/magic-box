from Crypto.Cipher import AES
import base64


class AES128Encrypt(object):
    def __init__(self):
        self.key = 'XXXXXXXXXXX'  #秘钥
        self.MODE = AES.MODE_ECB
        self.BS = AES.block_size
        self.pad = lambda s: s + (self.BS - len(s) % self.BS) * chr(self.BS - len(s) % self.BS)
        self.unpad = lambda s: s[0:-ord(s[-1])]

    def add_to_16(value):
        while len(value) % 16 != 0:
            value += '\0'
        return str.encode(value)  # 返回bytes

    def AES_encrypt(self, text):
        aes = AES.new(AES128Encrypt.add_to_16(self.key), self.MODE)  # 初始化加密器
        encrypted_text = str(base64.encodebytes(aes.encrypt(AES128Encrypt.add_to_16(self.pad(text)))),
                             encoding='utf-8').replace('\n', '')  # 这个replace大家可以先不用，然后在调试出来的结果中看是否有'\n'换行符
        # 执行加密并转码返回bytes
        return encrypted_text