package com.tech.sprj09.aspect;

import java.util.Properties;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import com.tech.sprj09.controller.BController;

@Aspect
@Component
public class LoggingAspect {
	
	private static final Logger logger=LoggerFactory.getLogger(BController.class);
	
	@Around("execution(* com.tech.sprj09.controller.*.*(..))")
	public Object loggerAround(ProceedingJoinPoint joinPoint) throws Throwable{
		long beforeTimeMillis=System.currentTimeMillis();
		System.out.println("[BoardController] start :"
				+joinPoint.getSignature().getDeclaringTypeName()+"."
				+joinPoint.getSignature().getName());
		System.out.println("time start : "+beforeTimeMillis);
		Object result=joinPoint.proceed();//핵심코드실행
		
		long afterTimeMillis=System.currentTimeMillis();
		System.out.println("[BoardController] end :"
				+joinPoint.getSignature().getDeclaringTypeName()+"."
				+joinPoint.getSignature().getName());
		System.out.println("time end : "+afterTimeMillis);
		logger.info("loggggggg 끝 : "+joinPoint.getSignature().getName());
		
		System.out.println("-------------------------------------------------------");
		return result;
	}
//	============ tx set ===============================
    @Autowired
    private DataSourceTransactionManager transactionManager;
    private final static String BASE_DEFAULT_POITCUT = "execution(* com.tech.sprj09.controller.*.*(..))";


	
//    private final PlatformTransactionManager transactionManager;

    @Bean
    public TransactionInterceptor txAdvice() {
        TransactionInterceptor txAdvice = new TransactionInterceptor();
        Properties txAttributes = new Properties();
        
        DefaultTransactionAttribute defaultAttribute = new DefaultTransactionAttribute();
//        DefaultTransactionAttribute defaultAttribute = new DefaultTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRED, rollbackRules);
        String attributesDefinition = defaultAttribute.toString();

        txAttributes.setProperty("*", attributesDefinition);

        txAdvice.setTransactionAttributes(txAttributes);
        txAdvice.setTransactionManager(transactionManager);
        return txAdvice;
    }

    @Bean
    public DefaultPointcutAdvisor txAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(BASE_DEFAULT_POITCUT);
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }
	
	
	
	
}
