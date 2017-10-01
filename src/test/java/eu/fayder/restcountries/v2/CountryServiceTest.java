package eu.fayder.restcountries.v2;

import eu.fayder.restcountries.v2.domain.*;
import eu.fayder.restcountries.v2.rest.CountryService;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CountryServiceTest {

    @Test
    public void getAll() throws Exception {
        List<Country> countries = CountryService.getInstance().getAll();
        Assert.assertFalse(countries.isEmpty());
        System.out.println("TOTAL Countries " + countries.size());
    }

    @Test
    public void getByAlpha2() throws Exception {
        Country country = CountryService.getInstance().getByAlpha("CO");
        Assert.assertNotNull(country);
        Assert.assertEquals("CO", country.getAlpha2Code());
    }

    @Test
    public void getByAlpha3() throws Exception {
        Country country = CountryService.getInstance().getByAlpha("COL");
        Assert.assertNotNull(country);
        Assert.assertEquals("COL", country.getAlpha3Code());
    }

    @Test
    public void getByCodeList() throws Exception {
        List<Country> countries = CountryService.getInstance().getByCodeList("CO;NOR;EE");
        Assert.assertNotNull(countries);
        Assert.assertFalse(countries.isEmpty());
        Assert.assertEquals(3, countries.size());

        for (Country country : countries) {
            Assert.assertTrue(country.getAlpha2Code().equals("CO") || country.getAlpha2Code().equals("NO") || country.getAlpha2Code().equals("EE"));
        }
    }

    @Test
    public void getByCurrency() throws Exception {
        List<Country> countries = CountryService.getInstance().getByCurrency("EUR");
        Assert.assertNotNull(countries);
        Assert.assertFalse(countries.isEmpty());
        for (Country country : countries) {
            for (Currency currency : country.getCurrencies()) {
                currency.getCode().equals("EUR");
            }
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
        Assert.assertEquals("Iran (Islamic Republic of)", countries.get(0).getName());

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
        Assert.assertEquals("Russian Federation", countries.get(0).getName());
    }

    @Test
    public void getByNameFullTextNotFound() throws Exception {
        List<Country> countries = CountryService.getInstance().getByName("Russian Fed", true);
        Assert.assertNotNull(countries);
        Assert.assertTrue(countries.isEmpty());
    }

    @Test
    public void getByCallingCode() throws Exception {
        List<Country> countries = CountryService.getInstance().getByCallingCode("57");
        Assert.assertNotNull(countries);
        Assert.assertFalse(countries.isEmpty());
        Assert.assertEquals(1, countries.size());
        Assert.assertEquals("CO", countries.get(0).getAlpha2Code());
    }

    @Test
    public void getByCapital() throws Exception {
        List<Country> countries = CountryService.getInstance().getByCapital("Tallinn");
        Assert.assertNotNull(countries);
        Assert.assertFalse(countries.isEmpty());
        Assert.assertEquals(1, countries.size());
        Assert.assertEquals("EE", countries.get(0).getAlpha2Code());
        Assert.assertEquals("Eesti", countries.get(0).getNativeName());
    }

    @Test
    public void getByRegion() throws Exception {
        List<Country> countries = CountryService.getInstance().getByRegion("Europe");
        Assert.assertNotNull(countries);
        Assert.assertFalse(countries.isEmpty());
        for (Country country : countries) {
            Assert.assertEquals("Europe", country.getRegion());
        }
    }

    @Test
    public void getByLanguageCode() throws Exception {
        List<Country> countries = CountryService.getInstance().getByLanguage("es");
        Assert.assertNotNull(countries);
        Assert.assertFalse(countries.isEmpty());
        for (Country country : countries) {
            for (Language language : country.getLanguages()) {
                if (language.getIso639_1().equals("es")) {
                    return;
                }
            }
        }
        Assert.fail();
    }

    @Test
    public void getByDemonym() throws Exception {
        List<Country> countries = CountryService.getInstance().getByDemonym("french");
        Assert.assertNotNull(countries);
        Assert.assertFalse(countries.isEmpty());
        for (Country country : countries) {
            Assert.assertEquals("french", country.getDemonym().toLowerCase());
        }
    }

    @Test
    public void getByRegionalBloc() throws Exception {
        List<Country> countries = CountryService.getInstance().getByRegionalBloc("eu");
        Assert.assertNotNull(countries);
        Assert.assertFalse(countries.isEmpty());
        for (Country country : countries) {
            for (RegionalBloc regionalBloc : country.getRegionalBlocs()) {
                if (regionalBloc.getAcronym().toLowerCase().equals("eu")) {
                    return;
                }
            }
        }
        Assert.fail();
    }

    @Test
    public void translations() throws Exception {
        Country country = CountryService.getInstance().getByAlpha("COL");
        Assert.assertNotNull(country);
        Translations translations = country.getTranslations();
        Assert.assertEquals("Kolumbien", translations.getDe());
        Assert.assertEquals("Colombia", translations.getEs());
        Assert.assertEquals("Colombie", translations.getFr());
        Assert.assertEquals("コロンビア", translations.getJa());
        Assert.assertEquals("Colombia", translations.getIt());
        Assert.assertEquals("Colômbia", translations.getBr());
        Assert.assertEquals("Colômbia", translations.getPt());
    }
}
