

from unittest import TestCase, main
from crypto_service.crypto import AES128Encrypt, AES128Decrypt


class TestCrypto(TestCase):

    def setUp(self):
        self.pwd = "change"

    def tearDown(self):
        self.pwd = None

    def testCrypto(self):
        encrypt_str = AES128Encrypt().encrypt(self.pwd)
        decrypt_str = AES128Decrypt().decrypt(encrypt_str)
        self.assertEqual(self.pwd, decrypt_str)

if __name__ == '__main__':
    main()