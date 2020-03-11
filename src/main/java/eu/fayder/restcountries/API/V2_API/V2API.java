package eu.fayder.restcountries.API.V2_API;

import eu.fayder.restcountries.v2.domain.Country;
import eu.fayder.restcountries.v2.domain.Currency;
import eu.fayder.restcountries.v2.domain.Language;
import eu.fayder.restcountries.v2.domain.RegionalBloc;
import eu.fayder.restcountries.v2.rest.CountryService;

import java.util.ArrayList;
import java.util.List;

public class V2API {

    private static CountryService instance;

    public static CountryService getInstance() {

        return CountryService.getInstance();
    }

    public V2API(){
        instance=this.getInstance();

    }

    public  List<Country> getAll(){
        List<Country> countries =instance.getAll();
        return countries;

    }

    public  Country getByAlpha(String alpha){
      Country con=  instance.getByAlpha(alpha);
      return con;
    }

    public List<Country> getByCodeList(String codeList){
        List<Country> con=instance.getByCodeList(codeList);
        return con;
    }

    public List<Country> getByName(String name, boolean isFullText) {
        return instance.getByName(name,isFullText);
    }

    public List<Country> getByCallingCode(String callingcode) {
        return instance.getByCallingCode(callingcode);
    }

    public List<Country> getByCapital(String capital) {
        return instance.getByCapital(capital);
    }

    public List<Country> getByRegion(String region) {
        return instance.getByRegion(region);
    }


    public List<Country> getBySubRegion(String subregion) {
        return instance.getBySubRegion(subregion);
    }

    public List<Country> getByCurrency(String currency) {
        return instance.getByCurrency(currency);
    }

    public List<Country> getByLanguage(String language) {
        return instance.getByLanguage(language);
    }

    public List<Country> getByDemonym(String demonym) {

        return instance.getByDemonym(demonym);
    }

    public List<Country> getByRegionalBloc(String regionalBloc) {

        return instance.getByRegionalBloc(regionalBloc);
    }







    public static void main(String[] args) {
        V2API contructor=new V2API();
        List <Country> list=contructor.getByName("egypt",true);

        System.out.println(list.get(0).getName()+" "+list.get(0).getLanguages().get(0).getNativeName());

    }

}
