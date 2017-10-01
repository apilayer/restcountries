package fayder.restcountries.servlet;

import fayder.restcountries.v1.rest.CountryRest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/rest")
public class RestApplication extends Application {
    private Set<Object> singletons = new HashSet<>();

    public RestApplication() {
        singletons.add(new CountryRest());
        singletons.add(new fayder.restcountries.v2.rest.CountryRest());
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
