package kr.co.lifePan.web.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUserDetails extends User {

	private static final long serialVersionUID = 1L;
	private kr.co.lifePan.web.domain.User user;
	private List<String> members;

	public CustomUserDetails(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked, authorities);
	}

	public kr.co.lifePan.web.domain.User getUser() {
		return user;
	}

	public void setUser(kr.co.lifePan.web.domain.User user) {
		this.user = user;
	}
	
	public List<String> getMembers() {
		return members;
	}
	
	public void setMembers(List<String> members) {
		this.members = members;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString()).append(": ");
		sb.append("Username: ").append(super.getUsername()).append("; ");
		sb.append("Password: [PROTECTED]; ");
		sb.append("Enabled: ").append(super.isEnabled()).append("; ");
		sb.append("AccountNonExpired: ").append(super.isAccountNonExpired())
				.append("; ");
		sb.append("credentialsNonExpired: ")
				.append(super.isCredentialsNonExpired()).append("; ");
		sb.append("AccountNonLocked: ").append(super.isAccountNonLocked())
				.append("; ");

		if (!super.getAuthorities().isEmpty()) {
			sb.append("Granted Authorities: ");

			boolean first = true;
			for (GrantedAuthority auth : super.getAuthorities()) {
				if (!first) {
					sb.append(",");
				}
				first = false;

				sb.append(auth);
			}
		} else {
			sb.append("Not granted any authorities");
		}

		return sb.toString();
	}
}
