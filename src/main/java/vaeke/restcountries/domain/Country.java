/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package vaeke.restcountries.domain;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

@JsonPropertyOrder(alphabetic = true)
public class Country {

	private String name;
	
	private String currency;
	
	@JsonProperty("topLevelDomain")
	private String tld;
	
	@JsonProperty("alpha2Code")
	private String cca2;
	
	@JsonProperty("alpha3Code")
	private String cca3;
	
	@JsonProperty("isoNumericCode")
	private String ccn3;
	
	@JsonProperty("callingcode")
	private String callingCode;
	
	private String capital;
	
	private String region;
	
	private String subregion;
	
	private String altSpellings;
	
	private String relevance;
	
	private String nationality;
	
	private List<Double> latlng;
	
	private List<String> languages;
	
	private CountryTranslations translations;
	
	private Integer population;
	
	private Double area;

	private Double gini;

	private List<String> timezones;
	
	private List<String> borders = new ArrayList();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getTld() {
		return tld;
	}

	public void setTld(String tld) {
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

	public String getCcn3() {
		return ccn3;
	}

	public void setCcn3(String ccn3) {
		this.ccn3 = ccn3;
	}

	public String getCallingCode() {
		return callingCode;
	}

	public void setCallingCode(String callingCode) {
		this.callingCode = callingCode;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
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

	public String getAltSpellings() {
		return altSpellings;
	}

	public void setAltSpellings(String altSpellings) {
		this.altSpellings = altSpellings;
	}

	public String getRelevance() {
		return relevance;
	}

	public void setRelevance(String relevance) {
		this.relevance = relevance;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public List<Double> getLatlng() {
		return latlng;
	}

	public void setLatlng(List<Double> latlng) {
		this.latlng = latlng;
	}

	public List<String> getLanguages() {
		return languages;
	}

	public void setLanguages(List<String> languages) {
		this.languages = languages;
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

	public List<String> getBorders() {
		return borders;
	}

	public void setBorders(List<String> borders) {
		this.borders = borders;
	}
}
	