package kuvaldis.model.migration

import groovy.util.logging.Slf4j
import liquibase.integration.spring.SpringLiquibase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * @author Kuvaldis
 * Create date: 07.07.2014 23:35
 */
@Component
@Slf4j
class LiquibaseRunner {
    @Autowired
    private SpringLiquibase liquibase

    void run() {
        log.info('Run migration')
        liquibase.shouldRun = true
        liquibase.afterPropertiesSet()
    }
}
