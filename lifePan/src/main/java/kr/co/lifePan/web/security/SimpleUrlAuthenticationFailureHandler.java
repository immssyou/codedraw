package kr.co.lifePan.web.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.security.core.AuthenticationException;

public class SimpleUrlAuthenticationFailureHandler extends org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler {

	private String otpLoginFormUrl;

	public SimpleUrlAuthenticationFailureHandler() {
		super();
	}

	public SimpleUrlAuthenticationFailureHandler(String defaultFailureUrl) {
		super(defaultFailureUrl);
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		if (exception instanceof OneTimePasswordNeedsException && otpLoginFormUrl != null) {
			saveException(request, exception);

			if (isUseForward()) {
				logger.debug("Forwarding to " + otpLoginFormUrl);
				request.getRequestDispatcher(otpLoginFormUrl).forward(request, response);
			} else {
				logger.debug("Redirecting to " + otpLoginFormUrl);
				getRedirectStrategy().sendRedirect(request, response, otpLoginFormUrl);
			}
		} else {
			
			super.onAuthenticationFailure(request, response, exception);
		}
	}

	public String getOtpLoginFormUrl() {
		return otpLoginFormUrl;
	}

	public void setOtpLoginFormUrl(String otpLoginFormUrl) {
		this.otpLoginFormUrl = otpLoginFormUrl;
	}
}
