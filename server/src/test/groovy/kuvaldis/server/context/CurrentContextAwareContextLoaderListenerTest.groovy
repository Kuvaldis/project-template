package kuvaldis.server.context

import org.springframework.context.ApplicationContext
import spock.lang.Specification

import javax.servlet.ServletContext

/**
 * @author Kuvaldis
 * Create date: 11.07.2014 0:21
 */
class CurrentContextAwareContextLoaderListenerTest extends Specification {
    def "createWebApplicationContext should return set applicationContext"() {
        given:
            def l = new CurrentContextAwareContextLoaderListener()
            def c = Mock(ApplicationContext)
            def sc = Mock(ServletContext)
        when:
            l.setApplicationContext(c)
            def ac = l.createWebApplicationContext(sc)
        then:
            c == ac || c == ac.$delegate
    }
}
