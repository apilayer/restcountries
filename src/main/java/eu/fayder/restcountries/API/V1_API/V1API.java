package eu.fayder.restcountries.API.V1_API;
import eu.fayder.restcountries.v1.*;
import eu.fayder.restcountries.v1.domain.Country;
import eu.fayder.restcountries.v1.rest.CountryRest;
import eu.fayder.restcountries.v1.rest.CountryService;
import org.apache.log4j.net.SyslogAppender;

import java.util.List;

public class V1API {

   private static CountryService countryService;

    public static CountryService getInstance()
    {
        return CountryService.getInstance();
    }
    public V1API()
    {
        countryService = this.getInstance();
    }

    public List<Country> getAll()
    {
        return countryService.getAll();
    }

    public Country getByAlpha(String alpha)
    {
        return countryService.getByAlpha(alpha);
    }

    public List<Country> getByCodeList(String codeList)
    {
         return countryService.getByCodeList(codeList);
    }

    public List<Country> getByName(String name,boolean isFullText)
    {
        return countryService.getByName(name,isFullText);
    }

    public List<Country> getByCallingCode(String callingCode)
    {
        return countryService.getByCallingCode(callingCode);
    }

    public List<Country> getByCapital(String capital)
    {
        return countryService.getByCapital(capital);
    }

    public List<Country> getByRegion(String region)
    {
        return countryService.getByRegion(region);
    }

    public List<Country> getBySubRegion(String subRegion)
    {
        return countryService.getBySubregion(subRegion);
    }

    public List<Country> getByCurrency(String currency)
    {
        return countryService.getByCurrency(currency);
    }

    public List<Country> getByLanguage(String language)
    {
        return countryService.getByLanguage(language);
    }

    public static void main(String[] args) {
         V1API countryService= new V1API();
        Country alpha=countryService.getByAlpha("CO");
        System.out.println(alpha.getName());
        List<Country> countries= countryService.getByName("Russian Federation",true);
        System.out.println(countries.get(0).getName());
        List<Country> countries1= countryService.getAll();
        System.out.println(countries1.size());
        for(Country country:countries1)
        {
            System.out.println(country.getName());
        }

    }
}
