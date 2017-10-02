/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package eu.fayder.restcountries.v1.rest;

import eu.fayder.restcountries.rest.CountryServiceBase;
import eu.fayder.restcountries.v1.domain.Country;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class CountryService extends CountryServiceBase {

    private static final Logger LOG = Logger.getLogger(CountryService.class);

    private static List<Country> countries;

    private CountryService() {
        initialize();
    }

    private static class InstanceHolder {
        private static final CountryService INSTANCE = new CountryService();
    }

    public static CountryService getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public List<Country> getAll() {
        return countries;
    }

    public Country getByAlpha(String alpha) {
        return super.getByAlpha(alpha, countries);
    }

    @SuppressWarnings("unchecked")
    public List<Country> getByCodeList(String codeList) {
        return (List<Country>) super.getByCodeList(codeList, countries);
    }

    @SuppressWarnings("unchecked")
    public List<Country> getByName(String name, boolean isFullText) {
        return (List<Country>) super.getByName(name, isFullText, countries);

    }

    @SuppressWarnings("unchecked")
    public List<Country> getByCallingCode(String callingcode) {
        return (List<Country>) super.getByCallingCode(callingcode, countries);
    }

    @SuppressWarnings("unchecked")
    public List<Country> getByCapital(String capital) {
        return (List<Country>) super.getByCapital(capital, countries);
    }

    @SuppressWarnings("unchecked")
    public List<Country> getByRegion(String region) {
        return (List<Country>) super.getByRegion(region, countries);
    }

    @SuppressWarnings("unchecked")
    public List<Country> getBySubregion(String subregion) {
        return (List<Country>) super.getBySubregion(subregion, countries);
    }

    public List<Country> getByCurrency(String currency) {
        List<Country> result = new ArrayList<>();
        for (Country country : countries) {
            for (String curr : country.getCurrencies()) {
                if (curr.toLowerCase().equals(currency.toLowerCase())) {
                    result.add(country);
                }
            }
        }
        return result;
    }

    public List<Country> getByLanguage(String language) {
        List<Country> result = new ArrayList<Country>();
        for (Country country : countries) {
            for (String lang : country.getLanguages()) {
                if (lang.toLowerCase().equals(language.toLowerCase())) {
                    result.add(country);
                }
            }
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    private void initialize() {
        countries = (List<Country>) super.loadJson("countriesV1.json", Country.class);
    }
}
