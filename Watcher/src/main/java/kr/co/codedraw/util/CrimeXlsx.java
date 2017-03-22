package kr.co.codedraw.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CrimeXlsx {
	
	private int testTotalRow;		// 액셀에서 행 갯수
	private int testTotalCell = 12;		// 액셀에서 열 갯수
	private int crimeTotalRow = 12;		// 범죄건수 담을 배열의 행
	private int crimeTotalCol = 4;		// 범죄건수 담을 배열의 열
	private int[][] test;
	
	
	public int compareRegionName(String region){
		int sheetPage = -1;
		RegionCode regionCode = new RegionCode();
		HashMap <String, String> sheetCode = new HashMap<String, String>();
		sheetCode = regionCode.getSheetCode();
		sheetPage = Integer.parseInt(sheetCode.get(region));
		
		return sheetPage;
	}
	
	public int[][] CrimeXlsx(String region) {
		try {
			
			File file = new File("C:/Users/Administrator/Desktop/CodeDraw/crime.xlsx");
			//File file = new File("~"+ File.separator + "watcher" + File.separator + "apache-tomcat-7.0.72" + File.separator + "webapps" + File.separator + "xlsx" + File.separator +"crime.xlsx");
			XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file));
			
			int sheetPage = compareRegionName(region);	// 엑셀에서 한글로된 시트이름을 숫자로 변환
			XSSFSheet sheet = wb.getSheetAt(sheetPage);	// 현재 설정된 sheetPage의 시트를 가져옴
			testTotalRow = sheet.getPhysicalNumberOfRows();	// 해당 sheetPage의 전체 행 개수를 가져옴
			
			test = new int[testTotalRow][testTotalCell];	// 엑셀에서 [전체행][월=12]
			for (int rowIdx = 0; rowIdx < testTotalRow; rowIdx++) {
				XSSFRow row = sheet.getRow(rowIdx);		// rowIdx번째의 행을 읽어옴
				for (int cellIdx = 0; cellIdx < testTotalCell; cellIdx++) {
					XSSFCell cell = row.getCell(cellIdx);	// 읽어온 행의 cellIdx번째 열읽어옴
					if (cell == null)
						continue;
					test[rowIdx][cellIdx] = (int) Double.parseDouble(cell.toString());
				}
			}
		} catch (FileNotFoundException fe) {
			System.out.println("fileNot");
		} catch (IOException ie) {
			System.out.println("IOException");
		}
		return test;
	}
	

	public double[][][] readCrimeXlsx(String region) {
		
		// [5대범죄][월][몇 (4)년치] ( 범죄순서 : 절도, 살인, 강도, 강간, 폭행)
		double [][][] crime = new double [5][crimeTotalRow][crimeTotalCol];
		
		CrimeXlsx(region);
		// 엑셀에서 가져온 데이터를 범죄별로 배열에 넣기
		for (int h = 0; h < 5; h++) {
			for (int i = 0; i < crimeTotalRow; i++) {
				for (int j = 0, k = 0; j < crimeTotalCol; j++) {
					crime[h][i][j] = test[k + h][i];	// 엑셀에서 가져온 값을 3차원배열형식(회귀분석에 맞는 형식)으로 재배열
					k += 5;
				}
			}
		}
		return crime;
	}
	public double[][] preYearCrime(String region){
		// [5대범죄][월]
		double[][] preYearCrime = new double[5][12];
		CrimeXlsx(region);
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < 12; j++){
				preYearCrime[i][j] = test[i + 20][j];	// 엑셀에서 가져온 값에서 행이 21번째(2014년) 부터시작해서 5개 행의 값만 가져옴 
			}
		}
		return preYearCrime;
	}
	
}