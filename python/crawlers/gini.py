from bs4 import BeautifulSoup
import urllib2

URL_GINI = 'https://en.wikipedia.org/wiki/'\
    'List_of_countries_by_income_equality'


def update(countriesJSON):
    html = __getHTML(URL_GINI)
    soup = BeautifulSoup(html, "lxml")
    giniData = __extractGiniData(soup)

    return __updateJSON(countriesJSON, giniData)


def __extractGiniData(html):
    giniData = {}
    for i, row in enumerate(html.table):
        if i > 1:
            for j, column in enumerate(row):
                if j == 1:
                    href = column.find('a')
                    if href is not None:
                        country = href.get_text().encode('utf-8')
                if j == 7:
                    gini = column.get_text()
                    giniData[country] = gini

    return giniData


def __updateJSON(countriesJSON, giniData):
    for country in giniData:
        for countryJSON in countriesJSON:
            if country == countryJSON['name'].encode('utf-8'):
                if giniData[country]:
                    countryJSON['gini'] = float(giniData[country])

    return countriesJSON


def __getHTML(url):
    usock = urllib2.urlopen(url)
    data = usock.read()
    usock.close()
    return data
