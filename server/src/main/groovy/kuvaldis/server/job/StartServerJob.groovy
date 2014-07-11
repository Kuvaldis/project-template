package kuvaldis.server.job

import groovy.util.logging.Slf4j
import kuvaldis.core.job.AbstractDeployJob
import kuvaldis.core.job.JobPriority
import org.eclipse.jetty.server.Server
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * User: NFadin
 * Date: 11.07.2014
 * Time: 13:35
 */
@Slf4j
@Component
class StartServerJob extends AbstractDeployJob {

    @Autowired
    Server server

    @Override
    void runJob() throws Exception {
        server.start()
    }

    @Override
    JobPriority getPriority() {
        JobPriority.SMALL
    }
}
