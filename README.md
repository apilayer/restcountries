REST Countries
=======

Get information about countries via a RESTful API https://restcountries.azurewebsites.net

This is a C# port of https://github.com/apilayer/restcountries

![Deployment](https://github.com/jprimke/restcountries/actions/workflows/restcountries.yml/badge.svg)

API Endpoints
=======

Below are described the REST endpoints available that you can use to search for countries

All
---------------

``` html
https://restcountries.azurewebsites.net/countries/all
```

Name
---------------

Search by country name. It can be the native name or partial name

``` javascript
https://restcountries.azurewebsites.net/countries/name/{name}
```

``` html
https://restcountries.azurewebsites.net/countries/name/eesti
```

``` html
https://restcountries.azurewebsites.net/countries/name/united
```

Full Name
---------------

Search by country full name

``` javascript
https://restcountries.azurewebsites.net/countries/name/{name}?fullText=true
```

``` html
https://restcountries.azurewebsites.net/countries/name/aruba?fullText=true
```

Code
---------------

Search by ISO 3166-1 2-letter or 3-letter country code

``` javascript
https://restcountries.azurewebsites.net/countries/alpha/{code}
```

``` html
https://restcountries.azurewebsites.net/countries/alpha/co
```

``` html
https://restcountries.azurewebsites.net/countries/alpha/col
```

List of codes
---------------

Search by list of ISO 3166-1 2-letter or 3-letter country codes

``` javascript
https://restcountries.azurewebsites.net/countries/alpha?codes={code};{code};{code}
```

``` html
https://restcountries.azurewebsites.net/countries/alpha?codes=col;no;ee
```

Currency
---------------

Search by ISO 4217 currency code

``` javascript
https://restcountries.azurewebsites.net/countries/currency/{currency}
```
``` html
https://restcountries.azurewebsites.net/countries/currency/cop
```

Language
---------------

Search by ISO 639-1 language code

``` javascript
https://restcountries.azurewebsites.net/countries/lang/{et}
```
``` html
https://restcountries.azurewebsites.net/countries/lang/es
```

Capital city
---------------

Search by capital city

``` javascript
https://restcountries.azurewebsites.net/countries/capital/{capital}
```
``` html
https://restcountries.azurewebsites.net/countries/capital/tallinn
```

Calling code
---------------

Search by calling code

``` javascript
https://restcountries.azurewebsites.net/countries/callingcode/{callingcode}
```
``` html
https://restcountries.azurewebsites.net/countries/callingcode/372
```

Region
---------------

Search by region: Africa, Americas, Asia, Europe, Oceania

``` javascript
https://restcountries.azurewebsites.net/countries/region/{region}
```
``` html
https://restcountries.azurewebsites.net/countries/region/europe
```

Regional Bloc
---------------

Search by regional bloc:

- EU (European Union)
- EFTA (European Free Trade Association)
- CARICOM (Caribbean Community)
- PA (Pacific Alliance)
- AU (African Union)
- USAN (Union of South American Nations)
- EEU (Eurasian Economic Union)
- AL (Arab League)
- ASEAN (Association of Southeast Asian Nations)
- CAIS (Central American Integration System)
- CEFTA (Central European Free Trade Agreement)
- NAFTA (North American Free Trade Agreement)
- SAARC (South Asian Association for Regional Cooperation)

``` javascript
https://restcountries.azurewebsites.net/countries/regionalbloc/{regionalbloc}
```
``` html
https://restcountries.azurewebsites.net/countries/regionalbloc/eu
```

Response Example
---------------

``` html
https://restcountries.azurewebsites.net/countries/alpha/col
```

``` json
[
  {
    "name": "Germany",
    "topLevelDomain": [
      ".de"
    ],
    "alpha2Code": "DE",
    "alpha3Code": "DEU",
    "callingCodes": [
      "49"
    ],
    "capital": "Berlin",
    "altSpellings": [
      "DE",
      "Federal Republic of Germany",
      "Bundesrepublik Deutschland"
    ],
    "subRegion": "Central Europe",
    "region": "Europe",
    "population": 83240525,
    "latlng": [
      51,
      9
    ],
    "demonym": "German",
    "area": 357114,
    "timezones": [
      "UTC+01:00"
    ],
    "borders": [
      "AUT",
      "BEL",
      "CZE",
      "DNK",
      "FRA",
      "LUX",
      "NLD",
      "POL",
      "CHE"
    ],
    "nativeName": "Deutschland",
    "numericCode": "276",
    "currencies": [
      {
        "code": "EUR",
        "name": "Euro",
        "symbol": "€"
      }
    ],
    "languages": [
      {
        "iso639_1": "de",
        "iso639_2": "deu",
        "name": "German",
        "nativeName": "Deutsch"
      }
    ],
    "translations": {
      "br": "Alemanha",
      "pt": "Alemanha",
      "nl": "Duitsland",
      "hr": "Njemačka",
      "fa": "آلمان",
      "de": "Deutschland",
      "es": "Alemania",
      "fr": "Allemagne",
      "ja": "ドイツ",
      "it": "Germania",
      "hu": "Grúzia"
    },
    "flags": {
      "svg": "https://upload.wikimedia.org/wikipedia/en/b/ba/Flag_of_Germany.svg",
      "png": "https://upload.wikimedia.org/wikipedia/en/thumb/b/ba/Flag_of_Germany.svg/320px-Flag_of_Germany.svg.png"
    },
    "flag": "https://upload.wikimedia.org/wikipedia/en/b/ba/Flag_of_Germany.svg",
    "cioc": "GER",
    "regionalBlocs": [
      {
        "acronym": "EU",
        "name": "European Union"
      }
    ],
    "independent": true
  }
]
```

Sources
=======
* [@mledoze]
* [List of countries]
* [Languages]
* [Currencies]
* [Area]

Similar projects
=======
* [Countries of the world]
* [REST Countries Node.js]
* [REST Countries Ruby]
* [REST Countries Go]
* [REST Countries Python]
* [world-currencies]
* [REST Countries C#](https://github.com/egbakou/RESTCountries.NET)

License
=======
[Mozilla Public License] MPL 2.0

[@mledoze]: https://github.com/mledoze/countries
[List of countries]: https://en.wikipedia.org/wiki/ISO_3166-1#Current_codes
[Languages]: https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes
[Currencies]: https://en.wikipedia.org/wiki/List_of_circulating_currencies
[Area]: https://en.wikipedia.org/wiki/List_of_countries_and_dependencies_by_area
[Population]: https://en.wikipedia.org/wiki/List_of_countries_by_population
[Gini coefficient]: http://en.wikipedia.org/wiki/List_of_countries_by_income_equality
[Mozilla Public License]: https://www.mozilla.org/en-US/MPL/2.0/
[world-currencies]: https://github.com/wiredmax/world-currencies
[REST Countries Node.js]: https://github.com/aredo/restcountries
[REST Countries Ruby]: https://github.com/davidesantangelo/restcountry
[REST Countries Go]: https://github.com/alediaferia/gocountries
[REST Countries Python]: https://github.com/SteinRobert/python-restcountries
[Countries of the world]: http://countries.petethompson.net
[TTÜ]: https://www.ttu.ee/studying/tut_admission/programmes-in-tut/ask-us/
[Spotify International Pricing Index]: http://mts.io/2014/05/07/spotify-pricing-index/
[Gorillaz]: http://www.gorillaz.com/
[Wanderlust]: https://wanderlust.com/
[Xero]: https://www.xero.com/
[FxPro]: http://www.fxpro.com/
[onefinestay]: https://www.onefinestay.com/
[Much Better Adventures]: https://www.muchbetteradventures.com
[SKROSS]: http://www.skross.com/en
