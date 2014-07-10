package kuvaldis.server.config

import spock.lang.Specification

/**
 * @author Kuvaldis
 * Create date: 07.07.2014 19:13
 */
class PropertiesBuilderTest extends Specification {
    def "throws illegal argument exception if main file is null"() {
        when:
            propertiesBuilder()
        then:
            thrown(IllegalArgumentException)
    }

    def "throws illegal argument exception if main file doesn't exist"() {
        when:
            propertiesBuilder('this/file/definitely/not/exist')
        then:
            thrown(IllegalArgumentException)
    }

    def "loads main config when no other config is present"() {
        when:
            def p = propertiesBuilder('testConfig.groovy').toProperties()
        then:
            p.size() == 3
            p.getProperty('prop1') == '1'
            p.getProperty('prop2.prop1') == '2.1'
            p.getProperty('prop2.prop2') == '2.2'
    }

    def "loads developer properties even if profiles properties are present"() {
        when:
            def p = propertiesBuilder('testConfig.groovy',
                    'testProfilesConfig.groovy',
                    'testDevConfig.groovy').toProperties()
        then:
            p.size() == 3
            p.getProperty('prop1') == '1'
            p.getProperty('prop2.prop1') == '2.111'
            p.getProperty('prop2.prop2') == '2.2'
    }

    def "loads default if dev properties not present even if profiles properties present without env variable"() {
        when:
            def p = propertiesBuilder('testConfig.groovy', 'testProfilesConfig.groovy').toProperties()
        then:
            p.size() == 3
            p.getProperty('prop1') == '1'
            p.getProperty('prop2.prop1') == '2.1'
            p.getProperty('prop2.prop2') == '2.2'
    }

    def "loads profile if developer properties aren't present"() {
        given:
            System.setProperty('env', 'profile')
        when:
            def p = propertiesBuilder('testConfig.groovy', 'testProfilesConfig.groovy').toProperties()
        then:
            p.size() == 3
            p.getProperty('prop1') == '1'
            p.getProperty('prop2.prop1') == '2.prof'
            p.getProperty('prop2.prop2') == '2.2'
    }

    def "throws exception if no developer properties and env set and profiles file is null" () {
        given:
            System.setProperty('env', 'profile')
        when:
            propertiesBuilder('testConfig.groovy').toProperties()
        then:
            thrown(IllegalArgumentException)
    }

    def "throws exception if no developer properties and env set and profiles file is absent" () {
        given:
            System.setProperty('env', 'profile')
        when:
            propertiesBuilder('testConfig.groovy', 'this/file/definitely/not/exist').toProperties()
        then:
            thrown(IllegalArgumentException)
    }

    private PropertiesHolder propertiesBuilder(
            final String main = null, final String profiles = null, final String dev = null) {
        def cl = this.class.classLoader
        new PropertiesBuilder().build(
                main ? cl.getResource(main)?.file : null,
                profiles ? cl.getResource(profiles)?.file : null,
                dev ? cl.getResource(dev)?.file : null
        )
    }
}
