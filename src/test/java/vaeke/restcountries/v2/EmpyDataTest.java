package vaeke.restcountries.v2;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.junit.Before;
import org.junit.Test;
import vaeke.restcountries.v2_alpha.domain.Country;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fayder on 24/08/15.
 */
public class EmpyDataTest {

    List<Country> countries;

    @Before
    public void before() throws IOException {
        InputStream is = this.getClass().getClassLoader()
                .getResourceAsStream("countriesV2.json");
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new InputStreamReader(is, "UTF-8"));
        countries = new ArrayList<Country>();
        reader.beginArray();
        while (reader.hasNext()) {
            Country country = gson.fromJson(reader, Country.class);
            countries.add(country);
        }
        reader.endArray();
        reader.close();
    }

    @Test
    public void emptyLanguages() throws Exception {
        for (Country country : countries) {
            if (country.getLanguages().isEmpty()) {
                System.out.println(country.getName());
            }
        }

    }
}
