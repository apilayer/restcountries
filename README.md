REST Countries v0.9.2
=====================

Get information about any country via a RESTful API http://restcountries.eu

----------

Branches
---------
##### dev
* well... dev environment. all changes are made here before new releases.
* using java7 and servlet api 3

##### master
* latest stable release
* using java7 and servlet api 3

##### servlet2
* latest stable release
* using java6 and servlet api 2

Available Data
---------

    name
    currency code(s)
    calling code(s)
    capital city
    region
    subregion
    alternative spellings
    relevance
    nationality (demonym)
    latlng (geolocation)
    ISO 639-1 languages
    translations
    top-level domain (tld)
    code ISO 3166-1 alpha-2 (cca2)
    code ISO 3166-1 numeric (ccn3)
    code ISO 3166-1 alpha-3 (cca3)
    population
----------

REST Services
---------
- By code ISO 3166-1 alpha-2 http://restcountries.eu/rest/alpha/NO
- By code ISO 3166-1 alpha-3 http://restcountries.eu/rest/alpha/NOR
- By currency http://restcountries.eu/rest/currency/NOK
- By callingcode http://restcountries.eu/rest/callingcode/47
- By name http://restcountries.eu/rest/name/norway
- By capital city http://restcountries.eu/rest/capital/oslo
- By region http://restcountries.eu/rest/region/americas
- By subregion http://restcountries.eu/rest/subregion/southern%20asia
- By language http://restcountries.eu/rest/lang/et

Credits
---------
JSON Data is merged mostly from [@mledoze] and [@jonathantneal] <br />
Population data is from https://en.wikipedia.org/wiki/List_of_countries_by_population <br />
CSS http://bootswatch.com/flatly/

License
---------
Mozilla Public License http://www.mozilla.org/MPL/

[@mledoze]: https://github.com/mledoze/countries
[@jonathantneal]: https://github.com/jonathantneal/countries
