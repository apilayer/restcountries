package eu.fayder.restcountries.domain

data class Country
(
        val currencies: List<Currency>?,
        val languages: List<Language>,
        val flag: String,

        // From mledoze
        val names: Names
//        val translations: Translations,
//        val cioc: String
//        val landlocked: Boolean,
) : BaseCountry()