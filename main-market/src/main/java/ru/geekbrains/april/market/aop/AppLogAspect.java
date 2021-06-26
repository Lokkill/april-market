package ru.geekbrains.april.market.aop;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
@Data
public class AppLogAspect {

    private long timeProductsService;
    private long timeUserService;
    private long timeOrderService;
    private long timeRoleService;

    @Around("execution(public * ru.geekbrains.eureka.client.services.ProductService.*(..))")
    public Object stopwatchProductService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return getDuration(proceedingJoinPoint, "ProductService");
    }

    @Around("execution(public * ru.geekbrains.april.market.services.UserService.*(..))")
    public Object stopwatchUserService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return getDuration(proceedingJoinPoint, "UserService");
    }

    @Around("execution(public * ru.geekbrains.april.market.services.OrderService.*(..))")
    public Object stopwatchOrderService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return getDuration(proceedingJoinPoint, "OrderService");
    }

    @Around("execution(public * ru.geekbrains.april.market.services.RoleService.*(..))")
    public Object stopwatchRoleService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return getDuration(proceedingJoinPoint, "RoleService");
    }

    public Object getDuration(ProceedingJoinPoint proceedingJoinPoint, String nameClass) throws Throwable {
        log.debug("Time measurement start for " + nameClass);
        long startTime = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        defineData(nameClass, duration);
        log.debug("Time measurement end for " + nameClass);
        return out;
    }

    public void defineData(String name, long durationTime){
        switch (name){
            case "ProductService":
                timeProductsService += durationTime;
                break;
            case "UserService":
                timeUserService += durationTime;
                break;
            case "OrderService":
                timeOrderService += durationTime;
                break;
            case "RoleService":
                timeRoleService += durationTime;
                break;
        }
    }
}
