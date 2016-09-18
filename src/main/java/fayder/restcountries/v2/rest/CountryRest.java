/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package fayder.restcountries.v2.rest;

import com.google.gson.*;
import fayder.restcountries.domain.ICountryRestSymbols;
import fayder.restcountries.v2.domain.Country;
import org.apache.log4j.Logger;
import fayder.restcountries.domain.ResponseEntity;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.codehaus.jackson.map.ser.FilterProvider;
import org.codehaus.jackson.map.ser.impl.SimpleBeanPropertyFilter;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Provider
@Path("{a:rest/v2-alpha|rest/v2}")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class CountryRest {

    private static final Logger LOG = Logger.getLogger(CountryRest.class);

    @GET
    @Path("all")
    public Object getAllCountries() {
        return this.getCountries();
    }

    @GET
    public Object getCountries() {
        LOG.info("Getting all");
        return CountryService.getInstance().getAll();
    }

    @GET
    @Path("alpha/{alphacode}")
    public Object getByAlpha(@PathParam("alphacode") String alpha, @QueryParam("exclude") String exclude) {
        LOG.info("Getting by alpha " + alpha);
        Country country = CountryService.getInstance().getByAlpha(alpha);
        if (country != null) {
            return parsedCountry(country, exclude);
        }
        return getResponse(Response.Status.NOT_FOUND);
    }

    @GET
    @Path("alpha/")
    public Object getByAlphaList(@QueryParam("codes") String codes, @QueryParam("exclude") String exclude) {
        LOG.info("Getting by list " + codes);
        try {
            List<Country> countries = CountryService.getInstance().getByCodeList(codes);
            if (!countries.isEmpty()) {
                return parsedCountries(countries, exclude);
            }
            return getResponse(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return getResponse(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("currency/{currency}")
    public Object getByCurrency(@PathParam("currency") String currency, @QueryParam("exclude") String exclude) {
        LOG.info("Getting by currency " + currency);
        try {
            List<Country> countries = CountryService.getInstance().getByCurrency(currency);
            if (!countries.isEmpty()) {
                return parsedCountries(countries, exclude);
            }
            return getResponse(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return getResponse(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("name/{name}")
    public Object getByName(@PathParam("name") String name, @QueryParam("fullText") boolean fullText, @QueryParam("exclude") String exclude) {
        LOG.info("Getting by name " + name);
        try {
            List<Country> countries = CountryService.getInstance().getByName(name, fullText);
            if (!countries.isEmpty()) {
                return parsedCountries(countries, exclude);
            }
            return getResponse(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return getResponse(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("callingcode/{callingcode}")
    public Object getByCallingCode(@PathParam("callingcode") String callingcode, @QueryParam("exclude") String exclude) {
        LOG.info("Getting by calling code " + callingcode);
        try {
            List<Country> countries = CountryService.getInstance().getByCallingCode(callingcode);
            if (!countries.isEmpty()) {
                return parsedCountries(countries, exclude);
            }
            return getResponse(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return getResponse(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("capital/{capital}")
    public Object getByCapital(@PathParam("capital") String capital, @QueryParam("exclude") String exclude) {
        LOG.info("Getting by capital " + capital);
        try {
            List<Country> countries = CountryService.getInstance().getByCapital(capital);
            if (!countries.isEmpty()) {
                return parsedCountries(countries, exclude);
            }
            return getResponse(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return getResponse(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("region/{region}")
    public Object getByRegion(@PathParam("region") String region, @QueryParam("exclude") String exclude) {
        LOG.info("Getting by region " + region);
        try {
            List<Country> countries = CountryService.getInstance().getByRegion(region);
            if (!countries.isEmpty()) {
                return parsedCountries(countries, exclude);
            }
            return getResponse(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return getResponse(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("subregion/{subregion}")
    public Object getBySubRegion(@PathParam("subregion") String subregion, @QueryParam("exclude") String exclude) {
        LOG.info("Getting by sub region " + subregion);
        try {
            List<Country> countries = CountryService.getInstance().getBySubRegion(subregion);
            if (!countries.isEmpty()) {
                return parsedCountries(countries, exclude);
            }
            return getResponse(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return getResponse(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("lang/{lang}")
    public Object getByLanguage(@PathParam("lang") String language, @QueryParam("exclude") String exclude) {
        LOG.info("Getting by language " + language);
        try {
            List<Country> countries = CountryService.getInstance().getByLanguage(language);
            if (!countries.isEmpty()) {
                return parsedCountries(countries, exclude);
            }
            return getResponse(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return getResponse(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @POST
    public Object doPOST() {
        LOG.info("Handling POST Request");
        return getResponse(Response.Status.METHOD_NOT_ALLOWED);
    }

    private Response getResponse(Response.Status status) {
        Gson gson = new Gson();
        return Response
                .status(status)
                .entity(gson.toJson(new ResponseEntity(status.getStatusCode(),
                        status.getReasonPhrase()))).build();
    }

    private Object parsedCountry(Country country, String excludedFields) {
        if (excludedFields == null || excludedFields.isEmpty()) {
            return country;
        } else {
            return getCountryJson(country, Arrays.asList(excludedFields.split(ICountryRestSymbols.SEMICOLON)));
        }
    }

    private Object parsedCountries(List<Country> countries, String excludedFields) {
        if (excludedFields == null || excludedFields.isEmpty()) {
            return countries;
        } else {
            return getCountriesJson(countries, Arrays.asList(excludedFields.split(ICountryRestSymbols.SEMICOLON)));
        }
    }

    private String getCountryJson(Country country, List<String> excludedFields) {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(gson.toJson(country)).getAsJsonObject();
        for (String excludedField : excludedFields) {
            jsonObject.remove(excludedField);
        }
        return jsonObject.toString();
    }

    private String getCountriesJson(List<Country> countries, List<String> excludedFields) {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(gson.toJson(countries)).getAsJsonArray();
        JsonArray resultArray = new JsonArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jsonObject = (JsonObject) jsonArray.get(i);
            for (String excludedField : excludedFields) {
                jsonObject.remove(excludedField);
            }
            resultArray.add(jsonObject);
        }
        return resultArray.toString();
    }
}
