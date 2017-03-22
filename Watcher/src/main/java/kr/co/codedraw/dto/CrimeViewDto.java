package kr.co.codedraw.dto;

public class CrimeViewDto {
	private Long wid;
	private String cDate;
	private Double temperature;
	private Double humidity;
	private Long cid;
	private Double crimeRate;
	private Integer crimeCount;
	private String crimeCodeNum;
	private String regionCode;
	private String regionName; 
	
	public Long getWid() {
		return wid;
	}
	public void setWid(Long wid) {
		this.wid = wid;
	}
	public String getcDate() {
		return cDate;
	}
	public void setcDate(String cDate) {
		this.cDate = cDate;
	}
	public Double getTemperature() {
		return temperature;
	}
	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}
	public Double getHumidity() {
		return humidity;
	}
	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}
	public Long getCid() {
		return cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
	}
	public Double getCrimeRate() {
		return crimeRate;
	}
	public void setCrimeRate(Double crimeRate) {
		this.crimeRate = crimeRate;
	}
	public Integer getCrimeCount() {
		return crimeCount;
	}
	public void setCrimeCount(Integer crimeCount) {
		this.crimeCount = crimeCount;
	}
	public String getCrimeCodeNum() {
		return crimeCodeNum;
	}
	public void setCrimeCodeNum(String crimeCodeNum) {
		this.crimeCodeNum = crimeCodeNum;
	}
	public String getRegionCode() {
		return regionCode;
	}
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	
	
}
