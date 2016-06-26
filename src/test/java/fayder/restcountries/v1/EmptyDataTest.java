/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package fayder.restcountries.v1;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import fayder.restcountries.v1.domain.Country;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class EmptyDataTest {

	List<Country> countries;

	@Before
	public void before() throws IOException {
		InputStream is = this.getClass().getClassLoader()
				.getResourceAsStream("countriesV1.json");
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
	public void emptyBorders() throws Exception {
		System.out.println("- Empty Borders");
		for (Country c : countries) {
			if (c.getBorders() == null || c.getBorders().isEmpty()) {
				System.out.println(c.getName());
			}
		}
	}

	@Test
	public void emptyAreas() throws Exception {
		System.out.println("- Empty Areas");
		for (Country c : countries) {
			if (c.getArea() == null) {
				System.out.println(c.getName());
			}
		}
	}

	@Test
	public void emptyGini() throws Exception {
		System.out.println("- Empty Gini");
		for (Country c : countries) {
			if (c.getGini() == null) {
				System.out.println(c.getName());
			}
		}
	}
}
