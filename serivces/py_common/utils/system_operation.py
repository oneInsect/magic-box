import shlex
import logging
from subprocess import check_output, call, CalledProcessError


LOG = logging.getLogger(__name__)


def get_output(cmd) -> str:
    _cmd = shlex.split(cmd)
    output = ""
    try:
        output = check_output(_cmd)
    except CalledProcessError:
        LOG.error("call %s error" % cmd)
    if isinstance(output, bytes):
        output = output.decode("utf-8")
    return output


def target_in_output(target: str, cmd) -> bool:
    return target in get_output(cmd)


def run_cmd(cmd) -> bool:
    pass