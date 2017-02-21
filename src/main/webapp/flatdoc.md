REST Countries
=======

Get information about countries via a RESTful API

[![Build Status](https://travis-ci.org/fayder/restcountries.svg?branch=master)](https://travis-ci.org/fayder/restcountries)

Contribute!
---------------
Maintining and updating REST Countries costs me around 360€ per year. Please consider [donating] so I can cover those expenses.

Stay up-to-date
---------------
Follow us on [Twitter]

Subscribe to the [mailing list]

API Endpoints
=======

Below are described the REST endpoints available that you can use to search for countries

All
---------------

``` html
https://restcountries.eu/rest/v2/all
```

Name
---------------

Search by country name. It can be the native name or partial name

``` javascript
https://restcountries.eu/rest/v2/name/{name}
```

``` html
https://restcountries.eu/rest/v2/name/eesti
```

``` html
https://restcountries.eu/rest/v2/name/united
```

Full Name
---------------

Search by country full name

``` javascript
https://restcountries.eu/rest/v2/name/{name}?fullText=true
```

``` html
https://restcountries.eu/rest/v2/name/aruba?fullText=true
```

Code
---------------

Search by ISO 3166-1 2-letter or 3-letter country code

``` javascript
https://restcountries.eu/rest/v2/alpha/{code}
```

``` html
https://restcountries.eu/rest/v2/alpha/co
```

``` html
https://restcountries.eu/rest/v2/alpha/col
```

List of codes
---------------

Search by list of ISO 3166-1 2-letter or 3-letter country codes

``` javascript
https://restcountries.eu/rest/v2/alpha?codes={code};{code};{code}
```

``` html
https://restcountries.eu/rest/v2/alpha?codes=col;no;ee
```

Currency
---------------

Search by ISO 4217 currency code

``` javascript
https://restcountries.eu/rest/v2/currency/{currency}
```
``` html
https://restcountries.eu/rest/v2/currency/cop
```

Language
---------------

Search by ISO 639-1 language code

``` javascript
https://restcountries.eu/rest/v2/lang/{et}
```
``` html
https://restcountries.eu/rest/v2/lang/es
```

Capital city
---------------

Search by capital city

``` javascript
https://restcountries.eu/rest/v2/capital/{capital}
```
``` html
https://restcountries.eu/rest/v2/capital/tallinn
```

Calling code
---------------

Search by calling code

``` javascript
https://restcountries.eu/rest/v2/callingcode/{callingcode}
```
``` html
https://restcountries.eu/rest/v2/callingcode/372
```

Region
---------------

Search by region: Africa, Americas, Asia, Europe, Oceania

``` javascript
https://restcountries.eu/rest/v2/region/{region}
```
``` html
https://restcountries.eu/rest/v2/region/europe
```

Response Example
---------------

``` html
https://restcountries.eu/rest/v2/name/afghanistan
```

``` json
[{
	"name": "Afghanistan",
	"topLevelDomain": [".af"],
	"alpha2Code": "AF",
	"alpha3Code": "AFG",
	"callingCodes": ["93"],
	"capital": "Kabul",
	"altSpellings": ["AF", "Afġānistān"],
	"region": "Asia",
	"subregion": "Southern Asia",
	"translations": {
		"de": "Afghanistan",
		"es": "Afganistán",
		"fr": "Afghanistan",
		"ja": "アフガニスタン",
		"it": "Afghanistan"
	},
	"population": 27657145,
	"latlng": [33.0, 65.0],
	"demonym": "Afghan",
	"area": 652230.0,
	"gini": 27.8,
	"timezones": ["UTC+04:30"],
	"borders": ["IRN", "PAK", "TKM", "UZB", "TJK", "CHN"],
	"nativeName": "افغانستان",
	"numericCode": "004",
	"currencies": [{
		"code": "AFN",
		"name": "Afghan afghani",
		"symbol": "؋"
	}],
	"languages": [{
		"iso639_1": "ps",
		"iso639_2": "pus",
		"name": "Pashto",
		"nativeName": "پښتو"
	}, {
		"iso639_1": "uz",
		"iso639_2": "uzb",
		"name": "Uzbek",
		"nativeName": "Oʻzbek"
	}, {
		"iso639_1": "tk",
		"iso639_2": "tuk",
		"name": "Turkmen",
		"nativeName": "Türkmen"
	}]
}]
```

[dist]: https://github.com/fayder/restcountries/
[Twitter]: https://twitter.com/restcountries
[mailing list]: http://eepurl.com/cC-h2v
[donating]: #donation