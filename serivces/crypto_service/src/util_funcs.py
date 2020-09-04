
import os

from py_common import APP_ROOT
from py_common.utils import get_output

def get_root_ket():
    root_key_path = os.path.join(APP_ROOT, "crypto_service", "keys", "root.key")
    return get_output("cat %s" % root_key_path).strip()