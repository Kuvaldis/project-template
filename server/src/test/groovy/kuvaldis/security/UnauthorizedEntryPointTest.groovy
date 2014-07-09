package kuvaldis.security

import org.springframework.security.core.AuthenticationException
import spock.lang.Specification

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author Kuvaldis
 * Create date: 10.07.2014 2:02
 */
class UnauthorizedEntryPointTest extends Specification {
    def "should always send unauthorized error"() {
        given:
            def ep = new UnauthorizedEntryPoint()
            def response = Mock(HttpServletResponse)
        when:
            ep.commence(Mock(HttpServletRequest), response, Mock(AuthenticationException))
        then:
            1 * response.sendError(HttpServletResponse.SC_UNAUTHORIZED, 'Unauthorized')
    }
}
