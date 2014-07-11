package kuvaldis.core.config

import spock.lang.Specification

/**
 * User: NFadin
 * Date: 07.07.2014
 * Time: 16:56
 */
class PropertiesHolderTest extends Specification {
    def "should load properties from config object"() {
        given:
            def ph = new PropertiesHolder(configObject:
                    new ConfigSlurper().parse(this.class.classLoader.getResource('testConfig.groovy')))
        when:
            def p = ph.toProperties()
        then:
            p.size() == 3
            p.getProperty('prop1') == '1'
            p.getProperty('prop2.prop1') == '2.1'
            p.getProperty('prop2.prop2') == '2.2'
    }
}
