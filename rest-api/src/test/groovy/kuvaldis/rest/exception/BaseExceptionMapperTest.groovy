package kuvaldis.rest.exception

import spock.lang.Specification

import javax.ws.rs.WebApplicationException
import javax.ws.rs.core.Response

/**
 * @author Kuvaldis
 * Create date: 10.07.2014 22:28
 */
class BaseExceptionMapperTest extends Specification {

    def "return response from WebApplicationException" () {
        given:
            def m = new BaseExceptionMapper()
            def e = Mock(WebApplicationException)
            def ir = Mock(Response)
            e.response >> ir
        when:
            def or = m.toResponse(e)
        then:
            or == ir
    }

    def "return response with internal server error for other exceptions" () {
        given:
            def m = new BaseExceptionMapper()
            def e = Mock(Exception)
        when:
            def r = m.toResponse(e)
        then:
            r.status == Response.Status.INTERNAL_SERVER_ERROR.statusCode
    }
}
