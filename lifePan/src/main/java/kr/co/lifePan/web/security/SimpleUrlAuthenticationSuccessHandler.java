package kr.co.lifePan.web.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;

import kr.co.lifePan.web.utility.StringUtil;

public class SimpleUrlAuthenticationSuccessHandler extends org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler {

//	@Autowired
//	private UserDao userDao;

	public SimpleUrlAuthenticationSuccessHandler() {
		super();
	}

	public SimpleUrlAuthenticationSuccessHandler(String defaultTargetUrl) {
		super(defaultTargetUrl);
	}

	@Override
//	@Transactional(propagation = Propagation.REQUIRED)
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			  Authentication authentication) throws IOException, ServletException {

//		if (authentication.getAuthorities().contains(Role.ADMIN)) {
//			managerDao.insertAccessLog(authentication.getName(), request);
//		}
		
		String valid = request.getParameter("valid");
		if(StringUtil.isEmptyString(valid) == false) {
			clearAuthenticationAttributes(request);			
			response.sendRedirect(super.getDefaultTargetUrl() + "?valid=" + valid);
		}

		super.onAuthenticationSuccess(request, response, authentication);
	}
}
