package kr.co.codedraw.util;

import java.util.HashMap;

public class RegionCode {
	
	private HashMap <String,String> regionCode;
	private HashMap <String,String> sheetCode;
	public String[] regionName = {"서울", "부산", "대구", "인천", "광주", "대전", "울산", "경기",
			"강원", "충북", "충남", "전북", "전남", "경북", "경남", "제주"};
	public String[] regionNum = {"02", "051", "053", "032", "062", "042", "052", "031",
			"033", "043", "041", "063", "061", "054", "055", "064"};
	
	public RegionCode(){
		regionCode = new HashMap <String, String>();
		sheetCode = new HashMap <String, String>();
		
		int[] sheetPage = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}; 
		
		for(int i = 0; i < regionName.length ; i++){
			regionCode.put(regionName[i], regionNum[i]);
			sheetCode.put(regionNum[i], String.valueOf(sheetPage[i]));
		}
	}
	
	public HashMap<String, String> getSheetCode() {
		return sheetCode;
	}

	public void setSheetCode(HashMap<String, String> sheetCode) {
		this.sheetCode = sheetCode;
	}

	public HashMap<String, String> getRegionCode() {
		return regionCode;
	}
	
	public void setRegionCode(HashMap<String, String> regionCode) {
		this.regionCode = regionCode;
	}
	
}