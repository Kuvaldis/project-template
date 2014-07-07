package kuvaldis.server

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.apache.commons.daemon.Daemon
import org.apache.commons.daemon.DaemonContext
import org.apache.commons.daemon.DaemonInitException
import org.springframework.context.support.ClassPathXmlApplicationContext

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
        def start = System.currentTimeMillis()
        try {
            def ctx = new ClassPathXmlApplicationContext(
                    'classpath:serverContext.xml')
            ctx.registerShutdownHook()
            log.info("Context started in ${System.currentTimeMillis() - start} ms")
        } catch (Exception e) {
            log.error('Application error : {}', e)
            System.exit(1)
        }
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