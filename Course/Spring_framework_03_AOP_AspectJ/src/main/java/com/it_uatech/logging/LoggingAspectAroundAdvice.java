package com.it_uatech.logging;

import com.it_uatech.domain.Person;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Order(2)
@Aspect
@Component
public class LoggingAspectAroundAdvice {

    @Around("@annotation(com.it_uatech.logging.Loggable)")
    public Object logAround(ProceedingJoinPoint joinPoint) {
        System.out.println("Around advice");

        System.out.println("Call method with parameters: " + Arrays.toString(joinPoint.getArgs()));

        System.out.println("------------------------------------------------------");


        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        System.out.println("Result from original execution of PersonService: " + result);
        System.out.println("------------------------------------------------------");
        System.out.println("Change result to: Baba Luba - 77");
        return new Person("Baba Luba",77);
    }
}
