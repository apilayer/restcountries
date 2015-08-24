/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package vaeke.restcountries.v2.domain;

public class Language {

    private String twoLetterCode;
    private String threeLetterCode;
    private String name;

    public String getTwoLetterCode() {
        return twoLetterCode;
    }

    public void setTwoLetterCode(String code) {
        this.twoLetterCode = code;
    }

    public String getThreeLetterCode() {
        return threeLetterCode;
    }

    public void setThreeLetterCode(String threeLetterCode) {
        this.threeLetterCode = threeLetterCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
