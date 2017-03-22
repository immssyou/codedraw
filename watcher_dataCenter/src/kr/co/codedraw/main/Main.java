package kr.co.codedraw.main;

import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;


import java.util.ArrayList;

import kr.co.codedraw.hive.dao.HiveDao;
import kr.co.codedraw.hive.dto.HiveDto;
import kr.co.codedraw.hive.dto.WordDto;
import kr.co.codedraw.hive.mining.HiveMining;
import kr.co.codedraw.hive.sqlcon.SQLConn;

public class Main {
	final static String[] countryCode = {"ko", "de", "en", "ja", "fr", "ru"};
	final static String[] countryCodeNm = {"001", "002", "003", "004", "005", "006"};
	static HashMap<String, String> fileName = new HashMap<String, String>();
	static HashMap<String, String []> keyword = new HashMap<String, String []>();
	
	public static void main(String[] args) throws Exception {
		HiveDao hDao = new HiveDao();
		SQLConn sConn = new SQLConn();
		
		hDao.connect();
		
		setUpFileName();
		setUpKeyword();
		String yesterday = setUpYesterday();
		
		for(int index = 0; index < countryCode.length; index++) {
			ArrayList<HiveDto> resKeywordList;
			ArrayList<WordDto> resWordCntList;
			String tableName = "twitter_" + countryCode[index];
			
			hDao.createTable(tableName);
			
			hDao.insertData("'/home/big/twitter_data/" + countryCode[index] + "/" + fileName.get(countryCode[index]) + yesterday + ".txt'", tableName, yesterday);
			
			resKeywordList = hDao.getCriminalKeyword(tableName, yesterday, keyword.get(countryCode[index]));
			
			if(countryCode[index].equals("ko")) {
				resKeywordList = HiveMining.morphemeAnalyze(resKeywordList);
			}
			
			hDao.createTable("criminal_" + tableName);
			HiveMining.createFile(yesterday, fileName.get(countryCode[index]), countryCode[index], resKeywordList);
			hDao.insertData("'/home/big/criminal_twitter_data/" + countryCode[index] + "/" + "criminal_" + fileName.get(countryCode[index]) + yesterday + ".txt'", "criminal_" + tableName, yesterday);
			
			resWordCntList = hDao.getWordCountData("criminal_" + tableName);
			sConn.startConnect("jdbc:mysql://14.63.217.11:3306/watcher", "codedraw", "codedraw");
			sConn.insertDB(countryCodeNm[index], resWordCntList, yesterday);
		}
		
		hDao.close();
		sConn.close();
	}
	
	private static void setUpFileName() {
		fileName.put(countryCode[0], "twitter_korea_output_");
		fileName.put(countryCode[1], "twitter_german_output_");
		fileName.put(countryCode[2], "twitter_america_output_");
		fileName.put(countryCode[3], "twitter_japan_output_");
		fileName.put(countryCode[4], "twitter_france_output_");
		fileName.put(countryCode[5], "twitter_russia_output_");
	}
	
	private static void setUpKeyword() {
		keyword.put("ko", new String [] {"범죄", "범죄의", "테러", "강간", "살인", "폭행", "절도", "성폭행", "방화", "납치",
										"isis", "ISIS", "강도", "성매매", "감금", "성추행", "학대"});
		keyword.put("de", new String [] {"kriminalität", "verbrecher", "terror", "vergewaltigen","mord", "gewalt", "diebstahl", "Kriminalität","Verbrecher", "Terror", "Vergewaltigen", "Mord", "Gewalt", "Diebstahl"});
		keyword.put("en", new String [] {"crime", "criminal", "terror", "rape", "murder", "violence", "theft", "Crime", "Criminal", "Terror", "Rape", "Murder", "Violence", "Theft"});
		keyword.put("ja", new String [] {"犯罪", "はんざい", "ハンザイ", "犯罪の", "はんざいの", "ハンザイノ", "テラー", "テロ", "レイプ", "強姦", "殺人", "さつじん", "サツジン", "暴力", "ぼうりょく", "ボウリョク", "窃盗", "せっとう", "セットウ"});
		keyword.put("fr", new String [] {"la criminalité", "criminel", "terreur", "râpé", "Meurtre", "violence"});
		keyword.put("ru", new String [] {"преступление", "преступник", "террор", "изнасилование", "убийство", "насилие", "кража"});
	}
	
	private static String setUpYesterday() {
		Calendar calendars = new GregorianCalendar();
		calendars.add(Calendar.DATE, -1);
		SimpleDateFormat simpleYesterday = new SimpleDateFormat("yyyyMMdd");
		String yesterday = simpleYesterday.format(calendars.getTime());
		
		return yesterday;
	}
}
