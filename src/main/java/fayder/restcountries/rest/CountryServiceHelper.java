/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package fayder.restcountries.rest;

import fayder.restcountries.domain.BaseCountry;
import fayder.restcountries.domain.ICountryRestSymbols;
import org.apache.log4j.Logger;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountryServiceHelper {

    private static final Logger LOG = Logger.getLogger(CountryServiceHelper.class);

    public static <T extends BaseCountry> T getByAlpha(String alpha, List<T> countries) {
        int alphaLength = alpha.length();
        for (T country : countries) {
            if (alphaLength == 2) {
                if (country.getAlpha2Code().toLowerCase().equals(alpha.toLowerCase())) {
                    return country;
                }
            } else if (alphaLength == 3) {
                if (country.getAlpha3Code().toLowerCase().equals(alpha.toLowerCase())) {
                    return country;
                }
            }
        }
        return null;
    }

    public static List<? extends BaseCountry> getByCodeList(String codeList, List<? extends BaseCountry> countries) {
        List<BaseCountry> result = new ArrayList<>();
        if(codeList == null) return result;

        List<String> codes = Arrays.asList(codeList.split(ICountryRestSymbols.SEMICOLON));
        for(String code : codes) {
            BaseCountry country = getByAlpha(code, countries);
            if(!result.contains(country))
                result.add(country);
        }
        return result;
    }

    public static List<? extends BaseCountry> getByName(String name, boolean fullText, List<? extends BaseCountry> countries) {
        if(fullText) {
            return fulltextSearch(name, countries);
        } else {
            return substringSearch(name, countries);
        }
    }

    public static List<? extends BaseCountry> getByCallingCode(String callingCode, List<? extends BaseCountry> countries) {
        List<BaseCountry> result = new ArrayList<>();
        for(BaseCountry country : countries) {
            for(String c : country.getCallingCodes()) {
                if(c.equals(callingCode))
                    result.add(country);
            }
        }
        return result;
    }

    public static List<? extends BaseCountry> getByCapital(String capital, List<? extends BaseCountry> countries) {
        List<BaseCountry> result = new ArrayList<>();
        for(BaseCountry country : countries) {
            if(normalize(country.getCapital().toLowerCase()).contains(normalize(capital.toLowerCase()))) {
                result.add(country);
            }
        }
        return result;
    }

    public static List<? extends BaseCountry> getByRegion(String region, List<? extends BaseCountry> countries) {
        List<BaseCountry> result = new ArrayList<>();
        for(BaseCountry country : countries) {
            if(country.getRegion().toLowerCase().equals(region.toLowerCase())) {
                result.add(country);
            }
        }
        return result;
    }

    public static List<? extends BaseCountry> getBySubregion(String subregion, List<? extends BaseCountry> countries) {
        List<BaseCountry> result = new ArrayList<>();
        for(BaseCountry country : countries) {
            if(country.getSubregion().toLowerCase().equals(subregion.toLowerCase())) {
                result.add(country);
            }
        }
        return result;
    }

    private static List<? extends BaseCountry> fulltextSearch(String name, List<? extends BaseCountry> countries) {
        // Using 2 different 'for' loops to give priority to 'name' matches over alternative spellings
        List<BaseCountry> result = new ArrayList<>();
        for (BaseCountry country : countries) {
            if (normalize(country.getName().toLowerCase()).equals(normalize(name.toLowerCase()))) {
                result.add(country);
            }
        }
        for (BaseCountry country : countries) {
            for (String alternative : country.getAltSpellings()) {
                if (normalize(alternative.toLowerCase()).equals(normalize(name.toLowerCase()))
                        && !result.contains(country)) {
                    result.add(country);
                }
            }
        }
        return result;
    }

    private static List<? extends BaseCountry> substringSearch(String name, List<? extends BaseCountry> countries) {
        // Using 2 different 'for' loops to give priority to 'name' matches over alternative spellings
        List<BaseCountry> result = new ArrayList<>();
        for(BaseCountry country : countries) {
            if(normalize(country.getName().toLowerCase()).contains(normalize(name.toLowerCase()))) {
                result.add(country);
            }
        }
        for(BaseCountry country : countries) {
            for (String alternative : country.getAltSpellings()) {
                if( normalize(alternative.toLowerCase()).contains(normalize(name.toLowerCase()))
                        && !result.contains(country) ) {
                    result.add(country);
                }
            }
        }
        return result;
    }

    private static String normalize(String string) {
        return Normalizer.normalize(string, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }
}
