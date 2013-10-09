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
