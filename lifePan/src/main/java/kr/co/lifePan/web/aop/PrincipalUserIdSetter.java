package kr.co.lifePan.web.aop;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import kr.co.lifePan.web.security.CustomUserDetails;

public class PrincipalUserIdSetter implements MethodInterceptor {

	private static Logger logger = Logger.getLogger(PrincipalUserIdSetter.class);
	
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		
/*		for(Object arg : invocation.getArguments()) {  
			if(arg instanceof StatisticsTableCommand) {
				logger.info("StatisticsTableCommand Parameter Setter UserIds");
				
				StatisticsTableCommand stc = (StatisticsTableCommand)arg;
				
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				if(authentication.getPrincipal() instanceof CustomUserDetails) {
					CustomUserDetails details = (CustomUserDetails)authentication.getPrincipal();					
					stc.setUserIds(details.getMembers());
				}
				break;
			}
		}	*/
		
		return invocation.proceed();
	}	
}
