package kr.co.codedraw.hive.mining;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.List;

import kr.co.codedraw.hive.dto.HiveDto;
import kr.co.shineware.nlp.komoran.core.analyzer.Komoran;
import kr.co.shineware.util.common.model.Pair;

public class HiveMining {
	
	// 한국어 형태소 분석.
	public static ArrayList<HiveDto> morphemeAnalyze(ArrayList<HiveDto> resList) {
		ArrayList<HiveDto> resMorphemeList = new ArrayList<HiveDto>();
		try {
			System.out.println("\nRunning : twitter_ko 테이블 데이터의 형태소 분석을 시작합니다.");
			
			// /Users/dongwookYang/Desktop/개발/빅데이터/codedraw/watcher_dataCenter/lib/AnalyzerLib/models
			Komoran komoran = new Komoran("/home/big/watcher_dataCenter/lib/AnalyzerLib/models");

			for (HiveDto resTempData : resList) {
				String resContent = resTempData.getContent();
				if (null != resContent && !resContent.isEmpty()) {
					List<List<Pair<String, String>>> result = komoran.analyze(resContent);
					for (List<Pair<String, String>> eojeolResult : result) {
						for (Pair<String, String> wordMorph : eojeolResult) {
							if(wordMorph.getSecond().equals("NNG") && wordMorph.getFirst().length() != 1) {
								HiveDto temp = new HiveDto();
								String FirstWord = wordMorph.getFirst();
								temp.setContent(FirstWord);
								resMorphemeList.add(temp);
								System.out.println("Running : 형태소 분석 중 - " + FirstWord);
							}
						}
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("\nRunning : twitter_ko 테이블 데이터의 형태소 분석을 완료하였습니다.\n");
		return resMorphemeList;
	}
	
	// 결과 파일로 출력하기.
	public static boolean createFile(String yesterday, String fileName, String countryCode, ArrayList<HiveDto> resList) {
		boolean result = false;
		
		try {
			System.out.println("\nRunning : criminal_twitter_" + countryCode + " 데이터 파일의 아카이브를 시작합니다.");
			File targetFile = new File(File.separator + "home" + File.separator + "big" + File.separator + "criminal_twitter_data" + File.separator + countryCode + File.separator + "criminal_" + fileName + yesterday + ".txt");
			
			if(!targetFile.exists()) {
				targetFile.createNewFile();							
			}
			
			BufferedWriter output = new BufferedWriter(new FileWriter(targetFile.getPath(), true));
			
			for(HiveDto temp : resList) {
				output.write("");
				output.write("$");
				
				output.write(temp.getContent());
				output.write("$");
					
				output.write("");
				output.write("$");
				output.newLine();
			}
			output.close();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("\nRunning : criminal_twitter_" + countryCode + " 데이터 파일 아카이브가 완료되었습니다.\n");
		return result;
	}
}
