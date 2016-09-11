import json
import collections

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

def remove(prop, country):
    if prop in country:
        del country[prop]

countriesJSON = getCountriesJSON()
for country in countriesJSON:
    remove('currency', country)
saveCountriesJSON(countriesJSON)
