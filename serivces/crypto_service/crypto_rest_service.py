

from flask import Flask, jsonify, request

from crypto_service.crypto import AES128Decrypt, AES128Encrypt


APP = Flask(__name__)
decryptor = AES128Decrypt()
encryptor = AES128Encrypt()



@APP.route("/magicbox/v1/crypto/decrypt", methods=["POST"])
def decrypt():
    data = request.form
    encrypt_str = data.get("encrypt_str")
    decrypt_str = decryptor.decrypt(encrypt_str)
    return jsonify({"decrypt_str": decrypt_str})


@APP.route("/magicbox/v1/crypto/encrypt", methods=["POST"])
def encrypt():
    data = request.form
    decrypt_str = data.get("decrypt_str")
    encrypt_str = encryptor.encrypt(decrypt_str)
    return jsonify({"encrypt_str": encrypt_str})


if __name__ == '__main__':
    APP.run(port=9001)


