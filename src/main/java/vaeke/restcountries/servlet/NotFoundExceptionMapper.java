package vaeke.restcountries.servlet;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<Throwable> {
	
	private static final Logger LOG = Logger.getLogger(NotFoundExceptionMapper.class);

	@Override
	public Response toResponse(Throwable exception) {
		LOG.error(exception.getMessage());
		return Response.status(Status.NOT_FOUND).build();
	}

}
