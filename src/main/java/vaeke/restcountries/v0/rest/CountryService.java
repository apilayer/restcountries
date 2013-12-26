/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package vaeke.restcountries.v0.rest;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import vaeke.restcountries.rest.ICountryRestSymbols;
import vaeke.restcountries.v0.domain.Country;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class CountryService {
	
	private static final Logger LOG = Logger.getLogger(CountryService.class);
	
	private static CountryService countryService;
	private static List<Country> countries;
	
	private CountryService() throws IOException{
		initialize();
	}
	
	public static CountryService getInstance() throws IOException {
		if (countryService == null) {
			countryService = new CountryService();
		}
		return countryService;
	}
	
	public List<Country> getAll() {
		return countries;
	}
	
	public Country getByAlpha(String alpha) {
		int alphaLength = alpha.length();
		for(Country country : countries) {
			if (alphaLength == 2) {
				if (country.getCca2().toLowerCase().equals(alpha.toLowerCase())) {
					return country;
				}
			} else if (alphaLength == 3) {
				if (country.getCca3().toLowerCase().equals(alpha.toLowerCase())) {
					return country;
				}
			}
		}
		return null;
	}
	
	public List<Country> getByCodeList(String codelist) {
		List<Country> result = new ArrayList<Country>();
		if(codelist == null) return result;
		
		List<String> codes = Arrays.asList(codelist.split(ICountryRestSymbols.SEMICOLON));
		for(String code : codes) {
			Country country = this.getByAlpha(code);
			if(!result.contains(country))
				result.add(country);
		}
		return result;
	}
	
	public List<Country> getByCurrency(String currency) {
		List<Country> result = new ArrayList<Country>();
		for(Country country : countries) {
			if(country.getCurrency().toLowerCase().contains(currency.toLowerCase())) {
				result.add(country);
			}
		}
		return result;
	}
	
	public List<Country> getByName(String name) {
		List<Country> result = new ArrayList<Country>();
		for(Country country : countries) {
			if(normalize(country.getName().toLowerCase()).contains(normalize(name.toLowerCase()))) {
				result.add(country);
			}
			if(normalize(
					country.getAltSpellings().toLowerCase()).contains(normalize(name.toLowerCase())) 
					&& !result.contains(country)) {
				result.add(country);
			}
		}
		return result;
	}
	
	public List<Country> getByCallingcode(String callingcode) {
		List<Country> result = new ArrayList<Country>();
		for(Country country : countries) {
			if(country.getCallingCode().equals(callingcode))
				result.add(country);
		}
		return result;
	}
	
	public List<Country> getByCapital(String capital) {
		List<Country> result = new ArrayList<Country>();
		for(Country country : countries) {
			if(normalize(country.getCapital().toLowerCase()).contains(normalize(capital.toLowerCase()))) {
				result.add(country);
			}
		}
		return result;
	}
	
	public List<Country> getByRegion(String region) {
		List<Country> result = new ArrayList<Country>();
		for(Country country : countries) {
			if(country.getRegion().toLowerCase().equals(region.toLowerCase())) {
				result.add(country);
			}
		}
		return result;
	}
	
	public List<Country> getBySubregion(String subregion) {
		List<Country> result = new ArrayList<Country>();
		for(Country country : countries) {
			if(country.getSubregion().toLowerCase().equals(subregion.toLowerCase())) {
				result.add(country);
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
	
	private void initialize() throws IOException {
		LOG.debug("Loading JSON Database");
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("countries.json");
		Gson gson = new Gson();
		JsonReader reader = new JsonReader(new InputStreamReader(is, "UTF-8"));
		countries = new ArrayList<Country>();
		reader.beginArray();
		while(reader.hasNext()) {
			Country country = gson.fromJson(reader, Country.class);
			countries.add(country);
		}
		reader.endArray();
        reader.close();
        
	}
	private String normalize(String string) {
	    return Normalizer.normalize(string, Form.NFD)
	        .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	}
	
}
