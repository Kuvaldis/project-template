package kuvaldis.rest.resource

import groovy.util.logging.Slf4j
import kuvaldis.core.service.AppUserService
import kuvaldis.model.data.domain.AppUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import javax.annotation.PostConstruct
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

/**
 * User: NFadin
 * Date: 09.07.2014
 * Time: 14:09
 */
@Slf4j
@Component
@Path('test')
@Produces(MediaType.APPLICATION_JSON)
class TestResource {
    @Inject
    private AppUserService appUserService

    @GET
    public AppUser get() {
        appUserService.find('admin')
    }
}
