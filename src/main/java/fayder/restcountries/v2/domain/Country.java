/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package fayder.restcountries.v2.domain;

import fayder.restcountries.domain.BaseCountry;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.List;

public class Country extends BaseCountry {

    private List<Currency> currencies;
    private List<Language> languages;
    private Translations translations;
    private String flag;
    private List<RegionalBloc> regionalBlocs;

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public Translations getTranslations() {
        return translations;
    }

    public String getFlag() {
        return flag;
    }

    public List<RegionalBloc> getRegionalBlocs() {
        return regionalBlocs;
    }


}
