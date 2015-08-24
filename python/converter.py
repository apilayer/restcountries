# Converts Countries V1 syntax to Countries V2 syntax
import json
import collections


def getCountriesJSON():
    inputJsonFile = open('countries-input.json')
    inputJsonString = json.load(inputJsonFile, object_pairs_hook=collections.OrderedDict)
    inputJsonFile.close()
    return inputJsonString


def saveCountriesJSON(countriesJSON):
    outputJsonFile = open('countries-output.json', 'w')
    with open('countries-output.json', 'w') as outputJsonFile:
        json.dump(countriesJSON, outputJsonFile)


def convert(countriesJSON):
    for countryJSON in countriesJSON:
        pass
    return countriesJSON


countriesJSON = getCountriesJSON()
convert(countriesJSON)
saveCountriesJSON(countriesJSON)
