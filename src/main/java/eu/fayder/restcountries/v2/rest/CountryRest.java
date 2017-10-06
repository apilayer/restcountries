/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package eu.fayder.restcountries.v2.rest;

import com.google.gson.*;
import eu.fayder.restcountries.domain.ResponseEntity;
import eu.fayder.restcountries.v2.domain.Country;
import eu.fayder.restcountries.domain.ICountryRestSymbols;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Provider
@Path("/v2")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class CountryRest {

    private static final Logger LOG = Logger.getLogger(CountryRest.class);

    @GET
    @Path("all")
    public Object getAllCountries(@QueryParam("fields") String fields) {
        return this.getCountries(fields);
    }

    @GET
    public Object getCountries(@QueryParam("fields") String fields) {
        LOG.info("Getting all");
        List<Country> countries = CountryService.getInstance().getAll();
        return parsedCountries(countries, fields);
    }

    @GET
    @Path("alpha/{alphacode}")
    public Object getByAlpha(@PathParam("alphacode") String alpha, @QueryParam("fields") String fields) {
        LOG.info("Getting by alpha " + alpha);
        if (isEmpty(alpha) || alpha.length() < 2 || alpha.length() > 3) {
            return getResponse(Response.Status.BAD_REQUEST);
        }
        Country country = CountryService.getInstance().getByAlpha(alpha);
        if (country != null) {
            return parsedCountry(country, fields);
        }
        return getResponse(Response.Status.NOT_FOUND);
    }

    @GET
    @Path("alpha/")
    public Object getByAlphaList(@QueryParam("codes") String codes, @QueryParam("fields") String fields) {
        LOG.info("Getting by list " + codes);
        if (isEmpty(codes) || codes.length() < 2 || (codes.length() > 3 && !codes.contains(";"))) {
            return getResponse(Response.Status.BAD_REQUEST);
        }
        try {
            List<Country> countries = CountryService.getInstance().getByCodeList(codes);
            if (!countries.isEmpty()) {
                return parsedCountries(countries, fields);
            }
            return getResponse(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return getResponse(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("currency/{currency}")
    public Object getByCurrency(@PathParam("currency") String currency, @QueryParam("fields") String fields) {
        LOG.info("Getting by currency " + currency);
        if (isEmpty(currency) || currency.length() != 3) {
            return getResponse(Response.Status.BAD_REQUEST);
        }
        try {
            List<Country> countries = CountryService.getInstance().getByCurrency(currency);
            if (!countries.isEmpty()) {
                return parsedCountries(countries, fields);
            }
            return getResponse(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return getResponse(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("name/{name}")
    public Object getByName(@PathParam("name") String name, @QueryParam("fullText") boolean fullText, @QueryParam("fields") String fields) {
        LOG.info("Getting by name " + name);
        try {
            List<Country> countries = CountryService.getInstance().getByName(name, fullText);
            if (!countries.isEmpty()) {
                return parsedCountries(countries, fields);
            }
            return getResponse(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return getResponse(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("callingcode/{callingcode}")
    public Object getByCallingCode(@PathParam("callingcode") String callingcode, @QueryParam("fields") String fields) {
        LOG.info("Getting by calling code " + callingcode);
        try {
            List<Country> countries = CountryService.getInstance().getByCallingCode(callingcode);
            if (!countries.isEmpty()) {
                return parsedCountries(countries, fields);
            }
            return getResponse(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return getResponse(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("capital/{capital}")
    public Object getByCapital(@PathParam("capital") String capital, @QueryParam("fields") String fields) {
        LOG.info("Getting by capital " + capital);
        try {
            List<Country> countries = CountryService.getInstance().getByCapital(capital);
            if (!countries.isEmpty()) {
                return parsedCountries(countries, fields);
            }
            return getResponse(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return getResponse(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("region/{region}")
    public Object getByRegion(@PathParam("region") String region, @QueryParam("fields") String fields) {
        LOG.info("Getting by region " + region);
        try {
            List<Country> countries = CountryService.getInstance().getByRegion(region);
            if (!countries.isEmpty()) {
                return parsedCountries(countries, fields);
            }
            return getResponse(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return getResponse(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("subregion/{subregion}")
    public Object getBySubRegion(@PathParam("subregion") String subregion, @QueryParam("fields") String fields) {
        LOG.info("Getting by sub region " + subregion);
        try {
            List<Country> countries = CountryService.getInstance().getBySubRegion(subregion);
            if (!countries.isEmpty()) {
                return parsedCountries(countries, fields);
            }
            return getResponse(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return getResponse(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("lang/{lang}")
    public Object getByLanguage(@PathParam("lang") String language, @QueryParam("fields") String fields) {
        LOG.info("Getting by language " + language);
        try {
            List<Country> countries = CountryService.getInstance().getByLanguage(language);
            if (!countries.isEmpty()) {
                return parsedCountries(countries, fields);
            }
            return getResponse(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return getResponse(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("demonym/{demonym}")
    public Object getByDemonym(@PathParam("demonym") String demonym, @QueryParam("fields") String fields) {
        LOG.info("Getting by demonym " + demonym);
        try {
            List<Country> countries = CountryService.getInstance().getByDemonym(demonym);
            if (!countries.isEmpty()) {
                return parsedCountries(countries, fields);
            }
            return getResponse(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return getResponse(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("regionalbloc/{regionalbloc}")
    public Object getByRegionalBloc(@PathParam("regionalbloc") String regionalBlock, @QueryParam("fields") String fields) {
        LOG.info("Getting by regional bloc " + regionalBlock);
        try {
            List<Country> countries = CountryService.getInstance().getByRegionalBloc(regionalBlock);
            if (!countries.isEmpty()) {
                return parsedCountries(countries, fields);
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

    private Object parsedCountry(Country country, String fields) {
        if (fields == null || fields.isEmpty()) {
            return country;
        } else {
            return getCountryJson(country, Arrays.asList(fields.split(ICountryRestSymbols.SEMICOLON)));
        }
    }

    private Object parsedCountries(List<Country> countries, String excludedFields) {
        if (excludedFields == null || excludedFields.isEmpty()) {
            return countries;
        } else {
            return getCountriesJson(countries, Arrays.asList(excludedFields.split(ICountryRestSymbols.SEMICOLON)));
        }
    }

    private String getCountryJson(Country country, List<String> fields) {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(gson.toJson(country)).getAsJsonObject();

        List<String> excludedFields = getExcludedFields(fields);
        for (String field : excludedFields) {
            jsonObject.remove(field);
        }
        return jsonObject.toString();
    }

    private String getCountriesJson(List<Country> countries, List<String> fields) {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(gson.toJson(countries)).getAsJsonArray();
        JsonArray resultArray = new JsonArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jsonObject = (JsonObject) jsonArray.get(i);

            List<String> excludedFields = getExcludedFields(fields);
            for (String excludedField : excludedFields) {
                jsonObject.remove(excludedField);
            }
            resultArray.add(jsonObject);
        }
        return resultArray.toString();
    }

    private List<String> getExcludedFields(List<String> fields) {
        List<String> excludedFields = new ArrayList<>(Arrays.asList(COUNTRY_FIELDS));
        excludedFields.removeAll(fields);
        return excludedFields;
    }

    private static final String[] COUNTRY_FIELDS = new String[]{
            "name",
            "topLevelDomain",
            "alpha2Code",
            "alpha3Code",
            "callingCodes",
            "capital",
            "altSpellings",
            "region",
            "subregion",
            "translations",
            "population",
            "latlng",
            "demonym",
            "area",
            "gini",
            "timezones",
            "borders",
            "nativeName",
            "numericCode",
            "currencies",
            "languages",
            "flag",
            "regionalBlocs",
            "cioc"
    };

    private boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }
}
