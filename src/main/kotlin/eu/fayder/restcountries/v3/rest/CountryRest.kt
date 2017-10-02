package eu.fayder.restcountries.v3.rest

import eu.fayder.restcountries.v2.rest.CountryService
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType
import javax.ws.rs.ext.Provider

@Provider
@Path("/v3")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
class CountryRest {

    @GET
    fun getCountries(@QueryParam("fields") fields: String): Any {
        return CountryService.getInstance().all
    }
}