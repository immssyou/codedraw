package kr.co.lifePan.web.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.ibatis.type.Alias;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import kr.co.lifePan.web.utility.serializer.FullDateSerializer;

@Alias("userOTP")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserOTP {

	private String otpId;
	private String otpPassword;
	private String otPswd;
	private Date otDate;
	private Long ttl;

	public String getOtpId() {
		return otpId;
	}

	public void setOtpId(String otpId) {
		this.otpId = otpId;
	}

	public String getOtpPassword() {
		return otpPassword;
	}

	public void setOtpPassword(String otpPassword) {
		this.otpPassword = otpPassword;
	}

	public String getOtPswd() {
		return otPswd;
	}

	public void setOtPswd(String otPswd) {
		this.otPswd = otPswd;
	}

	@JsonSerialize(using = FullDateSerializer.class)
	public Date getOtDate() {
		return otDate;
	}

	public void setOtDate(Date otDate) {
		this.otDate = otDate;
	}

	public Long getTtl() {
		return ttl;
	}

	public void setTtl(Long ttl) {
		this.ttl = ttl;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}
}
