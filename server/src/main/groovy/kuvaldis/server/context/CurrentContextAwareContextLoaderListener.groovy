package kuvaldis.server.context

import ch.qos.logback.core.spi.ContextAware
import org.springframework.beans.BeansException
import org.springframework.beans.factory.BeanFactory
import org.springframework.beans.factory.BeanFactoryAware
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.support.DefaultListableBeanFactory
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Service
import org.springframework.web.context.ContextLoader
import org.springframework.web.context.ContextLoaderListener
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.context.support.GenericWebApplicationContext

import javax.servlet.ServletContext

/**
 * User: NFadin
 * Date: 09.07.2014
 * Time: 12:43
 */
class CurrentContextAwareContextLoaderListener extends ContextLoaderListener implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    protected WebApplicationContext createWebApplicationContext(ServletContext sc) {
        return applicationContext as WebApplicationContext
    }

    @Override
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext
    }
}
