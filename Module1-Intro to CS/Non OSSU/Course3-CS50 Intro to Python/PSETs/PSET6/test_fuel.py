from fuel import convert, gauge
import pytest 

def test_fuel_correct():
    assert convert("3/4") == 75
    assert convert("1/2") == 50

def test_fuel_value():
    with pytest.raises(ValueError):
        convert("cat/shubham")

def test_fuel_zero():
    with pytest.raises(ZeroDivisionError):
        convert("3/0")

def test_gauge_F():
    assert gauge(99) == "F"

def test_gauge_E():
    assert gauge(1) == "E"

def test_gauge_mid():
    assert gauge(75) == "75%"