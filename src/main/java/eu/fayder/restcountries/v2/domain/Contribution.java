package eu.fayder.restcountries.v2.domain;

/**
 * Created by fayder on 24/02/2017.
 */
public class Contribution {

    private int amount;
    private String token;

    public int getAmount() {
        return amount;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "Contribution{" +
                "amount=" + amount +
                '}';
    }
}
