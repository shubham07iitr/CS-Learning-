from working import convert 
import pytest 

def test_working():
    assert convert("9 AM to 5 PM") == "09:00 to 17:00"
    assert convert("10:30 PM to 8 AM") == "22:30 to 08:00"

def test_working_error():
    with pytest.raises(ValueError):
        assert("9 AM - 5 PM")