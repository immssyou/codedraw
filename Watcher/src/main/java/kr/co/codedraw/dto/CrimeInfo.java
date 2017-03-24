package kr.co.codedraw.dto;

public class CrimeInfo {
	private Long cNo;
	private String areaCode;
	private String areaName;
	private float crimeRate;
	
	public Long getcNo() {
		return cNo;
	}
	public void setcNo(Long cNo) {
		this.cNo = cNo;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public float getCrimeRate() {
		return crimeRate;
	}
	public void setCrimeRate(float crimeRate) {
		this.crimeRate = crimeRate;
	}
	
	
}
