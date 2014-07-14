package kuvaldis.rest

import org.glassfish.hk2.utilities.Binder
import org.glassfish.hk2.utilities.binding.AbstractBinder
import org.glassfish.jersey.CommonProperties
import org.glassfish.jersey.test.JerseyTest
import spock.lang.Shared
import spock.lang.Specification

import javax.ws.rs.client.WebTarget
import javax.ws.rs.core.Application

/**
 * User: NFadin
 * Date: 14.07.2014
 * Time: 17:04
 */
class JerseySpockTest extends Specification {

    @Shared private JerseyTest jerseyTest

    def setupSpec() {
        jerseyTest = new JerseyTest() {
            @Override
            protected Application configure() {
                def c = new RestConfig('kuvaldis.rest').property(CommonProperties.METAINF_SERVICES_LOOKUP_DISABLE, true)
                def b
                if ((b = binder())) { c.register(b) }
                c
            }
        }
        jerseyTest.setUp()
    }

    def cleanupSpec() {
        if(jerseyTest) { jerseyTest.tearDown() }
    }

    WebTarget target() {
        jerseyTest.target()
    }

    private Binder binder() {
        def c = bindObjects()
        def b = new AbstractBinder() {
            @Override
            protected void configure() {
                if (c) { c.call() }
            }
        }
        if (c) {
            c.delegate = b
        }
        b
    }

    protected Closure bindObjects() {
        null
    }
}
