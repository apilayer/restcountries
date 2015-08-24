from bs4 import BeautifulSoup
import requests

ENDPOINT = 'https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes'


def update(countriesJSON):
    page = requests.get(ENDPOINT)
    page.encoding = 'utf-8'
    soup = BeautifulSoup(page.text, "html.parser")
    languages = __extractLanguagesData(soup)
    return __updateJSON(countriesJSON, languages)


def __extractLanguagesData(html):
    languages = []
    table = html.find('table', class_='wikitable sortable').find_all('tr')
    for i, row in enumerate(table):
        if i > 0:
            language = {}
            columns = row.find_all('td')
            language['name'] = __extractLanguageName(columns[2])
            language['nativeName'] = __extractLanguageName(columns[3])
            language['iso639_1'] = columns[4].text.encode('utf-8')
            language['iso639_2'] = columns[5].text.encode('utf-8')
            languages.append(language)
    return languages


def __extractLanguageName(html):
    text = html.text.encode('utf-8')
    return text.split(",")[0].strip()


def __updateJSON(countriesJSON, languages):
    for countryJSON in countriesJSON:
        for languageJSON in countryJSON['languages']:
            for language in languages:
                if languageJSON['iso639_1'] == language['iso639_1']:
                    languageJSON['iso639_2'] = language['iso639_2']
                    languageJSON['name'] = language['name']
                    languageJSON['nativeName'] = language['nativeName']
    return countriesJSON
