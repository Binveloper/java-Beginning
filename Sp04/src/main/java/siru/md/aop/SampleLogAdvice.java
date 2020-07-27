package siru.md.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import lombok.extern.log4j.Log4j;

@Aspect
@Component
@Log4j
public class SampleLogAdvice {
	/*@Before("execution(* siru.md.service.SampleLogService*.*(..))")
	public void logBefore() {
		log.info("logBefore() ����");
	}*/
	
	@Before("execution(* siru.md.service.SampleLogService*.add(String, String)) && args(str1, str2)")
	public void logBeforeWithParam(String str1, String str2) {
		log.info("logBeforeWithParam() ���� str 1: " + str1 + ", str2: " + str2);
	}
	
	@Around("execution(* siru.md.service.SampleLogService*.*(..))")
	public Object longTime(ProceedingJoinPoint pjp) {
		long start = System.currentTimeMillis();
		
		log.info(">>>>>pjp.getTarget().getClass(): " + pjp.getTarget().getClass());
		log.info(">>>>>pjp.getArgs: " + Arrays.toString(pjp.getArgs()));
		// ���� �߰�
		Object result = null;
		try {
			result = pjp.proceed(); //����Ͻ� �޼ҵ�� �����ϵ��� �ϴ� �޼ҵ�
			
		}catch(Throwable t) {}
		
		long end = System.currentTimeMillis();
		log.info(">>>>>���� �ð�:" + (end-start) + "ms");
		
		return result;
	}
	
	
	@After("execution(* siru.md.service.SampleLogService*.*(..))")
	public void logAfer() {
		log.info("logAfter() ����");
	}
	
	@AfterThrowing(pointcut = "execution(* siru.md.service.SampleLogService*.*(..))", throwing="exception")
	public void logAfterThrowing(Exception exception) {
		log.info(">>>>>logAfterThrowing() ���� exception : " + exception);
	}
}
