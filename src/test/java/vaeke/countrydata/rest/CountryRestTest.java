/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
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
	
	@HttpTest(method = Method.GET, path = "/alpha/co")
	public void alpha2() {
		Assert.assertOk(response);
		org.junit.Assert.assertTrue(response.getBody().contains("Colombia"));
	}
	
	@HttpTest(method = Method.GET, path = "/alpha/col")
	public void alpha3() {
		Assert.assertOk(response);
		org.junit.Assert.assertTrue(response.getBody().contains("Colombia"));
	}
	
	@HttpTest(method = Method.GET, path = "/currency/cop")
	public void currency() {
		Assert.assertOk(response);
		org.junit.Assert.assertTrue(response.getBody().contains("Colombia"));
	}
	
	@HttpTest(method = Method.GET, path = "/callingcode/57")
	public void callingcode() {
		Assert.assertOk(response);
		org.junit.Assert.assertTrue(response.getBody().contains("Colombia"));
	}

}
