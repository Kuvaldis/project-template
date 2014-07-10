package kuvaldis.core.service

import kuvaldis.model.data.domain.AppUser
import kuvaldis.model.data.repository.AppUserRepository
import spock.lang.Specification

/**
 * @author Kuvaldis
 * Create date: 09.07.2014 23:10
 */
class AppUserServiceTest extends Specification {

    private final username1 = 'username1'
    private final username2 = 'username2'

    def "user is available by name after creation"() {
        given:
            def ur = Mock(AppUserRepository)
            def u = new AppUser(username: username1)
            ur.save(u) >> {
                ur.findByUsername(username1) >> u
                u
            }
            def us = new AppUserService(appUserRepository: ur)
        when:
            us.create(u)
        then:
            us.find(username1).username == username1
    }

    def "should return null if no user with name and user object with given username"() {
        given:
            def ur = Mock(AppUserRepository)
            ur.findByUsername(username1) >> new AppUser(username: username1)
            ur.findByUsername(username2) >> null
            def us = new AppUserService(appUserRepository: ur)
        when:
            def u1 = us.find(username1)
            def u2 = us.find(username2)
        then:
            u1.username == username1
            u2 == null
    }
}
