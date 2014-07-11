package kuvaldis.core.config

import groovy.util.logging.Slf4j
import org.springframework.stereotype.Component

/**
 * User: NFadin
 * Date: 16.04.14
 * Time: 11:17
 */
@Slf4j
@Component('propertiesBuilder')
class PropertiesBuilder {
    @SuppressWarnings('GrMethodMayBeStatic')
    PropertiesHolder build(final String mainConfig, final String profilesConfig = null, final String devConfig = null) {
        final mainConfigFile
        if (!mainConfig || !(mainConfigFile = new File(mainConfig)).exists()) {
            throw new IllegalArgumentException("Main config file doesn't exist: '$mainConfig'")
        }
        def config = new ConfigSlurper().parse(mainConfigFile.toURI().toURL())
        final devConfigFile
        if (devConfig && (devConfigFile = new File(devConfig)).exists()) {
            log.info("Run with developer profile '$devConfig'")
            config.merge(new ConfigSlurper().parse(devConfigFile.toURI().toURL()))
        } else {
            def env = System.getProperty('env')
            if (env) {
                log.info("Run with profile '$env'")
                final profilesConfigFile
                if (!profilesConfig || !(profilesConfigFile = new File(profilesConfig)).exists()) {
                    throw new IllegalArgumentException("Can't run application with profile '$env'. " +
                            "Profiles config doesn't exist: '$profilesConfig'")
                }
                config.merge(new ConfigSlurper(env).parse(profilesConfigFile.toURI().toURL()))
            } else {
                log.info('Run with default profile')
            }

        }
        new PropertiesHolder(configObject: config)
    }
}
