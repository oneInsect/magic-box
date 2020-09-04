

from .util_funcs import get_root_ket

class CryptoBase(object):
    def __init__(self):
        self.__root_key = get_root_ket()

    def padding(self):
        pass