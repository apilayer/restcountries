package eu.fayder.restcountries.v2.rest;

import com.google.gson.Gson;
import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import eu.fayder.restcountries.domain.ResponseEntity;
import eu.fayder.restcountries.v2.domain.Contribution;
import org.apache.http.util.TextUtils;
import org.apache.log4j.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fayder on 24/02/2017.
 */
@Provider
@Path("/contribute")
@Consumes("application/json;charset=utf-8")
public class StripeRest {

    private static final Logger LOG = Logger.getLogger(StripeRest.class);

    @POST
    public Object contribute(Contribution contribution) {
        LOG.debug("Contribution: " + contribution);

        if (contribution == null || TextUtils.isBlank(contribution.getToken())) {
            return getResponse(Response.Status.BAD_REQUEST);
        }

        Stripe.apiKey = "";
        Map<String, Object> params = new HashMap<>();
        params.put("amount", contribution.getAmount());
        params.put("currency", "eur");
        params.put("description", "REST Countries");
        params.put("source", contribution.getToken());

        try {
            Charge.create(params);
        } catch (AuthenticationException | InvalidRequestException | CardException | APIConnectionException | APIException e) {
            LOG.error(e.getMessage(), e);
            return getResponse(Response.Status.BAD_REQUEST);
        }

        return getResponse(Response.Status.ACCEPTED);
    }

    private Response getResponse(Response.Status status) {
        Gson gson = new Gson();
        return Response
                .status(status)
                .entity(gson.toJson(new ResponseEntity(status.getStatusCode(),
                        status.getReasonPhrase()))).build();
    }
}
