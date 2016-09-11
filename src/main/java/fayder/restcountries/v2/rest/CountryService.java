/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package fayder.restcountries.v2.rest;

import fayder.restcountries.rest.CountryServiceHelper;
import fayder.restcountries.v2.domain.Country;
import fayder.restcountries.v2.domain.Currency;
import fayder.restcountries.v2.domain.Language;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class CountryService {

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
        return CountryServiceHelper.getByAlpha(alpha, countries);
    }

    @SuppressWarnings("unchecked")
    public List<Country> getByCodeList(String codeList) {
        return (List<Country>) CountryServiceHelper.getByCodeList(codeList, countries);
    }

    @SuppressWarnings("unchecked")
    public List<Country> getByName(String name, boolean isFullText) {
        return (List<Country>) CountryServiceHelper.getByName(name, isFullText, countries);
    }

    @SuppressWarnings("unchecked")
    public List<Country> getByCallingCode(String callingcode) {
        return (List<Country>) CountryServiceHelper.getByCallingCode(callingcode, countries);
    }

    @SuppressWarnings("unchecked")
    public List<Country> getByCapital(String capital) {
        return (List<Country>) CountryServiceHelper.getByCapital(capital, countries);
    }

    @SuppressWarnings("unchecked")
    public List<Country> getByRegion(String region) {
        return (List<Country>) CountryServiceHelper.getByRegion(region, countries);
    }

    @SuppressWarnings("unchecked")
    public List<Country> getBySubRegion(String subregion) {
        return (List<Country>) CountryServiceHelper.getBySubregion(subregion, countries);
    }

    public List<Country> getByCurrency(String currency) {
        List<Country> result = new ArrayList<Country>();
        for (Country country : countries) {
            for (Currency curr : country.getCurrencies()) {
                if (curr.getCode() != null && currency.toLowerCase().equals(curr.getCode().toLowerCase())) {
                    result.add(country);
                }
            }
        }
        return result;
    }

    public List<Country> getByLanguage(String language) {
        List<Country> result = new ArrayList<Country>();
        if (language.length() == 2) {
            for (Country country : countries) {
                for (Language lang : country.getLanguages()) {
                    if (language.toLowerCase().equals(lang.getIso639_1())) {
                        result.add(country);
                    }
                }
            }
        } else if (language.length() == 3) {
            for (Country country : countries) {
                for (Language lang : country.getLanguages()) {
                    if (language.toLowerCase().equals(lang.getIso639_2())) {
                        result.add(country);
                    }
                }
            }
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    private void initialize() {
        countries = (List<Country>) CountryServiceHelper.loadJson("countriesV2.json", Country.class);
    }
}
