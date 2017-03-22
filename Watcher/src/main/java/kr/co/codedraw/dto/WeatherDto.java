package kr.co.codedraw.dto;

public class WeatherDto {
	private int wId;
	private String date;
	private double temperature;
	private double humidity;
	private String regionCodeNum;
	public String getRegionCodeNum() {
		return regionCodeNum;
	}
	public void setRegionCodeNum(String regionCodeNum) {
		this.regionCodeNum = regionCodeNum;
	}
	public int getwId() {
		return wId;
	}
	public void setwId(int wId) {
		this.wId = wId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public double getHumidity() {
		return humidity;
	}
	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}
}