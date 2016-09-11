/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package fayder.restcountries.v1.rest;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fayder.restcountries.rest.CountryServiceHelper;
import fayder.restcountries.v1.domain.Country;
import org.apache.log4j.Logger;

import fayder.restcountries.domain.ICountryRestSymbols;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class CountryService {

	private static final Logger LOG = Logger.getLogger(CountryService.class);

	private static List<Country> countries;

	private CountryService() {
		initialize();
	}

	private static class InstanceHolder {
		private static final CountryService INSTANCE = new CountryService();
	}

	public static CountryService getInstance() {
		return InstanceHolder.INSTANCE;
	}

	public List<Country> getAll() {
		return countries;
	}

	public Country getByAlpha(String alpha) {
        return CountryServiceHelper.getByAlpha(alpha, countries);
	}

	@SuppressWarnings("unchecked")
	public List<Country> getByCodeList(String codeList) {
		return (List<Country>) CountryServiceHelper.getByCodeList(codeList, countries);
	}

    @SuppressWarnings("unchecked")
	public List<Country> getByName(String name, boolean isFullText) {
		return (List<Country>) CountryServiceHelper.getByName(name, isFullText, countries);

	}

    @SuppressWarnings("unchecked")
	public List<Country> getByCallingCode(String callingcode) {
		return (List<Country>) CountryServiceHelper.getByCallingCode(callingcode, countries);
	}

    @SuppressWarnings("unchecked")
	public List<Country> getByCapital(String capital) {
		return (List<Country>) CountryServiceHelper.getByCapital(capital, countries);
	}

    @SuppressWarnings("unchecked")
	public List<Country> getByRegion(String region) {
		return (List<Country>) CountryServiceHelper.getByRegion(region, countries);
	}

    @SuppressWarnings("unchecked")
	public List<Country> getBySubregion(String subregion) {
		return (List<Country>) CountryServiceHelper.getBySubregion(subregion, countries);
	}

    public List<Country> getByCurrency(String currency) {
        List<Country> result = new ArrayList<>();
        for(Country country : countries) {
            for(String curr : country.getCurrencies()) {
                if (curr.toLowerCase().equals(currency.toLowerCase())) {
                    result.add(country);
                }
            }
        }
        return result;
    }

	public List<Country> getByLanguage(String language) {
		List<Country> result = new ArrayList<Country>();
		for(Country country : countries) {
			for(String lang : country.getLanguages()) {
				if (lang.toLowerCase().equals(language.toLowerCase())) {
					result.add(country);
				}
			}
		}
		return result;
	}

	private void initialize() {
		LOG.debug("Loading JSON Database v1");
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("countriesV1.json");
		Gson gson = new Gson();
		JsonReader reader;
		try {
			reader = new JsonReader(new InputStreamReader(is, "UTF-8"));
			countries = new ArrayList<Country>();
			reader.beginArray();
			while(reader.hasNext()) {
				Country country = gson.fromJson(reader, Country.class);
				countries.add(country);
			}
			reader.endArray();
	        reader.close();
		} catch (Exception e) {
			LOG.error("Could not load JSON Database v1 ");
		}


	}
	private String normalize(String string) {
	    return Normalizer.normalize(string, Form.NFD)
	        .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	}

}
