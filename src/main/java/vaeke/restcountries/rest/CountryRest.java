/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package vaeke.restcountries.rest;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import vaeke.restcountries.domain.Country;
import vaeke.restcountries.domain.ResponseEntity;

import com.google.gson.Gson;

@Provider
@Path("rest")
@Produces(MediaType.APPLICATION_JSON)
public class CountryRest {
	
	private static final Logger LOG = Logger.getLogger(CountryRest.class);

	@GET
	public Object getCountries() {
		LOG.info("Getting all");
		try {
			return CountryService.getInstance().getAll();
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
			return getResponse(Status.INTERNAL_SERVER_ERROR); 
		}
	}
	
	@GET
	@Path("alpha/{alphacode}")
	public Object getByAlpha(@PathParam("alphacode") String alpha) {
		LOG.info("Getting by alpha " + alpha);
		try {
			Country country = CountryService.getInstance().getByAlpha(alpha);
			if(country != null) {
				return country;
			}
			return getResponse(Status.NOT_FOUND);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
			return getResponse(Status.INTERNAL_SERVER_ERROR); 
		}
	}
	
	@GET
	@Path("alpha2/{alpha2code}")
	@Deprecated
	public Object getByAlpha2(@PathParam("alpha2code") String alpha2) {
		return this.getByAlpha(alpha2);
	}
	
	@GET
	@Path("alpha3/{alpha3code}")
	@Deprecated
	public Object getByAlpha3(@PathParam("alpha3code") String alpha3) {
		return this.getByAlpha(alpha3);
	}
	
	@GET
	@Path("multiplecountries/")
	public Object getMultipleCountries(@QueryParam("countrylist") String alpha) {
		LOG.info("Getting by list " + alpha);
		try {
			List<Country> countries = CountryService.getInstance().getByCodeList(alpha);
			if (!countries.isEmpty()) {
				return countries;
			}
			return getResponse(Status.NOT_FOUND);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
			return getResponse(Status.INTERNAL_SERVER_ERROR); 
		}
	}
	
	@GET
	@Path("currency/{currency}")
	public Object getByCurrency(@PathParam("currency") String currency) {
		LOG.info("Getting by currency " + currency);
		try {
			List<Country> countries = CountryService.getInstance().getByCurrency(currency);
			if (!countries.isEmpty()) {
				return countries;
			}
			return getResponse(Status.NOT_FOUND);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
			return getResponse(Status.INTERNAL_SERVER_ERROR); 
		}
	}
	
	@GET
	@Path("name/{name}")
	public Object getByName(@PathParam("name") String name) {
		LOG.info("Getting by name " + name);
		try {
			List<Country> countries = CountryService.getInstance().getByName(name);
			if (!countries.isEmpty()) {
				return countries;
			} 
			return getResponse(Status.NOT_FOUND);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
			return getResponse(Status.INTERNAL_SERVER_ERROR); 
		}
	}
	
	@GET
	@Path("callingcode/{callingcode}")
	public Object getByCallingCode(@PathParam("callingcode") String callingcode) {
		LOG.info("Getting by calling code " + callingcode);
		try {
			List<Country> countries = CountryService.getInstance().getByCallingcode(callingcode);
			if (!countries.isEmpty()) {
				return countries;
			} 
			return getResponse(Status.NOT_FOUND);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
			return getResponse(Status.INTERNAL_SERVER_ERROR); 
		}
	}
	
	@GET
	@Path("capital/{capital}")
	public Object getByCapital(@PathParam("capital") String capital) {
		LOG.info("Getting by capital " + capital);
		try {
			List<Country> countries = CountryService.getInstance().getByCapital(capital);
			if(!countries.isEmpty()) {
				return countries;
			}
			return getResponse(Status.NOT_FOUND);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
			return getResponse(Status.INTERNAL_SERVER_ERROR); 
		}
	}
	
	@GET
	@Path("region/{region}")
	public Object getByRegion(@PathParam("region") String region) {
		LOG.info("Getting by region " + region);
		try {
			List<Country> countries = CountryService.getInstance().getByRegion(region);
			if(!countries.isEmpty()) {
				return countries;
			}
			return getResponse(Status.NOT_FOUND);
		} catch(IOException e) {
			LOG.error(e.getMessage(), e);
			return getResponse(Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GET
	@Path("subregion/{subregion}")
	public Object getBySubregion(@PathParam("subregion") String subregion) {
		LOG.info("Getting by region " + subregion);
		try {
			List<Country> countries = CountryService.getInstance().getBySubregion(subregion);
			if(!countries.isEmpty()) {
				return countries;
			}
			return getResponse(Status.NOT_FOUND);
		} catch(IOException e) {
			LOG.error(e.getMessage(), e);
			return getResponse(Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GET
	@Path("lang/{lang}")
	public Object getByLanguage(@PathParam("lang") String language) {
		LOG.info("Getting by language " + language);
		try {
			List<Country> countries = CountryService.getInstance().getByLanguage(language);
			if(!countries.isEmpty()) {
				return countries;
			}
			return getResponse(Status.NOT_FOUND);
		} catch(IOException e) {
			LOG.error(e.getMessage(), e);
			return getResponse(Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	private Response getResponse(Status status) {
		Gson gson = new Gson();
		return Response
				.status(status)
				.entity(gson.toJson(new ResponseEntity(status.getStatusCode(),
						status.getReasonPhrase()))).build();
	}
}
