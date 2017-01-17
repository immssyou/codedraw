/**
 * Provides the classes necessary to create an  
 * applet and the classes an applet uses 
 * to communicate with its applet context.
 * <p>
 * The applet framework involves two entities:
 * the applet and the applet context.
 * An applet is an embeddable window (see the
 * {@link java.awt.Panel} class) with a few extra
 * methods that the applet context can use to 
 * initialize, start, and stop the applet.
 *
 * @since 1.0
 * @see java.awt
 */

package kr.co.lifePan.web.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String arg0)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
/*
	private String delimiter;
	@Autowired
	private UserDao userDao;
	@Autowired
	private MailService mailService;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.NOT_SUPPORTED)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		String password = null;
		String onetimepassword = null;

		if (!StringUtil.isEmptyString(delimiter)) {
			String[] split = username.split(delimiter);
			if (split.length < 2) {
				throw new UsernameNotFoundException("Must specify both username and password");
			}

			username = split[0];
			password = split[1];
			if (split.length == 3) {
				onetimepassword = split[2];
			}
		}

		User user = userDao.selectOne(username);
		List<String> members = null;

		 PHAROS WAS 시스템에서 사용자 조회 
		if (user != null) {
			Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			if (user.isUse()) {
				authorities.add(Role.USER);
				members = Arrays.asList(user.getId());
				if (user.isManager()) {
					authorities.add(Role.MANAGER);
					members = userDao.listMembers(user.getId());
				} else if (user.isAdmin()) {
					authorities.add(Role.MANAGER);
					authorities.add(Role.ADMIN);
					members = null;
				}
				CustomUserDetails userDetails = new CustomUserDetails(user.getId(), user.getPswd(), true, true, true, true, authorities);
				userDetails.setUser(user);
				userDetails.setMembers(members);
				
				return userDetails;
			}
		} 
		 CUSTOMIZING FOR KT 
		else {
			user = userDao.getManager(username);
			 연동 시스템에서 관리자 조회 
			if (user != null) {
				Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

				if (user.isUse()) {
					authorities.add(Role.USER);
					if (user.isManager()) {
						authorities.add(Role.MANAGER);
						members = userDao.listMembers(user.getId());
					} else if (user.isAdmin()) {
						authorities.add(Role.MANAGER);
						authorities.add(Role.ADMIN);
					}
				}
				
				CustomUserDetails userDetails = new CustomUserDetails(user.getId(), user.getPswd(), true, true, true, true, authorities);
				userDetails.setUser(user);
				userDetails.setMembers(members);

				return userDetails;
			} 
			 연동 시스템에서 엔지니어 조회 
			else {
				user = userDao.selectSdmUser(username, password); // email, idNumber인데... 이 뭐꼬?
				if (user != null) {
					UserOTP userOTP = userDao.selectOTPOne(username, password);
					if (userOTP == null || (userOTP != null && userOTP.getTtl() > 300)) {
						userDao.deleteOTP(username, password);
						userOTP = new UserOTP();
						userOTP.setOtpId(username);
						userOTP.setOtpPassword(password);
						*//**
						 * 2012-10-17 클라이언트 요청사항: 큐비콤 김지연 요청사항 Otp복잡한 단계를 줄여달라는
						 * 요구사항 처리
						 * userOTP.setOtPswd(OTPUtil.generatePassword(10));
						 *//*
						userOTP.setOtPswd(OTPUtil.generatePassword(4));
						userDao.insertOTP(userOTP);

						mailService.sendOneTimePasswordMail(user, userOTP);

						throw new OneTimePasswordNeedsException("otp created");
					} else if (userOTP != null) {
						if (StringUtil.isEmptyString(onetimepassword)) {
							throw new OneTimePasswordNeedsException("otp needed");
						}
						if (!onetimepassword.equals(userOTP.getOtPswd())) {
							throw new OneTimePasswordNeedsException("otp mismatch");
						}
					}

					userDao.deleteOTP(username, password);

					List<OrgArea> orgAreas = user.getOrgAreas();

					if (orgAreas != null && orgAreas.size() == 0) {
						user.setOrgAreas(null);
					}

					Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

					authorities.add(Role.USER);

					CustomUserDetails userDetails = new CustomUserDetails(user.getId(), user.getPswd(), true, true, true, true, authorities);
					userDetails.setUser(user);

					return userDetails;
				}
			}
		}

		throw new UsernameNotFoundException("user not found");
	}

	public String getDelimiter() {
		return this.delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}*/
}
