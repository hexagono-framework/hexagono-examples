package com.github.hexagonoframework.example.webapplication.infrastructure.logging;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import com.github.hexagonoframework.ApplicationException;

@Interceptor
@Log
@Dependent
public class LoggingInterceptor {
    
    @Inject
    private LoggingService logger;
    
    @AroundInvoke
    public Object aroundInvoke(InvocationContext ic) throws Exception {
        logger.info("Executing " + ic.getMethod().getName() + " from " + ic.getTarget().getClass().getSimpleName());
        
        Object object;
        try {
            object = ic.proceed();
        } catch (ApplicationException e) {
            logger.info(e.getMessage());
            throw e;
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
            throw e;
        }
        
        logger.info("Successful execution");
        return object;
    }
}
