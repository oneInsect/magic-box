

from abc import ABCMeta, abstractmethod


class Subject(object):
    def __init__(self):
        self.observers = []
        self._state = ""

    @property
    def state(self):
        return self._state

    @state.setter
    def state(self, value):
        self._state = value
        self.notify_all()

    def add_observers(self, observer):
        self.observers.append(observer)


    def notify_all(self):
        for observer in self.observers:
            observer.update(self.state)



class Observer(metaclass=ABCMeta):

    def __init__(self, subject):
        subject.add_observers(self)


    @abstractmethod
    def update(self, state):
        """all observers must have this function to become a observer"""



class RainObserver(Observer):
    def __init__(self, subject):
        super().__init__(subject)

    def update(self, state):
        print(self.__class__.__name__, "had updated! now state: ", state)


class SunObserver(Observer):
    def __init__(self, subject):
        super().__init__(subject)

    def update(self, state):
        print(self.__class__.__name__, "had updated! now state: ", state)


if __name__ == '__main__':
    tmp_subject = Subject()

    RainObserver(tmp_subject)
    SunObserver(tmp_subject)

    tmp_subject.state = "Rain"

