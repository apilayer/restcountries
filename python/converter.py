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
        countryJSON['currencies'] = []
        countryJSON['languages'] = []
        countryJSON['alpha2Code'] = countryJSON['cca2']
        countryJSON['alpha3Code'] = countryJSON['cca3']
        countryJSON['callingCodes'] = countryJSON['callingCode']
#        for currency in countryJSON['currency']:
#            countryJSON['currencies'].append({"code": currency})
        for language in countryJSON['languageCodes']:
            countryJSON['languages'].append({"code": language})
    countryJSON.pop('currency')
    countryJSON.pop('language')
    countryJSON.pop('languageCodes')
    countryJSON.pop('cca2')
    countryJSON.pop('cca3')
    countryJSON.pop('callingCode')
    return countriesJSON


countriesJSON = getCountriesJSON()
convert(countriesJSON)
saveCountriesJSON(countriesJSON)
