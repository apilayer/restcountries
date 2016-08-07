/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package fayder.restcountries.v1;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.junit.Rule;
import org.junit.runner.RunWith;

import fayder.restcountries.v1.domain.DesCountry;

import com.eclipsesource.restfuse.Assert;
import com.eclipsesource.restfuse.Destination;
import com.eclipsesource.restfuse.HttpJUnitRunner;
import com.eclipsesource.restfuse.Method;
import com.eclipsesource.restfuse.Response;
import com.eclipsesource.restfuse.annotation.Context;
import com.eclipsesource.restfuse.annotation.HttpTest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RunWith(HttpJUnitRunner.class)
public class CountryRestTest {
	
	@Rule
	public Destination destination = new Destination(this, "http://localhost:8080/rest/v1");
	
	@Context
	private Response response;

	@HttpTest(method = Method.GET, path = "/")
	public void checkOnlineStatus() {
		Assert.assertOk(response);
	}
	
	@HttpTest(method = Method.GET, path = "/alpha/co")
	public void alpha2() {
		Assert.assertOk(response);
		DesCountry country = deserialize(response.getBody());
		org.junit.Assert.assertEquals("CO", country.getAlpha2Code());
	}
	
	@HttpTest(method = Method.GET, path = "/alpha/col")
	public void alpha3() {
		Assert.assertOk(response);
		DesCountry country = deserialize(response.getBody());
		org.junit.Assert.assertEquals("COL", country.getAlpha3Code());
	}
	
	@HttpTest(method = Method.GET, path = "/currency/eur")
	public void currency() {
		Assert.assertOk(response);
		List<DesCountry> countries = deserializeList(response.getBody());
		org.junit.Assert.assertFalse(countries.isEmpty());
		for(DesCountry country : countries) {
			org.junit.Assert.assertTrue(country.getCurrencies().contains("EUR"));
		}
	}
	
	@HttpTest(method = Method.GET, path = "/callingcode/1")
	public void callingcode() {
		Assert.assertOk(response);
		List<DesCountry> countries = deserializeList(response.getBody());
		org.junit.Assert.assertFalse(countries.isEmpty());
		for(DesCountry country : countries) {
			org.junit.Assert.assertTrue(country.getCallingCodes().contains("1"));
		}
	}
	
	@HttpTest(method = Method.GET, path = "/capital/washington")
	public void capital() {
		Assert.assertOk(response);
		List<DesCountry> countries = deserializeList(response.getBody());
		org.junit.Assert.assertFalse(countries.isEmpty());
		for(DesCountry country : countries) {
			org.junit.Assert.assertEquals("washington d.c.", country.getCapital().toLowerCase());
		}
	}
	
	@HttpTest(method = Method.GET, path = "/region/europe")
	public void region() {
		Assert.assertOk(response);
		List<DesCountry> countries = deserializeList(response.getBody());
		for(DesCountry country : countries) {
			org.junit.Assert.assertEquals("europe", country.getRegion().toLowerCase());
		}
	}
	
	@HttpTest(method = Method.GET, path = "/lang/en")
	public void language() {
		Assert.assertOk(response);
		List<DesCountry> countries = deserializeList(response.getBody());
		for(DesCountry country : countries) {
			org.junit.Assert.assertTrue(country.getLanguages().contains("en"));
		}
	}
	
	@HttpTest(method = Method.GET, path = "/alpha?codes=ar;be;fr;it")
	public void alphaList() {
		Assert.assertOk(response);
		List<DesCountry> countries = deserializeList(response.getBody());
		org.junit.Assert.assertFalse(countries.isEmpty());
		org.junit.Assert.assertEquals(4, countries.size());
		
		org.junit.Assert.assertEquals("AR", countries.get(0).getAlpha2Code());
		org.junit.Assert.assertEquals("BE", countries.get(1).getAlpha2Code());
		org.junit.Assert.assertEquals("FR", countries.get(2).getAlpha2Code());
		org.junit.Assert.assertEquals("IT", countries.get(3).getAlpha2Code());
	}
	
	@HttpTest(method = Method.GET, path = "/name/russia")
	public void name() {
		Assert.assertOk(response);
		List<DesCountry> countries = deserializeList(response.getBody());
		org.junit.Assert.assertFalse(countries.isEmpty());
		for(DesCountry c : countries) {
			org.junit.Assert.assertEquals("Russia", c.getName());
		}
	}
	
	@HttpTest(method = Method.GET, path = "/name/russian%20federation?searchFullText=true")
	public void nameFulltext() {
		Assert.assertOk(response);
		List<DesCountry> countries = deserializeList(response.getBody());
		org.junit.Assert.assertFalse(countries.isEmpty());
		for(DesCountry c : countries) {
			org.junit.Assert.assertEquals("Russia", c.getName());
		}
	}
	
	@HttpTest(method = Method.GET, path = "/name/russian%20fed?fullText=true")
	public void nameFulltextNotFound() {
		Assert.assertNotFound(response);
	}
	
	@HttpTest(method = Method.GET, path = "/alpha/co")
	public void getBorders() {
		Assert.assertOk(response);
		DesCountry country = deserialize(response.getBody());
		org.junit.Assert.assertFalse(country == null);
		List<String> bordercountry = country.getBorders();
		org.junit.Assert.assertTrue(bordercountry.size() == 5);
		Collection<String> c = new HashSet<String>();
		c.add("BRA");
		c.add("ECU");
		c.add("PAN");
		c.add("PER");
		c.add("VEN");
		org.junit.Assert.assertTrue(bordercountry.containsAll(c));
	}
	
	private DesCountry deserialize(String json) {
		Gson gson = new Gson();
		DesCountry country = gson.fromJson(json, DesCountry.class);
		return country;
	}
	
	private List<DesCountry> deserializeList(String json) {
		Gson gson = new Gson();
		Type listType = new TypeToken<ArrayList<DesCountry>>() {}.getType();
		List<DesCountry> countries = gson.fromJson(json, listType);
		return countries;
	}

}
