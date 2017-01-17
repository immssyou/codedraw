package kr.co.lifePan.web.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import kr.co.lifePan.web.utility.StringUtil;

public class TwoFactorAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private String extraParameterName = "extra";
	private String delimiter = ":";

	@Override
	protected String obtainUsername(HttpServletRequest request) {
		String username = request.getParameter(getUsernameParameter());
		if (!StringUtil.isEmptyString(getExtraParameterName())) {
			String extraParameterValue = request.getParameter(getExtraParameterName());
			String combinedUsername = username + (StringUtil.isEmptyString(extraParameterValue) ? "" : getDelimiter() + extraParameterValue);
			return combinedUsername;
		} else {
			return username;
		}
	}

	public String getExtraParameterName() {
		return extraParameterName;
	}

	public void setExtraParameterName(String extraParameterName) {
		this.extraParameterName = extraParameterName;
	}

	public String getDelimiter() {
		return this.delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}
}