
import os
import logging
from logging.handlers import RotatingFileHandler
from py_common import APP_ROOT


LOG_MAPPING = {"debug": logging.DEBUG,
               "info": logging.INFO,
               "warning": logging.WARNING,
               "error": logging.ERROR}


class LoggerFactory(object):

    @staticmethod
    def get_logger(module_name, log_level='info'):
        return Logger(module_name, log_level)



class Logger(object):
    def __init__(self, module_name, log_level):
        self._log = logging.getLogger(module_name)
        self.log_level = LOG_MAPPING.get(log_level) or logging.INFO
        self.log_file = os.path.join(APP_ROOT, "log", module_name + ".log")
        self.init_logger()

    def init_logger(self):
        self._log.setLevel(level=self.log_level)
        rotate_handler = RotatingFileHandler(
            self.log_file, maxBytes=20 * 1024 * 1024, backupCount=3)
        rotate_handler.setLevel(logging.INFO)
        formatter = logging.Formatter(
            '[%(asctime)s][%(name)s][%(levelname)s] - %(message)s')
        rotate_handler.setFormatter(formatter)

        console = logging.StreamHandler()
        console.setLevel(logging.INFO)
        console.setFormatter(formatter)

        console = logging.StreamHandler()
        console.setLevel(self.log_level)
        console.setFormatter(formatter)

        self._log.addHandler(rotate_handler)
        self._log.addHandler(console)

    def debug(self, msg, *args, **kwargs):
        """
        Log 'msg % args' with severity 'DEBUG'.

        To pass exception information, use the keyword argument exc_info with
        a true value, e.g.

        logger.debug("Houston, we have a %s", "thorny problem", exc_info=1)
        """
        self._log.debug(msg, *args, **kwargs)

    def info(self, msg, *args, **kwargs):
        """
        Log 'msg % args' with severity 'INFO'.

        To pass exception information, use the keyword argument exc_info with
        a true value, e.g.

        logger.info("Houston, we have a %s", "interesting problem", exc_info=1)
        """
        self._log.info(msg, *args, **kwargs)

    def warning(self, msg, *args, **kwargs):
        """
        Log 'msg % args' with severity 'WARNING'.

        To pass exception information, use the keyword argument exc_info with
        a true value, e.g.

        logger.warning("Houston, we have a %s", "bit of a problem", exc_info=1)
        """
        self._log.warning(msg, *args, **kwargs)

    def error(self, msg, *args, **kwargs):
        """
        Log 'msg % args' with severity 'ERROR'.

        To pass exception information, use the keyword argument exc_info with
        a true value, e.g.

        logger.error("Houston, we have a %s", "major problem", exc_info=1)
        """
        self._log.error(msg, *args, **kwargs)

    def exception(self, msg, *args, exc_info=True, **kwargs):
        """
        Convenience method for logging an ERROR with exception information.
        """
        self.error(msg, *args, exc_info=exc_info, **kwargs)


if __name__ == '__main__':
    log = LoggerFactory.get_logger("test")
    log1 = LoggerFactory.get_logger("test1")
    log.info("Start print log")
    log.debug("Do something")
    log.warning("Something maybe fail.")
    log1.info("Finish")
    try:
        1/0
    except ArithmeticError:
        log1.exception("erro")