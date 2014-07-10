package kuvaldis.security

import kuvaldis.model.data.domain.AppUser
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import spock.lang.Specification

import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession

/**
 * User: NFadin
 * Date: 10.07.2014
 * Time: 11:25
 */
class AuthenticationSessionProcessingFilterTest extends Specification {

    private final usernameSessionAttr = 'username'
    private final username = 'someuser'

    private ServletRequest request
    private ServletResponse response
    private FilterChain filterChain
    private AuthenticationSessionProcessingFilter filter
    private HttpSession session
    private UserDetailsService userDetailsService

    def setup() {
        request = Mock(HttpServletRequest)
        response = Mock(HttpServletResponse)
        filterChain = Mock(FilterChain)
        session = Mock(HttpSession)
        request.session >> session
        userDetailsService = Mock(UserDetailsService)
        filter = new AuthenticationSessionProcessingFilter(
                devMode: false,
                userDetailsService: userDetailsService,
                usernameSessionAttr: usernameSessionAttr
        )
        SecurityContextHolder.context.authentication = null
    }

    def "throws exception if not HttpServletRequest"() {
        given:
        request = Mock(ServletRequest)
        when:
        filter.doFilter(request, response, filterChain)
        then:
        def t = thrown(Exception)
        t.message == 'Expecting an HTTP request'
    }

    def "should load user details to security context if present"() {
        given:
        session.getAttribute(usernameSessionAttr) >> username
        userDetailsService.loadUserByUsername(username) >> new User(username, 'somepassword', [])
        when:
        filter.doFilter(request, response, filterChain)
        then:
        userDetails().username == username
    }

    def "should load developer with all roles in case of dev mode and user's not logged in"() {
        given:
        filter.devMode = Boolean.TRUE
        session.getAttribute(usernameSessionAttr) >> null
        when:
        filter.doFilter(request, response, filterChain)
        then:
        def ud = userDetails()
        ud.username == 'developer'
        !AppUser.Role.values().find { role ->
            !ud.authorities*.authority.find {
                role.name() == it
            }
        }
    }

    def "no authentication data if user wasn't found in user details service"() {
        given:
        session.getAttribute(usernameSessionAttr) >> username
        userDetailsService.loadUserByUsername(username) >> null
        when:
        filter.doFilter(request, response, filterChain)
        then:
        userDetails() == null
    }

    UserDetails userDetails() {
        SecurityContextHolder.context.authentication?.principal as UserDetails
    }
}
