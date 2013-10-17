REST Countries
=====================

Get information about any country via a RESTful API. This branch uses Java6 and Servlet API 2

----------

Work in Progress
---------
- More REST search options

Available Data
---------

    name
    top-level domain (tld)
    code ISO 3166-1 alpha-2 (cca2)
    code ISO 3166-1 numeric (ccn3)
    code ISO 3166-1 alpha-3 (cca3)
    currency code(s)
    calling code(s)
    capital city
    region
    subregion
----------

REST Services
---------
### By code ISO 3166-1 alpha-2
http://domain.com/rest/alpha2/NO

### By code ISO 3166-1 alpha-3
http://domain.com/rest/alpha3/NOR

### By currency
http://domain.com/rest/currency/NOK

### By callingcode
http://domain.com/rest/callingcode/47

### By name (english)
http://domain.com/rest/name/Norway

Credits
---------
JSON Data is taken from https://github.com/mledoze/countries

License
---------
Mozilla Public License http://www.mozilla.org/MPL/
