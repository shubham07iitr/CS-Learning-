import pytest 
from my_numbers import validate 

def test_validate():
    assert validate("1.1.1.1") == True 
    assert validate("1000.1000.1000.1000") == False 
    assert validate("cat") == False 


