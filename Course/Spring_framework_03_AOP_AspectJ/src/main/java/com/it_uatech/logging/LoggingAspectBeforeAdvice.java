package com.it_uatech.logging;

import com.it_uatech.dao.PersonDao;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Aspect
@Component
public class LoggingAspectBeforeAdvice {

    @Before("@annotation(com.it_uatech.logging.Loggable)")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Before advice");
        System.out.println("Вызов метода : " + joinPoint.getSignature().getName());
        System.out.println("Call method from target class : " + ((PersonDao)joinPoint.getTarget()).findByName("Vova"));
        System.out.println("################################################################");
    }
}
