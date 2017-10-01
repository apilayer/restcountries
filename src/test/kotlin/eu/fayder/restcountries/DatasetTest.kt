package eu.fayder.restcountries

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import eu.fayder.restcountries.domain.Country
import org.junit.Before

class DatasetTest {
    private lateinit var countries: List<Country>

    @Before
    fun setUp() {
        val content = javaClass.classLoader.getResourceAsStream("countriesV3.json").bufferedReader().use { it.readText() }
        val countriesType = object : TypeToken<List<Country>>() {}.type
        countries = Gson().fromJson<List<Country>>(content, countriesType)
    }

    
}