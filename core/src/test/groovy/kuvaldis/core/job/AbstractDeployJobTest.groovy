package kuvaldis.core.job

import spock.lang.Specification

/**
 * User: NFadin
 * Date: 09.07.2014
 * Time: 17:03
 */
class AbstractDeployJobTest extends Specification {
    def "priority have to be default by default"() {
        given:
            def j = Spy(AbstractDeployJob)
        when:
            def p = j.priority
        then:
            p == JobPriority.DEFAULT
    }
}
