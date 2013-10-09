package vaeke.countrydata.rest;

import org.junit.Rule;
import org.junit.runner.RunWith;

import com.eclipsesource.restfuse.Assert;
import com.eclipsesource.restfuse.Destination;
import com.eclipsesource.restfuse.HttpJUnitRunner;
import com.eclipsesource.restfuse.Method;
import com.eclipsesource.restfuse.Response;
import com.eclipsesource.restfuse.annotation.Context;
import com.eclipsesource.restfuse.annotation.HttpTest;

@RunWith(HttpJUnitRunner.class)
public class CountryRestTest {

	@Rule
	public Destination destination = new Destination(this, "http://localhost:8081/rest");

	@Context
	private Response response;

	@HttpTest(method = Method.GET, path = "/")
	public void checkRestfuseOnlineStatus() {
		Assert.assertOk(response);
	}
	
	@HttpTest(method = Method.GET, path = "/alpha2/co")
	public void alpha2() {
		Assert.assertOk(response);
		org.junit.Assert.assertTrue(response.getBody().contains("Colombia"));
	}
	
	@HttpTest(method = Method.GET, path = "/alpha3/col")
	public void alpha3() {
		Assert.assertOk(response);
		org.junit.Assert.assertTrue(response.getBody().contains("Colombia"));
	}
	
	@HttpTest(method = Method.GET, path = "/currency/cop")
	public void currency() {
		Assert.assertOk(response);
		org.junit.Assert.assertTrue(response.getBody().contains("Colombia"));
	}

}
