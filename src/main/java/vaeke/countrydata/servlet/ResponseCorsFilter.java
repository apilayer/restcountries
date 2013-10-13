/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package vaeke.countrydata.servlet;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

@WebFilter(
		filterName="cross-origin", 
		urlPatterns = {"/*"}, 
		initParams = {
				@WebInitParam(name = "allowedOrigins", value = "*"),
				@WebInitParam(name = "allowedMethods", value = "GET,OPTIONS")
				})
public class ResponseCorsFilter extends org.eclipse.jetty.servlets.CrossOriginFilter {}
