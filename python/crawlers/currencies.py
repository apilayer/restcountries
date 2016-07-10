from bs4 import BeautifulSoup
import requests

ENDPOINT = 'https://en.wikipedia.org/wiki/List_of_circulating_currencies'


def update(countriesJSON):
    page = requests.get(ENDPOINT)
    page.encoding = 'utf-8'
    soup = BeautifulSoup(page.text, "lxml")
    countries = __extractCurrencyData(soup)
    return __updateJSON(countriesJSON, countries)


def __extractCurrencyData(html):
    countries = []
    table = html.find_all('table', class_='wikitable')[0].find_all('tr')
    for i, row in enumerate(table):
        if i > 0:
            columns = row.find_all('td')
            if len(columns) == 6:
                country = {}
                country["currencies"] = []
                country["country"] = __extractCountryName(columns[0])
                currencyName = columns[1].find('a').text.encode('utf-8')
                currencySymbol = __extractCurrencySymbol(columns[2])
                currencyCode = __extractCurrencyCode(columns[3])
                currency = {"name": currencyName, "symbol": currencySymbol, "code": currencyCode}
                country["currencies"].append(currency)
            elif len(columns) == 5:
                currencyName = __extractCurrencyName(columns[0])
                currencySymbol = __extractCurrencySymbol(columns[1])
                currencyCode = __extractCurrencyCode(columns[2])
                currency = {"name": currencyName, "symbol": currencySymbol, "code": currencyCode}
                country["currencies"].append(currency)
            countries.append(country)
    return countries


def __extractCountryName(html):
    links = html.find_all('a')
    if len(links) == 1:
        return links[0]['title'].encode('utf-8')
    elif len(links) == 2 and 'cite' not in links[1]['href']:
        return links[1].text.encode('utf-8')
    else:
        return links[0]['title'].encode('utf-8')


def __extractCurrencyName(html):
    link = html.find('a')
    spans = html.find_all('span')
    if link and 'cite' not in link['href']:
        return link.text.encode('utf-8')
    elif len(spans) > 0:
        if spans[1].text and '(none)' not in spans[1].text:
            return spans[1].text.encode('utf-8')
        else:
            return None
    else:
        return None


def __extractCurrencySymbol(html):
    contents = html.contents
    if len(contents) == 1:
        if html.find('a'):
            return None
        else:
            currencySymbol = html.text.encode('utf-8')
            if 'or' in currencySymbol:
                currencySymbol = currencySymbol.split('or')[0].strip()
            return currencySymbol
    else:
        return None


def __extractCurrencyCode(html):
    if html.find('span'):
        return None
    else:
        return html.text.encode('utf-8')


def __updateJSON(countriesJSON, countries):
    for country in countries:
        for countryJSON in countriesJSON:
            if country['country'] == countryJSON['name'].encode('utf-8'):
                countryJSON['currencies'] = country['currencies']
    return countriesJSON
