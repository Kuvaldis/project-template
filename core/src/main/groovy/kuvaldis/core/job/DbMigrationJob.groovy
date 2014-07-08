package kuvaldis.core.job

import groovy.transform.CompileStatic
import kuvaldis.model.migration.LiquibaseRunner
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * User: NFadin
 * Date: 16.04.14
 * Time: 12:34
 */
@Component
@CompileStatic
class DbMigrationJob extends AbstractDeployJob {

    @Autowired
    private LiquibaseRunner liquibaseRunner

    @Override
    JobPriority getPriority() {
        JobPriority.BIG
    }

    @Override
    void runJob() throws Exception {
        liquibaseRunner.run()
    }
}
