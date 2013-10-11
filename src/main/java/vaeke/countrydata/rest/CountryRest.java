/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package vaeke.countrydata.rest;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

import vaeke.countrydata.domain.Country;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

@Provider
@Path("rest")
@Produces(MediaType.APPLICATION_JSON)
public class CountryRest {

	@GET
	public Object getCountries() {
		try {
			return getAll();
		} catch (IOException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build(); 
		}
	}
	
	@GET
	@Path("alpha2/{alpha2code}")
	public Object getByAlpha2(@PathParam("alpha2code") String alpha2) {
		
		try {
			List<Country> countries = getAll();
			for(Country country : countries) {
				if (country.getCca2().toLowerCase().equals(alpha2.toLowerCase())) {
					return country;
				}
			}
		} catch (IOException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build(); 
		}
		return Response.status(Status.NOT_FOUND).build();
	}
	
	@GET
	@Path("alpha3/{alpha3code}")
	public Object getByAlpha3(@PathParam("alpha3code") String alpha3) {
		try {
			List<Country> countries = getAll();
			for(Country country : countries) {
				if (country.getCca3().toLowerCase().equals(alpha3.toLowerCase())) {
					return country;
				}
			}
		} catch (IOException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build(); 
		}
		return Response.status(Status.NOT_FOUND).build();
	}
	
	@GET
	@Path("currency/{currency}")
	public Object getByCurrency(@PathParam("currency") String currency) {
		try {
			List<Country> countries = getAll();
			List<Country> result = new ArrayList<Country>();
			for(Country country : countries) {
				if(country.getCurrency().toLowerCase().contains(currency.toLowerCase())) {
					result.add(country);
				}
			}
			return result;
		} catch (IOException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build(); 
		}
	}
	
	@GET
	@Path("name/{name}")
	public Object getByName(@PathParam("name") String name) {
		try {
			List<Country> countries = getAll();
			List<Country> result = new ArrayList<Country>();
			for(Country country : countries) {
				if(country.getName().toLowerCase().contains(name.toLowerCase())) {
					result.add(country);
				}
			}
			return result;
		} catch (IOException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build(); 
		}
	}
	
	@GET
	@Path("callingcode/{callingcode}")
	public Object getByCallingCode(@PathParam("callingcode") String callingcode) {
		try {
			List<Country> countries = getAll();
			for(Country country : countries) {
				if(country.getCallingcode().equals(callingcode))
					return country;
			}
			
		} catch (IOException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build(); 
		}
		return Response.status(Status.NOT_FOUND).build();
	}
	
	private List<Country> getAll() throws IOException {
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("countries.json");
		Gson gson = new Gson();
		JsonReader reader = new JsonReader(new InputStreamReader(is, "UTF-8"));
		List<Country> list = new ArrayList<Country>();
		reader.beginArray();
		while(reader.hasNext()) {
			Country country = gson.fromJson(reader, Country.class);
			list.add(country);
		}
		reader.endArray();
        reader.close();
        return list;
	}
}
