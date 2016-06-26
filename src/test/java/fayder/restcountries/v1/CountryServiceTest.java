/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package fayder.restcountries.v1;

import java.util.List;

import fayder.restcountries.v1.domain.Country;
import org.junit.Assert;
import org.junit.Test;

import fayder.restcountries.v1.rest.CountryService;

public class CountryServiceTest {
	
	@Test
	public void singletonTest() throws Exception {
		for(int i = 0; i < 100; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					CountryService.getInstance();
				}
			}).run();
		}
	}
	
	@Test
	public void getAll() throws Exception {
		List<Country> countries = CountryService.getInstance().getAll();
		Assert.assertNotNull(countries);
		Assert.assertFalse(countries.isEmpty());
		System.out.println("TOTAL Countries " + countries.size());
	}
	
	@Test
	public void getByAlpha2() throws Exception {
		Country country = CountryService.getInstance().getByAlpha("CO");
		Assert.assertNotNull(country);
		Assert.assertEquals("CO", country.getCca2());
	}
	
	@Test
	public void getByAlpha3() throws Exception {
		Country country = CountryService.getInstance().getByAlpha("COL");
		Assert.assertNotNull(country);
		Assert.assertEquals("COL", country.getCca3());
	}
	
	@Test
	public void getByCodeList() throws Exception {
		List<Country> countries = CountryService.getInstance().getByCodeList("CO;NOR;EE");
		Assert.assertNotNull(countries);
		Assert.assertFalse(countries.isEmpty());
		Assert.assertEquals(3, countries.size());
		
		for(Country country : countries) {
			Assert.assertTrue(country.getCca2().equals("CO") || country.getCca2().equals("NO") || country.getCca2().equals("EE"));
		}
	}
	
	@Test
	public void getByCurrency() throws Exception {
		List<Country> countries = CountryService.getInstance().getByCurrency("EUR");
		Assert.assertNotNull(countries);
		Assert.assertFalse(countries.isEmpty());
		for(Country country : countries) {
			Assert.assertTrue(country.getCurrency().contains("EUR"));
		}
	}
	
	@Test
	public void getByName() throws Exception {
		List<Country> countries = CountryService.getInstance().getByName("Norway", false);
		Assert.assertNotNull(countries);
		Assert.assertFalse(countries.isEmpty());
		Assert.assertEquals("Norway", countries.get(0).getName());
	}
	
	@Test
	public void getByNamePriority() throws Exception {
		List<Country> countries = CountryService.getInstance().getByName("Iran", false);
		Assert.assertNotNull(countries);
		Assert.assertFalse(countries.isEmpty());
		Assert.assertEquals("Iran", countries.get(0).getName());
		
		countries = CountryService.getInstance().getByName("United", false);
		Assert.assertNotNull(countries);
		Assert.assertFalse(countries.isEmpty());
		Assert.assertEquals("United States Minor Outlying Islands", countries.get(0).getName());
	}
	
	@Test
	public void getByNameAlt() throws Exception {
		List<Country> countries = CountryService.getInstance().getByName("Norge", false);
		Assert.assertNotNull(countries);
		Assert.assertFalse(countries.isEmpty());
		Assert.assertEquals("Norway", countries.get(0).getName());
	}
	
	@Test
	public void getByNameFullText() throws Exception {
		List<Country> countries = CountryService.getInstance().getByName("Russian Federation", true);
		Assert.assertNotNull(countries);
		Assert.assertFalse(countries.isEmpty());
		Assert.assertEquals("Russia", countries.get(0).getName());
	}
	
	@Test
	public void getByNameFullTextNotFound() throws Exception {
		List<Country> countries = CountryService.getInstance().getByName("Russian Fed", true);
		Assert.assertNotNull(countries);
		Assert.assertTrue(countries.isEmpty());
	}
	
	@Test
	public void getByCallingCode() throws Exception {
		List<Country> countries = CountryService.getInstance().getByCallingcode("57");
		Assert.assertNotNull(countries);
		Assert.assertFalse(countries.isEmpty());
		Assert.assertEquals(1, countries.size());
		Assert.assertEquals("CO", countries.get(0).getCca2());
	}
	
	@Test
	public void getByCapital() throws Exception {
		List<Country> countries = CountryService.getInstance().getByCapital("Tallinn");
		Assert.assertNotNull(countries);
		Assert.assertFalse(countries.isEmpty());
		Assert.assertEquals(1, countries.size());
		Assert.assertEquals("EE", countries.get(0).getCca2());
		Assert.assertEquals("Eesti", countries.get(0).getNativeName());
	}
	
	@Test
	public void getByRegion() throws Exception {
		List<Country> countries = CountryService.getInstance().getByRegion("Europe");
		Assert.assertNotNull(countries);
		Assert.assertFalse(countries.isEmpty());
		for(Country country : countries) {
			Assert.assertEquals("Europe", country.getRegion());
		}
	}
	
	@Test
	public void getBySubregion() throws Exception {
		List<Country> countries = CountryService.getInstance().getBySubregion("Northern Europe");
		Assert.assertNotNull(countries);
		Assert.assertFalse(countries.isEmpty());
		for(Country country : countries) {
			Assert.assertEquals("Northern Europe", country.getSubregion());
		}
	}
	
	@Test
	public void getByLanguageCode() throws Exception {
		List<Country> countries = CountryService.getInstance().getByLanguage("en");
		Assert.assertNotNull(countries);
		Assert.assertFalse(countries.isEmpty());
		for(Country country : countries) {
			Assert.assertTrue(country.getLanguageCodes().contains("en"));
		}
	}

}
