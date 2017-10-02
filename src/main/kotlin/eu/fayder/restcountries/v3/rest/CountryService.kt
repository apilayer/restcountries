package eu.fayder.restcountries.v3.rest

import eu.fayder.restcountries.domain.BaseCountry
import eu.fayder.restcountries.rest.CountryServiceBase
import eu.fayder.restcountries.v3.domain.Country

object CountryService : CountryServiceBase(){
    private var countries: MutableList<out BaseCountry>? = loadJson("countriesV3.json", Country::class.java)
}