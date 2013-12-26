/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package vaeke.restcountries.rest;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.runner.RunWith;

import vaeke.restcountries.domain.Country;

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
	public Destination destination = new Destination(this, "http://localhost:8081/rest");

	@Context
	private Response response;

	@HttpTest(method = Method.GET, path = "/")
	public void checkOnlineStatus() {
		Assert.assertOk(response);
	}
	
	@HttpTest(method = Method.GET, path = "/alpha/co")
	public void alpha2() {
		Assert.assertOk(response);
		org.junit.Assert.assertTrue(response.getBody().contains("\"alpha2Code\":\"CO\""));
	}
	
	@HttpTest(method = Method.GET, path = "/alpha/col")
	public void alpha3() {
		Assert.assertOk(response);
		org.junit.Assert.assertTrue(response.getBody().contains("\"alpha3Code\":\"COL\""));
	}
	
	@HttpTest(method = Method.GET, path = "/currency/eur")
	public void currency() {
		Assert.assertOk(response);
		List<Country> countries = deserializeList(response.getBody());
		org.junit.Assert.assertFalse(countries.isEmpty());
		for(Country country : countries) {
			org.junit.Assert.assertTrue(country.getCurrency().toLowerCase().contains("eur"));
		}
	}
	
	@HttpTest(method = Method.GET, path = "/callingcode/1")
	public void callingcode() {
		Assert.assertOk(response);
		List<Country> countries = deserializeList(response.getBody());
		org.junit.Assert.assertFalse(countries.isEmpty());
		org.junit.Assert.assertTrue(response.getBody().contains("\"callingcode\":\"1\""));
	}
	
	@HttpTest(method = Method.GET, path = "/capital/washington")
	public void capital() {
		Assert.assertOk(response);
		List<Country> countries = deserializeList(response.getBody());
		org.junit.Assert.assertFalse(countries.isEmpty());
		for(Country country : countries) {
			org.junit.Assert.assertEquals("washington d.c.", country.getCapital().toLowerCase());
		}
	}
	
	@HttpTest(method = Method.GET, path = "/region/europe")
	public void region() {
		Assert.assertOk(response);
		List<Country> countries = deserializeList(response.getBody());
		for(Country country : countries) {
			org.junit.Assert.assertEquals("europe", country.getRegion().toLowerCase());
		}
	}
	
	@HttpTest(method = Method.GET, path = "/lang/et")
	public void language() {
		Assert.assertOk(response);
		List<Country> countries = deserializeList(response.getBody());
		for(Country country : countries) {
			org.junit.Assert.assertTrue(country.getLanguages().contains("et"));
			System.out.println(country.getName());
		}
	}
	
	@HttpTest(method = Method.GET, path = "/alpha?codes=ar;be;fr;it")
	public void alphaList() {
		Assert.assertOk(response);
		List<Country> countries = deserializeList(response.getBody());
		org.junit.Assert.assertFalse(countries.isEmpty());
		org.junit.Assert.assertEquals(4, countries.size());
		org.junit.Assert.assertTrue(response.getBody().contains("\"alpha2Code\":\"AR\""));
		org.junit.Assert.assertTrue(response.getBody().contains("\"alpha2Code\":\"BE\""));
		org.junit.Assert.assertTrue(response.getBody().contains("\"alpha2Code\":\"FR\""));
		org.junit.Assert.assertTrue(response.getBody().contains("\"alpha2Code\":\"IT\""));
	}
	
	@HttpTest(method = Method.GET, path = "/name/russia")
	public void name() {
		Assert.assertOk(response);
		List<Country> countries = deserializeList(response.getBody());
		org.junit.Assert.assertFalse(countries.isEmpty());
		for(Country c : countries) {
			org.junit.Assert.assertEquals("Russia", c.getName());
		}
		
	}
	
	private List<Country> deserializeList(String json) {
		Gson gson = new Gson();
		Type listType = new TypeToken<ArrayList<Country>>() {}.getType();
		List<Country> countries = gson.fromJson(json, listType);
		return countries;
	}
	
}
