package fayder.restcountries.domain;

import java.text.Normalizer;

// Represents the relevance of a country as a search result.
// The relevance value is calculated according to whether a search term is an 
// exact, prefix or partial match of the country's name or any of its alternative names.
public class CountryNameRelevance implements Comparable<CountryNameRelevance> {
	
	private int relevance;
	
	protected CountryNameRelevance(int relevance) {
		this.relevance = relevance;
	}
	
	private static final CountryNameRelevance EXACT_MATCH_NAME = new CountryNameRelevance(5000);
	private static final CountryNameRelevance EXACT_MATCH_ALTSPELLING = new CountryNameRelevance(4000);
	private static final CountryNameRelevance NO_MATCH = new CountryNameRelevance(0);
	
	private static final CountryNameRelevance PARTIAL_MATCH_NAME = new CountryNameRelevance(3000);
	private static final CountryNameRelevance PARTIAL_MATCH_ALTSPELLING = new CountryNameRelevance(2000);
	
	// TODO This method copies the one in CountryServiceBase, need to find a common place to put it.
	private static String normalize(String string) {
        return Normalizer.normalize(string, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

	public static CountryNameRelevance higher(CountryNameRelevance r1, CountryNameRelevance r2) {
		if(r1.compareTo(r2) > 0) {
			return r1;
		}
		return r2;
	}

    public static CountryNameRelevance calculateCountryRelevance(BaseCountry country, String query) {
		String normCountryName = normalize(country.getName().toLowerCase());
        CountryNameRelevance relevance = NO_MATCH;
        
        if(normCountryName.equals(query)) {
            relevance = higher(relevance, EXACT_MATCH_NAME);
            // There is no match more relevant than this.
            return relevance;
        }
        
        int matchIndex = normCountryName.indexOf(query);
        if(matchIndex >= 0) {
        	relevance = higher(relevance, PARTIAL_MATCH_NAME);
        }
        
        for(String alternative : country.getAltSpellings()) {
        	String normAlternative = normalize(alternative.toLowerCase());
        	if(normAlternative.equals(query)) {
            	relevance = higher(relevance, EXACT_MATCH_ALTSPELLING);
            	// exact match is already better than any partial we can find
            	break;
            }
            
            matchIndex = normAlternative.indexOf(query);
            if(matchIndex >= 0) {
            	relevance = higher(relevance, PARTIAL_MATCH_ALTSPELLING);
            }
        }
		return relevance == NO_MATCH? null : relevance;
	}

	@Override
	public int compareTo(CountryNameRelevance o) {
		return Integer.compare(relevance, o.relevance);
	}
	
	@Override
	public boolean equals(Object otherObject) {
		if(this == otherObject) return true;
		
		if(otherObject == null) return false;
		
		if(getClass() != otherObject.getClass()) return false;
		
		CountryNameRelevance other = (CountryNameRelevance)otherObject;
		return relevance == other.relevance;
	}
}
