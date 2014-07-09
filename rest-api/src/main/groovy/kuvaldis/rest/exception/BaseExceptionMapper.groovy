package kuvaldis.rest.exception

import groovy.util.logging.Slf4j
import org.springframework.stereotype.Component

import javax.ws.rs.WebApplicationException
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

/**
 * User: NFadin
 * Date: 09.07.2014
 * Time: 16:11
 */
@Provider
@Slf4j
class BaseExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    Response toResponse(Throwable t) {
        log.error('Uncaught exception thrown by rest service', t)
        if (t instanceof WebApplicationException) {
            t.response
        } else {
            Response.status(Response.Status.INTERNAL_SERVER_ERROR).build()
        }
    }
}
