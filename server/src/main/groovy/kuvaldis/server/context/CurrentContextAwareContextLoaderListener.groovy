package kuvaldis.server.context

import org.springframework.beans.BeansException
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.web.context.ContextLoaderListener
import org.springframework.web.context.WebApplicationContext

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
