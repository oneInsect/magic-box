

class Node(object):
    def __init__(self, data):
        self.data = data
        self._next = None

    def set_next(self, next_node):
        self._next = next_node

    def get_next(self):
        return self._next


class LinkList(object):
    def __init__(self):
        self._head = None
        self._last= None
        self._size = 0

    def insert(self, index, data):
        tmp_node = Node(data)
        if self._size == 0:
            self._head = tmp_node
            self._last = tmp_node
        elif index == 0:
            tmp_node.set_next(self._head)
            self._head = tmp_node
        elif index == self._size:
            self._last.set_next(tmp_node)
            self._last = tmp_node
        else:
            pre_node = self.get_node(index - 1)
            tmp_node.set_next(pre_node.get_next())
            pre_node.set_next(tmp_node)
        self._size += 1

    def get_node(self, index) -> Node:
        if index < 0 or index >= self._size:
            raise IndexError()
        tmp_node = self._head
        for _ in range(index):
            tmp_node = tmp_node.get_next()
        return tmp_node

    def __str__(self):
        tmp_node = self._head
        data_list = []
        for index in range(self._size):
            data_list.append(str(tmp_node.data))
            tmp_node = tmp_node.get_next()
        return "LinkList: " + ",".join(data_list)


if __name__ == '__main__':
    link_list = LinkList()
    link_list.insert(0,1)
    link_list.insert(1,2)
    link_list.insert(1,3)
    print(link_list)

