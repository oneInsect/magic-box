

from configparser import ConfigParser


class IniParser(object):
    """Parser of ini config file,"""
    def __init__(self, ini_path):
        self.parser_instance = ConfigParser()
        self.parser_instance.read(ini_path)

    def get_value(self, section, option):
        return self.parser_instance.get(section, option)


    def set_value(self, section, option):
        pass


    def save(self):
        pass