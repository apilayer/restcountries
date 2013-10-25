/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package vaeke.restcountries.rest;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import vaeke.restcountries.domain.Country;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

@Provider
@Path("rest")
@Produces(MediaType.APPLICATION_JSON)
public class CountryRest {
	
	private static final Logger LOG = Logger.getLogger(CountryRest.class);
	private static List<Country> countries;

	@GET
	public Object getCountries() {
		LOG.info("Getting all");
		try {
			return getAll();
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("500: Internal Server Error").build(); 
		}
	}
	
	@GET
	@Path("alpha2/{alpha2code}")
	public Object getByAlpha2(@PathParam("alpha2code") String alpha2) {
		LOG.info("Getting by alpha2 " + alpha2);
		try {
			List<Country> countries = getAll();
			for(Country country : countries) {
				if (country.getCca2().toLowerCase().equals(alpha2.toLowerCase())) {
					return country;
				}
			}
			return Response.status(Status.NOT_FOUND).entity("404: Not Found").build();
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("500: Internal Server Error").build(); 
		}
		
	}
	
	@GET
	@Path("alpha3/{alpha3code}")
	public Object getByAlpha3(@PathParam("alpha3code") String alpha3) {
		LOG.info("Getting by alpha3 " + alpha3);
		try {
			List<Country> countries = getAll();
			for(Country country : countries) {
				if (country.getCca3().toLowerCase().equals(alpha3.toLowerCase())) {
					return country;
				}
			}
			return Response.status(Status.NOT_FOUND).entity("404: Not Found").build();
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("500: Internal Server Error").build(); 
		}
	}
	
	@GET
	@Path("currency/{currency}")
	public Object getByCurrency(@PathParam("currency") String currency) {
		LOG.info("Getting by currency " + currency);
		try {
			List<Country> countries = getAll();
			List<Country> result = new ArrayList<Country>();
			for(Country country : countries) {
				if(country.getCurrency().toLowerCase().contains(currency.toLowerCase())) {
					result.add(country);
				}
			}
			if (!result.isEmpty()) {
				return result;
			} else {
				return Response.status(Status.NOT_FOUND).entity("404: Not Found").build();
			}
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("500: Internal Server Error").build(); 
		}
	}
	
	@GET
	@Path("name/{name}")
	public Object getByName(@PathParam("name") String name) {
		LOG.info("Getting by name " + name);
		try {
			List<Country> countries = getAll();
			List<Country> result = new ArrayList<Country>();
			for(Country country : countries) {
				if(country.getName().toLowerCase().contains(name.toLowerCase())) {
					result.add(country);
				}
			}
			if (!result.isEmpty()) {
				return result;
			} else {
				return Response.status(Status.NOT_FOUND).entity("404: Not Found").build();
			}
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("500: Internal Server Error").build(); 
		}
	}
	
	@GET
	@Path("callingcode/{callingcode}")
	public Object getByCallingCode(@PathParam("callingcode") String callingcode) {
		LOG.info("Getting by calling code " + callingcode);
		try {
			List<Country> countries = getAll();
			for(Country country : countries) {
				if(country.getCallingcode().equals(callingcode))
					return country;
			}
			return Response.status(Status.NOT_FOUND).entity("404: Not Found").build();
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("500: Internal Server Error").build(); 
		}
	}
	
	@GET
	@Path("capital/{capital}")
	public Object getByCapital(@PathParam("capital") String capital) {
		LOG.info("Getting by capital " + capital);
		try {
			List<Country> countries = getAll();
			for(Country country : countries) {
				if(removeDiacriticalMarks(country.getCapital().toLowerCase()).equals(removeDiacriticalMarks(capital.toLowerCase()))) {
					return country;
				}
			}
			return Response.status(Status.NOT_FOUND).entity("404: Not Found").build();
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("500: Internal Server Error").build(); 
		}
	}
	
	private String removeDiacriticalMarks(String string) {
	    return Normalizer.normalize(string, Form.NFD)
	        .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	}
	
	private List<Country> getAll() throws IOException {
		if(countries != null) return countries; 
		
		LOG.debug("Loading JSON Database");
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("countries.json");
		Gson gson = new Gson();
		JsonReader reader = new JsonReader(new InputStreamReader(is, "UTF-8"));
		countries = new ArrayList<Country>();
		reader.beginArray();
		while(reader.hasNext()) {
			Country country = gson.fromJson(reader, Country.class);
			countries.add(country);
		}
		reader.endArray();
        reader.close();
        return countries;
	}
}
