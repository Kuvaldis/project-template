package kuvaldis.rest.resource

import kuvaldis.shared.dto.AppUserDto
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.transaction.TransactionConfiguration
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

import javax.annotation.Resource

/**
 * User: NFadin
 * Date: 14.07.2014
 * Time: 14:36
 */
@ContextConfiguration(locations = ['classpath*:coreContext.xml', 'classpath*:restContext.xml'])
@Transactional
@TransactionConfiguration(defaultRollback = true)
class UserResourceTest extends Specification {

    @Resource
    UserResource userResource

    def "find the same user after create"() {
        given:
        def u = new AppUserDto(
                username: 'user',
                password: 'user',
                roles: ['USER']
        )
        when:
        def cbu = userResource.create(u)
        def fu = userResource.get(cbu.id)
        then:
        fu.username == cbu.username
        fu.id == cbu.id
    }
}
