package com.chaucer.blockchain.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author Chaucer
 * @date 2019-10-07 13:46
 */

@Aspect
@Component
@Slf4j
public class DataStorageAspect {

    //统一切点
    @Pointcut("execution(* com.chaucer.blockchain.service.impl.SenseDataServiceImpl.writeToBlockchain())")
    public void pointCut(){
    }

    //前置通知
    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        log.info("前置通知：开始执行writeToBlockchain()方法,将数据存入DHT,并将数据addr上传到区块链");
    }

    @AfterReturning("pointCut()")
    public void after(JoinPoint joinPoint) {
        log.info("后置通知：执行完成");
    }
}
