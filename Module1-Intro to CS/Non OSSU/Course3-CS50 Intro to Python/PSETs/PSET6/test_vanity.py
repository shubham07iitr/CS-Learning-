from vanity import is_valid
import pytest 

def test_isvalid_True():
    assert is_valid("CS50") == True 
    # assert is_valid("AAA222") == True 

def test_isvalid_False():
    assert is_valid("Hello, World") == False 
    assert is_valid("S") == False