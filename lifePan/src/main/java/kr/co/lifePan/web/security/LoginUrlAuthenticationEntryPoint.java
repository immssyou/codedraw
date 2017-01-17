package kr.co.lifePan.web.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;

public class LoginUrlAuthenticationEntryPoint extends org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint {

	private String ajaxLoginFormUrl;

	public LoginUrlAuthenticationEntryPoint(String loginFormUrl) {
		super(loginFormUrl);
	}

	protected boolean isAjaxRequest(HttpServletRequest request, HttpServletResponse response) {
		if ("XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))) {
			return true;
		}
		if (response != null && response.getContentType() != null && response.getContentType().toLowerCase().startsWith("application/json")) {
			return true;
		}
		return false;
	}

	@Override
	protected String determineUrlToUseForThisRequest(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
		if (isAjaxRequest(request, response)) {
			return getAjaxLoginFormUrl();
		}

		return getLoginFormUrl();
	}

	public String getAjaxLoginFormUrl() {
		return ajaxLoginFormUrl;
	}

	public void setAjaxLoginFormUrl(String ajaxLoginFormUrl) {
		this.ajaxLoginFormUrl = ajaxLoginFormUrl;
	}
}
