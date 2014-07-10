package kuvaldis.core.job

import spock.lang.Specification

/**
 * User: NFadin
 * Date: 09.07.2014
 * Time: 17:57
 */
class DeployJobExecutorTest extends Specification {
    def "jobs should be run according to priority"() {
        given:
            def j1 = job(JobPriority.SMALL)
            def j2 = job(JobPriority.BIG)
            def j3 = job(JobPriority.MEDIUM)
            def e = executor([j1, j2, j3])
        when:
            e.init()
        then:
            1 * j2.runJob()
        then:
            1 * j3.runJob()
        then:
            1 * j1.runJob()
    }

    def "should throw exception on job exception"() {
        given:
            def j = job()
            j.runJob() >> { throw new Exception() }
            def e = executor([j])
        when:
            e.init()
        then:
            thrown(Exception)
    }

    private static DeployJobExecutor executor(def jobs) {
        def result = new DeployJobExecutor()
        result.jobs = jobs
        result
    }

    private DeployJob job(def priority = JobPriority.DEFAULT) {
        def job = Mock(DeployJob)
        job.priority >> priority
        job
    }
}
