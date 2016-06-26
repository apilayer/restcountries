/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package fayder.restcountries.v1.domain;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Country {
	
	private String name;
	
	@JsonProperty("topLevelDomain")
	private List<String> tld;
	
	@JsonProperty("alpha2Code")
	private String cca2;
	
	@JsonProperty("alpha3Code")
	private String cca3;
	
	@JsonProperty("currencies")
	private List<String> currency;
	
	@JsonProperty("callingCodes")
	private List<String> callingCode;
	
	private String capital;
	
	private List<String> altSpellings;
	
	private String relevance;
	
	private String region;
	
	private String subregion;
	
	@JsonProperty("languages")
	private List<String> languageCodes;
	
	private CountryTranslations translations;
	
	private Integer population;
	
	private List<Double> latlng;
	
	private String demonym;
	
	private Double area;

	private Double gini;

	private List<String> timezones;
	
	private List<String> borders;
	
	private String nativeName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getTld() {
		return tld;
	}

	public void setTld(List<String> tld) {
		this.tld = tld;
	}

	public String getCca2() {
		return cca2;
	}

	public void setCca2(String cca2) {
		this.cca2 = cca2;
	}

	public String getCca3() {
		return cca3;
	}

	public void setCca3(String cca3) {
		this.cca3 = cca3;
	}

	public List<String> getCallingCodes() {
		return callingCode;
	}

	public void setCallingCodes(List<String> countryCodes) {
		this.callingCode = countryCodes;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public List<String> getAltSpellings() {
		return altSpellings;
	}

	public void setAltSpellings(List<String> altSpellings) {
		this.altSpellings = altSpellings;
	}

	public String getRelevance() {
		return relevance;
	}

	public void setRelevance(String relevance) {
		this.relevance = relevance;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getSubregion() {
		return subregion;
	}

	public void setSubregion(String subregion) {
		this.subregion = subregion;
	}

	public List<String> getLanguageCodes() {
		return languageCodes;
	}

	public void setLanguageCodes(List<String> languageCodes) {
		this.languageCodes = languageCodes;
	}

	public CountryTranslations getTranslations() {
		return translations;
	}

	public void setTranslations(CountryTranslations translations) {
		this.translations = translations;
	}

	public Integer getPopulation() {
		return population;
	}

	public void setPopulation(Integer population) {
		this.population = population;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public Double getGini() {
		return gini;
	}

	public void setGini(Double gini) {
		this.gini = gini;
	}

	public List<String> getTimezones() {
		return timezones;
	}

	public void setTimezones(List<String> timezones) {
		this.timezones = timezones;
	}

	public List<Double> getLatlng() {
		return latlng;
	}

	public void setLatlng(List<Double> latlng) {
		this.latlng = latlng;
	}

	public String getDemonym() {
		return demonym;
	}

	public void setDemonym(String denonym) {
		this.demonym = denonym;
	}

	public List<String> getBorders() {
		return borders;
	}

	public void setBorders(List<String> borders) {
		this.borders = borders;
	}

	public List<String> getCurrency() {
		return currency;
	}

	public void setCurrency(List<String> currency) {
		this.currency = currency;
	}

	public String getNativeName() {
		return nativeName;
	}

	public void setNativeName(String nativeName) {
		this.nativeName = nativeName;
	}

}
