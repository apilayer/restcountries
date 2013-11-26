/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package vaeke.countrydata.rest;

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
public class CountryRestMultipleCountriesTest {

	@Rule
	public Destination destination = new Destination(this, "http://localhost:8081/rest");

	@Context
	private Response response;
	
	@HttpTest(method = Method.GET, path = "/multiplecountries?countrylist=ar;be;fr;it")
	public void multiplecountries() {
		Assert.assertOk(response);
		List<Country> countries = deserializeList(response.getBody());
		org.junit.Assert.assertFalse(countries.isEmpty());
		for(Country country : countries) {
			System.out.println(country.getName());
		}
	}
	
	private List<Country> deserializeList(String json) {
		Gson gson = new Gson();
		Type listType = new TypeToken<ArrayList<Country>>() {}.getType();
		List<Country> countries = gson.fromJson(json, listType);
		return countries;
	}
	
}
