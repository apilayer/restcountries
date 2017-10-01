package eu.fayder.restcountries.domain

data class RegionalBloc
(
        val acronym: String?,
        val name: String,
        val translations: List<Translations>
)