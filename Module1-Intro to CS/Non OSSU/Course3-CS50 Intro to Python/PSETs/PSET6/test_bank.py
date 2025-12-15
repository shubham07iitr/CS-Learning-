from bank import greeting
import pytest 

def test_greeting_hello():
    assert greeting("hello") == 0

def test_greeting_how():
    assert greeting("how") == 20 

def test_greeting_shubham():
    assert greeting("shubham") == 100
