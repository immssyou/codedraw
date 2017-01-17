package kr.co.lifePan.web.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.ibatis.type.Alias;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import kr.co.lifePan.web.security.Role;
import kr.co.lifePan.web.utility.serializer.FullDateSerializer;
 
@Alias("user")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "{validation.required}")
	@Length(min = 4, max = 30, message = "{validation.min.max.length}")
	private String id;
	@Length(min = 6, max = 50, message = "{validation.min.max.length}")
	private String pswd;
	@Length(min = 6, max = 50, message = "{validation.min.max.length}")
	private String newPswd;
	@Length(min = 3, max = 40, message = "{validation.min.max.length}")
	private String name;
	private String grade;
	@Email(message = "{validation.email.invalid}")
	@Length(max = 80, message = "{validation.max.length}")
	private String email;
	@Pattern(regexp = "[Y|N]", message = "{validation.pattern.invalid}")
	private String useStatus;
	
	private Integer orgid;
	
	private String teamCode;
	private String teamName;
	private String fieldOfficeName;
	private String headOfficeName;

	private Date registDate;
	
	private String fullname;
	
	private String kmlbts;
	private Integer userNo;
	private String userId;
	private Integer studentNo;

	public String getKmlbts() {
		return kmlbts;
	}

	public void setKmlbts(String kmlbts) {
		this.kmlbts = kmlbts;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonIgnore
	public String getPswd() {
		return pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	public String getNewPswd() {
		return newPswd;
	}

	public void setNewPswd(String newPswd) {
		this.newPswd = newPswd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonSerialize(using = FullDateSerializer.class)
	public Date getRegistDate() {
		return registDate;
	}

	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}

	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	public boolean isUse() {
		if ("Y".equalsIgnoreCase(getUseStatus())) {
			return true;
		}
		return false;
	}

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getFieldOfficeName() {
		return fieldOfficeName;
	}

	public void setFieldOfficeName(String fieldOfficeName) {
		this.fieldOfficeName = fieldOfficeName;
	}

	public String getHeadOfficeName() {
		return headOfficeName;
	}

	public void setHeadOfficeName(String headOfficeName) {
		this.headOfficeName = headOfficeName;
	}

	public Integer getOrgid() {
		return orgid;
	}

	public void setOrgid(Integer orgid) {
		this.orgid = orgid;
	}
	
	public Integer getUserNo() {
		return userNo;
	}

	public void setUserNo(Integer userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public Integer getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(Integer studentNo) {
		this.studentNo = studentNo;
	}

	public boolean isAdmin() {
		return Role.ADMIN.getAuthority().equalsIgnoreCase(getGrade());
	}

	public boolean isManager() {
		return Role.MANAGER.getAuthority().equalsIgnoreCase(getGrade());
	}

	public boolean isUser() {
		return Role.USER.getAuthority().equalsIgnoreCase(getGrade());
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}
}
