/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package vaeke.restcountries.v0;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import vaeke.restcountries.v0.domain.Country;

import com.eclipsesource.restfuse.HttpJUnitRunner;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

@RunWith(HttpJUnitRunner.class)
public class EmptyDataTest {
	
	List<Country> countries;
	
	@Before
	public void before() throws IOException {
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
	
	@Test
	public void emptyPopulation() {
		System.out.println("\nEmpty Population");
		for(Country c : countries) {
			if(c.getPopulation() == null)
				System.out.println(c.getName());
		}
	}
	
	@Test
	public void emptyLanguages() throws Exception {
		System.out.println("\nEmpty Languages");
		for(Country c : countries) {
			if(c.getLanguages() == null || c.getLanguages().isEmpty())
				System.out.println(c.getName());
		}
	}
}
