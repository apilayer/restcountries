REST Countries
=======

Get information about countries via a RESTful API https://restcountries.eu

[![Build Status](https://travis-ci.org/fayder/restcountries.svg?branch=master)](https://travis-ci.org/fayder/restcountries)

Contribute!
---------------
**570€ per year** aprox. is the cost to maintain and update REST Countries. Please help me cover these costs so I can keep improving this service.

[Contribute]

### 2017 costs funded: 0%

Stay up-to-date
---------------
Follow us on [Twitter]

Or subscribe to the [mailing list]

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
Sources
=======
* [@mledoze]
* [List of countries]
* [Languages]
* [Currencies]
* [Area]

Similar projects
=======
* [REST Countries Node.js]
* [REST Countries Ruby]
* [REST Countries Go]
* [REST Countries Python]
* [world-currencies]

License
=======
[Mozilla Public License] MPL

[dist]: https://github.com/fayder/restcountries/
[Twitter]: https://twitter.com/restcountries
[mailing list]: http://eepurl.com/cC-h2v
[contribute]: https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=V5AJAEMKE6A3E
[@mledoze]: https://github.com/mledoze/countries
[List of countries]: https://en.wikipedia.org/wiki/ISO_3166-1#Current_codes
[Languages]: https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes
[Currencies]: https://en.wikipedia.org/wiki/List_of_circulating_currencies
[Area]: https://en.wikipedia.org/wiki/List_of_countries_and_dependencies_by_area
[Population]: https://en.wikipedia.org/wiki/List_of_countries_by_population
[Gini coefficient]: http://en.wikipedia.org/wiki/List_of_countries_by_income_equality
[Mozilla Public License]: http://www.mozilla.org/MPL/
[world-currencies]: https://github.com/wiredmax/world-currencies
[REST Countries Node.js]: https://github.com/aredo/restcountries
[REST Countries Ruby]: https://github.com/davidesantangelo/restcountry
[REST Countries Go]: https://github.com/alediaferia/gocountries
[REST Countries Python]: https://github.com/SteinRobert/python-restcountries