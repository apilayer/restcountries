/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package vaeke.countrydata.servlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

@WebServlet(
		name ="RestEasy", 
		urlPatterns = {"/*"}, 
		initParams = {@WebInitParam(name="resteasy.scan", value="true")})
public class HttpServletDispatcher extends org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6432105335073718114L;

}
