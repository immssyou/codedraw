package kr.co.lifePan.web.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public interface Role {

	// System Administrator
	public static final GrantedAuthority ADMIN = new SimpleGrantedAuthority("ROLE_ADMIN");

	// Analyst 
	// 여러 조직과 엔지니어를 거느릴 수 있다. 
	// 엔지니어의 역할을 할 수 도 있다.
	public static final GrantedAuthority MANAGER = new SimpleGrantedAuthority("ROLE_MANAGER");
	
	// Engineer
	// 모바일 기기를 가지고 DM 데이터를 수집한다. 
	public static final GrantedAuthority USER = new SimpleGrantedAuthority("ROLE_USER");

}
