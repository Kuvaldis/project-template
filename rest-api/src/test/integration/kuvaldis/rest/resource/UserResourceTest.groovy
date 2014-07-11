package kuvaldis.rest.resource

import kuvaldis.model.data.domain.AppUser
import kuvaldis.rest.RestConfig
import org.glassfish.jersey.client.ClientConfig
import org.glassfish.jersey.server.ResourceConfig
import org.glassfish.jersey.test.DeploymentContext
import org.glassfish.jersey.test.jetty.JettyTestContainerFactory
import org.glassfish.jersey.test.spi.TestContainer
import org.springframework.context.support.ClassPathXmlApplicationContext
import spock.lang.Shared
import spock.lang.Specification

import javax.ws.rs.client.ClientBuilder

/**
 * User: NFadin
 * Date: 11.07.2014
 * Time: 16:07
 */
class UserResourceTest extends Specification {

    @Shared
    protected TestContainer container

    def setupSpec() {
        final ResourceConfig rc = new RestConfig('kuvaldis.rest')
                .property('contextConfig', new ClassPathXmlApplicationContext(
                'classpath*:coreContext.xml',
                'classpath*:restContext.xml'))
        container = new JettyTestContainerFactory()
                .create(URI.create('http://localhost:9090'),
                DeploymentContext.builder(rc).contextPath('/').build())
        container.start()
    }

    def cleanupSpec() {
        container?.stop()
    }

    def "try something"() {
        given:
        def config = new ClientConfig()
        def client = ClientBuilder.newClient(config)
        def target = client.target(container.baseUri)
        when:
        def response = target.path('/user').request().get(AppUser)
        then:
        response?.username == 'admin'
    }
}
