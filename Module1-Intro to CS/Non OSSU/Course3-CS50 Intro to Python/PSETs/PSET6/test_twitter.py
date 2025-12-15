from twitter import output
import pytest

def test_output():
    assert output("twitter") == "twttr"
    assert output("Shubham Gupta") == "Shbhm Gpt"