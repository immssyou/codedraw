package kr.co.lifePan.web.security;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class OneTimePasswordNeedsException extends UsernameNotFoundException {

	public OneTimePasswordNeedsException(String msg) {
		super(msg);
	}

	@Deprecated
	public OneTimePasswordNeedsException(String msg, Object extraInformation) {
		super(msg, extraInformation);
	}

	public OneTimePasswordNeedsException(String msg, Throwable t) {
		super(msg, t);
	}
}
