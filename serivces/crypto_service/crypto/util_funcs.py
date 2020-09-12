
import os

from py_common import APP_ROOT
from py_common.utils import IniParser

def get_root_ket():
    root_key_path = os.path.join(APP_ROOT, "crypto_service",
                                 "crypto", "config", "key.ini")
    return IniParser(root_key_path).get_value("KEY", "root_key")


def get_iv():
    root_key_path = os.path.join(APP_ROOT, "crypto_service",
                                 "crypto", "config", "key.ini")
    return IniParser(root_key_path).get_value("KEY", "iv").encode('utf-8')