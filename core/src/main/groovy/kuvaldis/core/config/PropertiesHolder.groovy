package kuvaldis.core.config

import groovy.transform.CompileStatic

/**
 * User: NFadin
 * Date: 16.04.14
 * Time: 11:18
 */
@CompileStatic
class PropertiesHolder {
    ConfigObject configObject

    Properties toProperties() {
        configObject.toProperties()
    }
}
