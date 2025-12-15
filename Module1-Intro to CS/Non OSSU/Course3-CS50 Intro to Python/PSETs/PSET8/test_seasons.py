from seasons import minutes_elapsed, convert_date_english
import pytest 

def test_minutes_elapsed():
    assert minutes_elapsed("1989-09-25") == 18760320

def test_convert_date_english(): 
    assert convert_date_english(18760320) == "eighteen million, seven hundred and sixty thousand, three hundred and twenty minutes"

