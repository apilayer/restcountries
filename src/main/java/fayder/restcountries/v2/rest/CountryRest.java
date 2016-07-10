/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package fayder.restcountries.v2.rest;

import com.google.gson.Gson;
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
    public Object getByAlpha(@PathParam("alphacode") String alpha) {
        LOG.info("Getting by alpha " + alpha);
        Country country = CountryService.getInstance().getByAlpha(alpha);
        if (country != null) {
            return country;
        }
        return getResponse(Response.Status.NOT_FOUND);
    }

    @GET
    @Path("alpha/")
    public Object getByAlphaList(@QueryParam("codes") String codes) {
        LOG.info("Getting by list " + codes);
        try {
            List<Country> countries = CountryService.getInstance().getByCodeList(codes);
            if (!countries.isEmpty()) {
                return countries;
            }
            return getResponse(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return getResponse(Response.Status.INTERNAL_SERVER_ERROR);
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
            return getResponse(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return getResponse(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("name/{name}")
    public Object getByName(@PathParam("name") String name, @QueryParam("fullText") boolean fullText) {
        LOG.info("Getting by name " + name);
        try {
            List<Country> countries = CountryService.getInstance().getByName(name, fullText);
            if (!countries.isEmpty()) {
                return countries;
            }
            return getResponse(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return getResponse(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("callingcode/{callingcode}")
    public Object getByCallingCode(@PathParam("callingcode") String callingcode) {
        LOG.info("Getting by calling code " + callingcode);
        try {
            List<Country> countries = CountryService.getInstance().getByCallingCode(callingcode);
            if (!countries.isEmpty()) {
                return countries;
            }
            return getResponse(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return getResponse(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("capital/{capital}")
    public Object getByCapital(@PathParam("capital") String capital) {
        LOG.info("Getting by capital " + capital);
        try {
            List<Country> countries = CountryService.getInstance().getByCapital(capital);
            if (!countries.isEmpty()) {
                return countries;
            }
            return getResponse(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return getResponse(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("region/{region}")
    public Object getByRegion(@PathParam("region") String region) {
        LOG.info("Getting by region " + region);
        try {
            List<Country> countries = CountryService.getInstance().getByRegion(region);
            if (!countries.isEmpty()) {
                return countries;
            }
            return getResponse(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return getResponse(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("lang/{lang}")
    public Object getByLanguage(@PathParam("lang") String language) {
        LOG.info("Getting by language " + language);
        try {
            List<Country> countries = CountryService.getInstance().getByLanguage(language);
            if (!countries.isEmpty()) {
                return countries;
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

    private Object parsedCountry(Country country, String excludedFields) throws IOException {
        if(excludedFields == null || excludedFields.isEmpty()) {
            return country;
        } else {
            return excludeFields(Arrays.asList(excludedFields.split(ICountryRestSymbols.SEMICOLON)), country);
        }
    }

    private String excludeFields(List<String> fields, Country country) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.getSerializationConfig().addMixInAnnotations(Object.class, PropertyFilterMixIn.class);
        String[] ignorableFieldNames = (String[]) fields.toArray();
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter(
                        "filter properties by name",
                        SimpleBeanPropertyFilter.serializeAllExcept(ignorableFieldNames)
                );
        ObjectWriter writer = mapper.writer(filters);
        return writer.writeValueAsString(country);
    }
}
