package eu.fayder.restcountries.v3.domain

data class RegionalBloc
(
        val acronym: String?,
        val name: String,
        val translations: List<Translations>
)