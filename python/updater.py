# This is only for countriesV2
# 1- load countries-input.json
# 2- crawl,parse and update json
# 4- save json string in new file

import json
import collections
from crawlers import population
from crawlers import gini
from crawlers import currencies
from crawlers import languages
from crawlers import numeric_codes
from crawlers import regional_blocs

def getCountriesJSON():
    inputJsonFile = open('countries-input.json')
    inputJsonString = json.load(
        inputJsonFile, object_pairs_hook=collections.OrderedDict)
    inputJsonFile.close()
    return inputJsonString


def saveCountriesJSON(countriesJSON):
    outputJsonFile = open('countries-output.json', 'w')
    with open('countries-output.json', 'w') as outputJsonFile:
        json.dump(countriesJSON, outputJsonFile)

countriesJSON = getCountriesJSON()
#countriesJSON = population.update(countriesJSON)
#countriesJSON = gini.update(countriesJSON)
#countriesJSON = currencies.update(countriesJSON)
#countriesJSON = languages.update(countriesJSON)
#countriesJSON = numeric_codes.update(countriesJSON)
countriesJSON = regional_blocs.update(countriesJSON)
saveCountriesJSON(countriesJSON)
