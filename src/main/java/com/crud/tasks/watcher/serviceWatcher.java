package com.crud.tasks.watcher;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class serviceWatcher {
    @Before("execution(* com.crud.tasks.service.DbService.*(..))" +
            "&& target(object)")
    public void logEvent(JoinPoint joinPoint, Object object) {
        log.info("New request! [Class: " + object.getClass().getName() + ", Method: " + joinPoint.getSignature().getName() + "]");
    }
}
