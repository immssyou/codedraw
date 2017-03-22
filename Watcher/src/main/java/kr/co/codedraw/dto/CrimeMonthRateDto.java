package kr.co.codedraw.dto;

public class CrimeMonthRateDto {
	private int mId;
	private String regionCodeNum;
	private String date;
	private double monthRate;
	public int getmId() {
		return mId;
	}
	public void setmId(int mId) {
		this.mId = mId;
	}
	public String getRegionCodeNum() {
		return regionCodeNum;
	}
	public void setRegionCodeNum(String regionCodeNum) {
		this.regionCodeNum = regionCodeNum;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getMonthRate() {
		return monthRate;
	}
	public void setMonthRate(double monthRate) {
		this.monthRate = monthRate;
	}
}
