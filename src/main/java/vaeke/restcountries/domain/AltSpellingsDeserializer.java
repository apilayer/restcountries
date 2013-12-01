package vaeke.restcountries.domain;

import java.io.IOException;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

public class AltSpellingsDeserializer extends JsonDeserializer<String>{

	@Override
	public String deserialize(JsonParser jp, DeserializationContext context)
			throws IOException, JsonProcessingException {
		String json = jp.getText();
		System.out.println(json);
		
		return json;
	}

}
