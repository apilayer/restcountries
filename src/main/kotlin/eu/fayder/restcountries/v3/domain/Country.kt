package eu.fayder.restcountries.v3.domain

import eu.fayder.restcountries.domain.BaseCountry

data class Country
(
        val names: Names,
        val currencies: List<Currency>?,
        val languages: List<Language>,
        val flag: String,
        val translations: Translations,
        val cioc: String
) : BaseCountry()