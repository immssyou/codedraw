package kr.co.codedraw.dto;

public class CrimeCountDto {
	private String preYearDate;
	private String presentDate;
	private String regionCodeNum;
	
	public String getPreYearDate() {
		return preYearDate;
	}
	public void setPreYearDate(String preYearDate) {
		this.preYearDate = preYearDate;
	}
	public String getPresentDate() {
		return presentDate;
	}
	public void setPresentDate(String presentDate) {
		this.presentDate = presentDate;
	}
	public String getRegionCodeNum() {
		return regionCodeNum;
	}
	public void setRegionCodeNum(String regionCodeNum) {
		this.regionCodeNum = regionCodeNum;
	}
}
