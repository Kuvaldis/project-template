package kuvaldis.rest

import groovy.transform.CompileStatic
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
    }
}
