import requests
from bs4 import BeautifulSoup

@profile
def get_urls():
    response = requests.get("http://missing.csail.mit.edu")
    s = BeautifulSoup(response.content, 'lxml')
    urls = []
    for url in s.find_all('a'):
        urls.append(url['href'])

if __name__ =="__main__":
    get_urls() 
