package kuvaldis.rest

import groovy.transform.CompileStatic
import kuvaldis.rest.exception.BaseExceptionMapper
import org.glassfish.jersey.jackson.JacksonFeature
import org.glassfish.jersey.server.ResourceConfig

/**
 * User: NFadin
 * Date: 09.07.2014
 * Time: 13:51
 */
@CompileStatic
class RestConfig extends ResourceConfig {
    RestConfig(String... packages) {
        this.packages(packages)
        // exception mappers
        register(BaseExceptionMapper)
        // json feature
        register(JacksonFeature)
    }
}
