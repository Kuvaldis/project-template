package kuvaldis.security.service

import kuvaldis.core.service.AppUserService
import kuvaldis.model.data.domain.AppUser
import org.springframework.security.core.userdetails.User
import spock.lang.Specification

import static kuvaldis.security.util.RoleUtil.toAuthorities

/**
 * @author Kuvaldis
 * Create date: 11.07.2014 22:24
 */
class UserServiceTest extends Specification {

    def "return null if there is not such user"() {
        given:
        def aus = Mock(AppUserService)
        def us = new UserService(appUserService: aus)
        when:
        def u = us.loadUserByUsername('a')
        then:
        !u
    }

    def "return user details with respective username and roles"() {
        given:
        def username = 'username'
        def role = AppUser.Role.ADMIN
        def aus = Mock(AppUserService)
        aus.find(username) >> new AppUser(
                username: username,
                password: '',
                roles: [role])
        def us = new UserService(appUserService: aus)
        when:
        def u = us.loadUserByUsername(username)
        then:
        u?.username == username
        u?.authorities?.size() == 1 &&
        u.authorities[0].authority == role.name()
    }
}
