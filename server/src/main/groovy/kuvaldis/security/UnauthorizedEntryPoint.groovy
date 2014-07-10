package kuvaldis.security

import groovy.transform.CompileStatic
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint

import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author Kuvaldis
 * Create date: 10.07.2014 0:21
 */
@CompileStatic
class UnauthorizedEntryPoint implements AuthenticationEntryPoint {
    @Override
    void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, 'Unauthorized')
    }
}
