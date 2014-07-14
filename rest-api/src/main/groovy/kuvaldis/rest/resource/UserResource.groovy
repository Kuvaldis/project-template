package kuvaldis.rest.resource

import groovy.util.logging.Slf4j
import kuvaldis.core.service.AppUserService
import kuvaldis.model.data.domain.AppUser
import kuvaldis.shared.dto.AppUserDto
import org.springframework.stereotype.Component

import javax.inject.Inject
import javax.validation.Valid
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

/**
 * User: NFadin
 * Date: 09.07.2014
 * Time: 14:09
 */
@Slf4j
@Component
@Path('user')
@Produces(MediaType.APPLICATION_JSON)
class UserResource {

    UserResource() {
        log.info('123')
    }

    @Inject
    private AppUserService appUserService

    @GET
    AppUserDto get() {
        appUserService.find('admin').toMap()
    }

    @GET
    @Path('{id}')
    AppUserDto get(@PathParam('id') Long id) {
        appUserService.find(id).toMap()
    }

    @POST
    AppUserDto create(@Valid final AppUserDto user) {
        hidePassword(appUserService.create([
                username: user.username,
                password: user.password,
                roles: user.roles.collect {
                    AppUser.Role.valueOf(it)
                }
        ])).toMap()
    }

    static def hidePassword(AppUser user) {
        user.password = '[PASSWORD]'
        user
    }
}
