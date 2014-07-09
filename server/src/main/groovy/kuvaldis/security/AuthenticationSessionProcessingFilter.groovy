package kuvaldis.security

import groovy.transform.CompileStatic
import groovy.transform.TupleConstructor
import kuvaldis.model.data.domain.AppUser
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.GenericFilterBean

import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

/**
 * @author Kuvaldis
 * Create date: 10.07.2014 0:26
 */
@CompileStatic
@TupleConstructor
class AuthenticationSessionProcessingFilter extends GenericFilterBean {

    @Value('${common.devMode}')
    Boolean devMode

    private UserDetailsService userDetailsService
    private String usernameSessionAttr

    @Override
    void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = getAsHttpRequest(request);
        String username = httpRequest.session.getAttribute(usernameSessionAttr)
        UserDetails userDetails = null
        if (username) {
            userDetails = userDetailsService.loadUserByUsername(username)
        } else if (devMode) { // in dev mode you can go anywhere
            userDetails = new User('developer', 'developer', EnumSet.allOf(AppUser.Role).collect {
                new SimpleGrantedAuthority(it.name())
            })
        }
        if (userDetails) {
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities);
            authentication.details = new WebAuthenticationDetailsSource().buildDetails(httpRequest);
            SecurityContextHolder.context.authentication = authentication;
        }
        chain.doFilter(request, response);
    }

    private static HttpServletRequest getAsHttpRequest(ServletRequest request) {
        if (!(request instanceof HttpServletRequest)) {
            throw new RuntimeException("Expecting an HTTP request");
        }
        return (HttpServletRequest) request;
    }

    void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService
    }

    void setUsernameSessionAttr(String usernameSessionAttr) {
        this.usernameSessionAttr = usernameSessionAttr
    }
}
