package kuvaldis.rest.resource

import kuvaldis.core.service.AppUserService
import kuvaldis.model.data.domain.AppUser
import kuvaldis.rest.JerseySpecification
import kuvaldis.shared.dto.AppUserDto

import javax.ws.rs.BadRequestException
import javax.ws.rs.client.Entity
import javax.ws.rs.core.MediaType

/**
 * User: NFadin
 * Date: 11.07.2014
 * Time: 16:07
 */
class UserRestTest extends JerseySpecification {

    @Override
    protected Closure bindObjects() {
        return {
            def us = Mock(AppUserService)
            us.find(_) >> new AppUser(username: 'admin')
            bind(us).to(AppUserService)
        }
    }

    def "validate username is not null"() {
        when:
        target().path('/user').request().post(Entity.entity(
                new AppUserDto(username: null), MediaType.APPLICATION_JSON_TYPE), AppUserDto)
        then:
        def e = thrown(BadRequestException)
        e.message == 'HTTP 400 Bad Request'
    }
}