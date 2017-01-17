package kr.co.lifePan.web.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.util.StopWatch;

@Aspect
public class ProfilingAspect {

	private static Logger logger = Logger.getLogger(ProfilingAspect.class);

	@Pointcut("execution(public * kr.co.lifePan.web.service.*.*(..))")
	public void serviceMethods() {
	}

	@Pointcut("execution(public * kr.co.lifePan.web.controller.*.*(..))")
	public void controllerMethods() {
	}

	@Pointcut("serviceMethods() || controllerMethods()")
	public void profileMethods() {
	} 

	@Around("controllerMethods()")
	public Object profile(ProceedingJoinPoint jonPoint) throws Throwable {
		Signature signature = jonPoint.getSignature();
		StopWatch stopWatch = null;
		try {
			if (logger.isTraceEnabled()) {
				stopWatch = new StopWatch(signature.toShortString());
				stopWatch.start(jonPoint.getSignature().getName());
			} 
			Object reault = jonPoint.proceed();
			return reault;
		} finally {
			if (logger.isTraceEnabled()) {
				stopWatch.stop();
				logger.trace(stopWatch.prettyPrint());
			}
		}
	}
}