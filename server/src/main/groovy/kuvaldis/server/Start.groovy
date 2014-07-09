package kuvaldis.server

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import kuvaldis.core.service.AppUserService
import kuvaldis.model.data.domain.AppUser
import kuvaldis.model.migration.LiquibaseRunner
import org.apache.commons.daemon.Daemon
import org.apache.commons.daemon.DaemonContext
import org.apache.commons.daemon.DaemonInitException
import org.eclipse.jetty.server.Server
import org.slf4j.bridge.SLF4JBridgeHandler
import org.springframework.context.support.ClassPathXmlApplicationContext

import java.util.logging.Handler
import java.util.logging.LogManager
import java.util.logging.Logger

/**
 * User: NFadin
 * Date: 07.07.2014
 * Time: 16:00
 */
@Slf4j
@CompileStatic
class Start implements Daemon {

    private static ClassPathXmlApplicationContext context
    private String[] arguments = new String[0]

    public static void main(String[] args) {
        log.info('Start application')
        prepareLoggers()
        def start = System.currentTimeMillis()
        try {
            def ctx = new ClassPathXmlApplicationContext('classpath:serverContext.xml')
            ctx.registerShutdownHook()
            log.info("Context started in ${System.currentTimeMillis() - start} ms")
            def server = ctx.getBean('server', Server)
            server.start()
        } catch (Exception e) {
            log.error('Application error : {}', e)
            System.exit(1)
        }
    }

    private static prepareLoggers() {
        log.debug('Prepare java util logger')
        Logger javaRootLogger = LogManager.logManager.getLogger("")
        for (Handler handler : javaRootLogger.handlers) {
            javaRootLogger.removeHandler(handler)
        }
        SLF4JBridgeHandler.install()
    }

    @Override
    void init(DaemonContext context) throws DaemonInitException, Exception {
        arguments = context.getArguments()
    }

    @Override
    void start() throws Exception {
        main(arguments)
    }

    @Override
    void stop() throws Exception {
        context.destroy()
    }

    @Override
    void destroy() {
        context.destroy()
    }
}