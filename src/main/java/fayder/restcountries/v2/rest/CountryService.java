/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package fayder.restcountries.v2.rest;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import fayder.restcountries.v2.domain.Country;
import org.apache.log4j.Logger;
import fayder.restcountries.domain.ICountryRestSymbols;
import fayder.restcountries.v2.domain.Currency;
import fayder.restcountries.v2.domain.Language;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountryService {

    private static final Logger LOG = Logger.getLogger(CountryService.class);

    private static List<Country> countries;

    private CountryService() {
        initialize();
    }

    private static class InstanceHolder {
        public static final CountryService INSTANCE = new CountryService();
    }

    public static CountryService getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public List<Country> getAll() {
        return countries;
    }

    public Country getByAlpha(String alpha) {
        int alphaLength = alpha.length();
        for (Country country : countries) {
            if (alphaLength == 2) {
                if (country.getAlpha2Code().toLowerCase().equals(alpha.toLowerCase())) {
                    return country;
                }
            } else if (alphaLength == 3) {
                if (country.getAlpha3Code().toLowerCase().equals(alpha.toLowerCase())) {
                    return country;
                }
            }
        }
        return null;
    }

    public List<Country> getByCodeList(String codelist) {
        List<Country> result = new ArrayList<Country>();
        if (codelist == null) return result;

        List<String> codes = Arrays.asList(codelist.split(ICountryRestSymbols.SEMICOLON));
        for (String code : codes) {
            Country country = this.getByAlpha(code);
            if (!result.contains(country))
                result.add(country);
        }
        return result;
    }

    public List<Country> getByCurrency(String currency) {
        List<Country> result = new ArrayList<Country>();
        for (Country country : countries) {
            for (Currency curr : country.getCurrencies()) {
                if (curr.getCode() != null && currency.toLowerCase().equals(curr.getCode().toLowerCase())) {
                    result.add(country);
                }
            }
        }
        return result;
    }

    public List<Country> getByName(String name, boolean fullText) {
        if (fullText) {
            return fulltextSearch(name);
        } else {
            return substringSearch(name);
        }

    }

    private List<Country> substringSearch(String name) {
        // Using 2 different 'for' loops to give priority to 'name' matches over alternative spellings
        List<Country> result = new ArrayList<Country>();
        for (Country country : countries) {
            if (normalize(country.getName().toLowerCase()).contains(normalize(name.toLowerCase()))) {
                result.add(country);
            }
        }
        for (Country country : countries) {
            for (String alternative : country.getAltSpellings()) {
                if (normalize(alternative.toLowerCase()).contains(normalize(name.toLowerCase()))
                        && !result.contains(country)) {
                    result.add(country);
                }
            }
        }
        return result;
    }

    private List<Country> fulltextSearch(String name) {
        // Using 2 different 'for' loops to give priority to 'name' matches over alternative spellings
        List<Country> result = new ArrayList<Country>();
        for (Country country : countries) {
            if (normalize(country.getName().toLowerCase()).equals(normalize(name.toLowerCase()))) {
                result.add(country);
            }
        }
        for (Country country : countries) {
            for (String alternative : country.getAltSpellings()) {
                if (normalize(alternative.toLowerCase()).equals(normalize(name.toLowerCase()))
                        && !result.contains(country)) {
                    result.add(country);
                }
            }
        }
        return result;
    }

    public List<Country> getByCallingCode(String callingcode) {
        List<Country> result = new ArrayList<Country>();
        for (Country country : countries) {
            for (String callingCode : country.getCallingCodes()) {
                if (callingCode.equals(callingcode))
                    result.add(country);
            }
        }
        return result;
    }

    public List<Country> getByCapital(String capital) {
        List<Country> result = new ArrayList<Country>();
        for (Country country : countries) {
            if (normalize(country.getCapital().toLowerCase()).contains(normalize(capital.toLowerCase()))) {
                result.add(country);
            }
        }
        return result;
    }

    public List<Country> getByRegion(String region) {
        List<Country> result = new ArrayList<Country>();
        for (Country country : countries) {
            if (country.getRegion().toLowerCase().equals(region.toLowerCase())) {
                result.add(country);
            }
        }
        return result;
    }

    public List<Country> getByLanguage(String language) {
        List<Country> result = new ArrayList<Country>();
        if (language.length() == 2) {
            for (Country country : countries) {
                for (Language lang : country.getLanguages()) {
                    if (language.toLowerCase().equals(lang.getIso639_1())) {
                        result.add(country);
                    }
                }
            }
        } else if (language.length() == 3) {
            for (Country country : countries) {
                for (Language lang : country.getLanguages()) {
                    if (language.toLowerCase().equals(lang.getIso639_2())) {
                        result.add(country);
                    }
                }
            }
        }
        return result;
    }

    private void initialize() {
        LOG.debug("Loading JSON Database v2");
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("countriesV2.json");
        Gson gson = new Gson();
        JsonReader reader;
        try {
            reader = new JsonReader(new InputStreamReader(is, "UTF-8"));
            countries = new ArrayList<Country>();
            reader.beginArray();
            while (reader.hasNext()) {
                Country country = gson.fromJson(reader, Country.class);
                countries.add(country);
            }
            reader.endArray();
            reader.close();
        } catch (Exception e) {
            LOG.error("Could not load JSON Database v2", e);
        }
    }

    private String normalize(String string) {
        return Normalizer.normalize(string, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }
}
