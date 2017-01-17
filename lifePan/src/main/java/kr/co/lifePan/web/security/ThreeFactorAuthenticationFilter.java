package kr.co.lifePan.web.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import kr.co.lifePan.web.utility.StringUtil;

public class ThreeFactorAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private String extraParameter1Name = "extra1";
	private String extraParameter2Name = "extra2";
	private String delimiter = ":";

	@Override
	protected String obtainUsername(HttpServletRequest request) {
		String username = request.getParameter(getUsernameParameter());
		String extraParameter1Value = request.getParameter(getExtraParameter1Name());
		String extraParameter2Value = request.getParameter(getExtraParameter2Name());
		String combinedUsername = username + getDelimiter() + (StringUtil.isEmptyString(extraParameter1Value) ? "" : extraParameter1Value) + getDelimiter()
				+ (StringUtil.isEmptyString(extraParameter2Value) ? "" : extraParameter2Value);
		return combinedUsername;
	}

	public String getExtraParameter1Name() {
		return extraParameter1Name;
	}

	public void setExtraParameter1Name(String extraParameter1Name) {
		this.extraParameter1Name = extraParameter1Name;
	}

	public String getExtraParameter2Name() {
		return extraParameter2Name;
	}

	public void setExtraParameter2Name(String extraParameter2Name) {
		this.extraParameter2Name = extraParameter2Name;
	}

	public String getDelimiter() {
		return this.delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}
}