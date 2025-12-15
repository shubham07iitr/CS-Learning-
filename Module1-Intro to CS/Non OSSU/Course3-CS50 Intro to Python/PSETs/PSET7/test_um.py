import pytest 
from um import count 


def test_count():
    assert count("Um, thanks for the album.") == 1
    assert count("Um") == 1
    assert count("shubham gumta") ==  0

