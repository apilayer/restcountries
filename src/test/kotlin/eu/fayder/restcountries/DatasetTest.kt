package eu.fayder.restcountries

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import eu.fayder.restcountries.v3.domain.Country
import org.junit.Before
import org.junit.Test

class DatasetTest {
    private lateinit var countries: List<Country>

    @Before
    fun setUp() {
        val content = javaClass.classLoader.getResourceAsStream("countriesV3.json").bufferedReader().use { it.readText() }
        val countriesType = object : TypeToken<List<Country>>() {}.type
        countries = Gson().fromJson<List<Country>>(content, countriesType)
    }

    @Test
    fun population() {
        println("-- Empty Population --")
        countries
                .filter { it.population == null || it.population == 0 }
                .forEach { println(it.name) }
    }

    @Test
    fun gini() {
        println("-- Empty Gini --")
        countries
                .filter { it.gini == null }
                .forEach { println(it.name) }
    }

    @Test
    fun currencies() {
        println("-- Empty Currencies --")
        countries
                .filter { it.currencies == null || it.currencies!!.isEmpty() }
                .forEach { println(it.name) }
    }
}