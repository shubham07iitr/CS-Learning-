class Jar:
    def __init__(self, capacity=12, size = 0):
        ...
        if type(capacity) == int and capacity >= 1:
            self._capacity = capacity
        else:
            raise ValueError ("Capacity can't be negative")
        if type(size) != int or size >= self._capacity:
            raise ValueError ("You can't hold more cookies than capacity")
        else:
            self._size = size

    def __str__(self):
        ...
        return "🍪"*self._size

    def deposit(self, n):
        if n + self._size <= self._capacity:
            self._size += n 
            print(f"You have added {n} cookies to the jar, total cookies are now {self._size}")
        else:
            raise ValueError ("YOu want to add more cookies than capacity")

    def withdraw(self, n):
        ...
        if self._size - n >= 0:
            self._size -=  n 
            print(f"You have taken {n} cookies to the jar, total cookies are now {self._size}")
        else:
            raise ValueError ("You want to eat more cookies than in the jar")

    @property
    def capacity(self):
        ...
        return self._capacity

    @property
    def size(self):
        ...
        return self._size 
    


def main():
    jar = Jar()
    jar.capacity
    jar.size
    jar.deposit(10)
    jar.withdraw(4)
    jar.capacity
    jar.size
    print(jar)

if __name__ == "__main__":
    main()
