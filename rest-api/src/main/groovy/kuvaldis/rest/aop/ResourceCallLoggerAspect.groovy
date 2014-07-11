package kuvaldis.rest.aop

import groovy.util.logging.Slf4j
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.springframework.stereotype.Component

/**
 * User: NFadin
 * Date: 11.07.2014
 * Time: 14:19
 */
@Slf4j
@Aspect
@Component
class ResourceCallLoggerAspect {

    @Pointcut('within(kuvaldis.rest.resource..*)')
    void withinResources() {}

    @Pointcut('@annotation(javax.ws.rs.GET)')
    void getMethod() {}

    @Pointcut('@annotation(javax.ws.rs.POST)')
    void postMethod() {}

    @Pointcut('@annotation(javax.ws.rs.PUT)')
    void putMethod() {}

    @Pointcut('@annotation(javax.ws.rs.DELETE)')
    void deleteMethod() {}

    @Pointcut('getMethod() || postMethod() || putMethod() || deleteMethod()')
    void restMethod() {}

    @Before('withinResources() && getMethod()')
    void logGetMethodCall(JoinPoint joinPoint) {
        logRestMethodCall(joinPoint, 'GET')
    }

    @Before('withinResources() && postMethod()')
    void logPostMethodCall(JoinPoint joinPoint) {
        logRestMethodCall(joinPoint, 'POST')
    }

    @Before('withinResources() && putMethod()')
    void logPutMethodCall(JoinPoint joinPoint) {
        logRestMethodCall(joinPoint, 'PUT')
    }

    @Before('withinResources() && deleteMethod()')
    void logDeleteMethodCall(JoinPoint joinPoint) {
        logRestMethodCall(joinPoint, 'DELETE')
    }

    @SuppressWarnings('GrMethodMayBeStatic')
    private logRestMethodCall(final JoinPoint jp, final String method) {
        log.info("Performing $method request to resource ${jp.signature.declaringType.simpleName} " +
                "(method ${jp.signature.name})")
    }
}
