package vaeke.restcountries.rest.v0;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import vaeke.restcountries.v1.domain.Country;
import vaeke.restcountries.v1.rest.CountryService;

import com.eclipsesource.restfuse.HttpJUnitRunner;

@RunWith(HttpJUnitRunner.class)
public class CountryServiceTest {
	
	@Test
	public void getAll() throws Exception {
		List<Country> countries = CountryService.getInstance().getAll();
		Assert.assertNotNull(countries);
		Assert.assertFalse(countries.isEmpty());
		Assert.assertTrue(countries.size() >= 250);
	}

}
