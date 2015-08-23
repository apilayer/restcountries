from bs4 import BeautifulSoup
import requests

ENDPOINT = 'https://en.wikipedia.org/wiki/List_of_circulating_currencies'


def update():
    page = requests.get(ENDPOINT)
    page.encoding = 'utf-8'
    soup = BeautifulSoup(page.text)
    currencyData = __extractCurrencyData(soup)


def __extractCurrencyData(soup):
    populationData = {}

update()
