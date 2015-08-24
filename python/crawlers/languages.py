from bs4 import BeautifulSoup
import requests

ENDPOINT = 'https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes'


def update(countriesJSON):
    page = requests.get(ENDPOINT)
    page.encoding = 'utf-8'
    soup = BeautifulSoup(page.text)
    countries = __extractLanguagesData(soup)


def __extractLanguagesData(html):
    languages = []
    table = html.find('table', class_='wikitable')
    print(table)

update(None)
