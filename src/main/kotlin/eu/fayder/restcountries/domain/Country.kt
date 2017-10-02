package eu.fayder.restcountries.domain

data class Country
(
        val currencies: List<Currency>?,
        val languages: List<Language>,
        val flag: String

        // From mledoze
//        val name: Name,
//        val translations: Translations,
//        val cioc: String
//        val landlocked: Boolean,
) : BaseCountry()