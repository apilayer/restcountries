from bs4 import BeautifulSoup
import urllib2

URL_POPULATION = 'https://en.wikipedia.org/wiki/'\
                 'List_of_countries_by_population'


def update(countriesJSON):
    html = __getHTML(URL_POPULATION)
    soup = BeautifulSoup(html, "html.parser")
    populationData = __extractPopulationData(soup)
    return __updateJSON(countriesJSON, populationData)


def __extractPopulationData(html):
    populationData = {}
    for i, row in enumerate(html.table):
        if i > 1:
            for j, column in enumerate(row):
                if j == 3:
                    href = column.find('a')
                    country = href.get_text().encode('utf-8')
                if j == 5:
                    population = column.get_text().replace(',', '')
                    populationData[country] = population

    return populationData


def __updateJSON(countriesJSON, populationData):
    for country in populationData:
        for countryJSON in countriesJSON:
            if country == countryJSON['name'].encode('utf-8'):
                countryJSON['population'] = int(populationData[country])

    return countriesJSON


def __getHTML(url):
    usock = urllib2.urlopen(url)
    data = usock.read()
    usock.close()
    return data
