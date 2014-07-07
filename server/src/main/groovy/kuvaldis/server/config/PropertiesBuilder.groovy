package kuvaldis.server.config

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
    PropertiesHolder build(String mainConfig, String profilesConfig, String devConfig) {
        final mainConfigFile = new File(mainConfig)
        if (!mainConfigFile.exists()) {
            throw new IllegalArgumentException("Main config file doesn't exist: '$mainConfig'")
        }
        def config = new ConfigSlurper().parse(mainConfigFile.toURI().toURL())
        final devConfigFile = new File(devConfig)
        if (devConfigFile.exists()) {
            log.info("Run with developer profile '$devConfig'")
            config.merge(new ConfigSlurper().parse(devConfigFile.toURI().toURL()))
        } else {
            def env = System.getProperty('env')
            if (!env) {
                log.info('Run with default profile')
            } else {
                log.info("Run with profile '$env'")
                final profilesConfigFile = new File(profilesConfig)
                if (!profilesConfigFile.exists()) {
                    throw new IllegalArgumentException("Can't run application with profile '$env'. " +
                            "Profiles config doesn't exist: '$profilesConfig'")
                }
                config.merge(new ConfigSlurper(env).parse(profilesConfigFile.toURI().toURL()))
            }

        }
        new PropertiesHolder(configObject: config)
    }
}
