package kuvaldis.core.config

import spock.lang.Specification

/**
 * User: NFadin
 * Date: 07.07.2014
 * Time: 16:35
 */
class MultiConfigPropertiesFactoryBeanTest extends Specification {
    def "should load properties from properties holder"() {
        given:
            def ph = new PropertiesHolder(configObject:
                    new ConfigSlurper().parse(this.class.classLoader.getResource('testConfig.groovy')))
            def m = new MultiConfigPropertiesFactoryBean(propertiesHolder: ph)
            def p = new Properties()
        when:
            m.loadProperties(p)
        then:
            p.size() == 3
            p.getProperty('prop1') == '1'
            p.getProperty('prop2.prop1') == '2.1'
            p.getProperty('prop2.prop2') == '2.2'
    }
}
