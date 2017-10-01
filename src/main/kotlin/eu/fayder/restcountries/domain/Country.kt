package eu.fayder.restcountries.domain

import com.google.gson.annotations.SerializedName

data class Country
(
        val name: Name,
        val cca2: String,
        val cca3: String,
        val ccn3: String,
        val cioc: String,
        val capital: String,
        val region: String,
        val tld: List<String>,
//        val currencies: List<Currency>,
        @SerializedName("callingCode")
        val callingCodes: List<String>,
//        val languages: List<Language>,
        val translations: Translations,
        val latlng: List<Float>,
        val demonym: String,
        val landlocked: Boolean,
        val borders: List<String>,
        val area: Float?,
        val flag: String,
        val population: Int,
        val gini: Float?
)