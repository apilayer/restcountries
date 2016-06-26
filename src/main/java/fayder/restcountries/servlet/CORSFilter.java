package fayder.restcountries.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by fayder on 07/04/15.
 */
@WebFilter(filterName = "CORSFilter", urlPatterns = "/*")
public class CORSFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ((HttpServletResponse)response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse)response).addHeader("Access-Control-Allow-Methods", "GET");
        ((HttpServletResponse)response).addHeader("Access-Control-Allow-Headers", "Accept, X-Requested-With");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
