package kr.co.codedraw.dto;

public class WordDto {
	private int pId;
	private String countryCode;
	private String word;
	private int frequency;
	private String date;
	private String countryName;
	
	public WordDto(){
		
	}
	public WordDto(int pId, String countryCode, String word, int frequency, String date, String countryName) {
		this.pId = pId;
		this.countryCode = countryCode;
		this.word = word;
		this.frequency = frequency;
		this.date = date;
		this.countryName = countryName;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
}
