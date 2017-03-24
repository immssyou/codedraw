package kr.co.codedraw.dto;

public class CrimeDto {
	private int cId;
	private String date;
	private double crimeRate;
	private String regionCodeNum;
	private String crimeCodeNum;
	private double crimeCount;
	private int wId;
	public double getCrimeCount() {
		return crimeCount;
	}
	public int getwId() {
		return wId;
	}
	public void setwId(int wId) {
		this.wId = wId;
	}
	public void setCrimeCount(double crimeCount) {
		this.crimeCount = crimeCount;
	}
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getCrimeRate() {
		return crimeRate;
	}
	public void setCrimeRate(double crimeRate) {
		this.crimeRate = crimeRate;
	}
	public String getRegionCodeNum() {
		return regionCodeNum;
	}
	public void setRegionCodeNum(String regionCodeNum) {
		this.regionCodeNum = regionCodeNum;
	}
	public String getCrimeCodeNum() {
		return crimeCodeNum;
	}
	public void setCrimeCodeNum(String crimeCodeNum) {
		this.crimeCodeNum = crimeCodeNum;
	}
	
}