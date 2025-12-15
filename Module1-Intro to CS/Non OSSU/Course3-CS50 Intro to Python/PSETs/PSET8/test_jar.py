from cookie import Jar
import pytest

def test_init_allcorrect():
     jar = Jar(capacity = 12, size = 5)
     assert jar.capacity == 12
     assert jar.size == 5


def test_init_capacity_incorrect():
     with pytest.raises(ValueError):
          jar = Jar(capacity = "cat", size = 5)
     

def test_init_size_incorrect():
     with pytest.raises(ValueError):
          jar = Jar(capacity = 10, size = "cat")

def test_print():
     jar = Jar(capacity=12, size=5)
     assert str(jar) == "🍪🍪🍪🍪🍪"

def test_deposit_correct():
     jar = Jar(12, 5)
     jar.deposit(5)
     assert jar.size == 10 

def test_deposit_incorrect():
     jar = Jar(12, 5)
     with pytest.raises(ValueError):
          jar.deposit(20)
     

def test_withdraw_correct():
     jar = Jar(12, 5)
     jar.withdraw(3)
     assert jar.size == 2

def test_withdraw_incorrect():
     jar = Jar(12, 5)     
     with pytest.raises(ValueError):
          jar.withdraw(10)