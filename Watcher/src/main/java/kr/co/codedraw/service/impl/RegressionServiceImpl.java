package kr.co.codedraw.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.codedraw.dao.CrimeDao;
import kr.co.codedraw.dao.WeatherDao;
import kr.co.codedraw.dto.CrimeDto;
import kr.co.codedraw.dto.WeatherDto;
import kr.co.codedraw.service.RegressionService;
import kr.co.codedraw.util.CrimeXlsx;
import kr.co.codedraw.util.RegionCode;
import kr.co.codedraw.util.RegressionMatrix;
import kr.co.codedraw.util.WeatherXlsx;

@Component
public class RegressionServiceImpl implements RegressionService {
	
	@Autowired
	private CrimeDao crimeDao;
	
	@Autowired
	private WeatherDao weatherDao;
	
	@Override
	public List<CrimeDto> readCrimeRate() {
		List<CrimeDto> crimeList = new ArrayList<CrimeDto>();
		crimeList = crimeDao.readCrimeRate(); 
		return crimeList;
	}

	@Override
	public void writeCrimeRate(CrimeDto crimeDto) {
		crimeDao.writeCrimeRate(crimeDto);
		
	}

	@Override
	public void writeHistoryCrimeRate(CrimeDto crimeDto) {
		crimeDao.writeHistoryCrimeRate(crimeDto);
		
	}

	@Override
	public void writeCrimeCount(CrimeDto crimeDto) {
		crimeDao.writeCrimeCount(crimeDto);
		
	}
	
	@Override
	public List<WeatherDto> readWeather() {
		List<WeatherDto> weatherList = new ArrayList<WeatherDto>();
		weatherList = weatherDao.readWeather();
		return weatherList;
	}

	@Override
	public void writeWeather(WeatherDto weatherDto) {
		weatherDao.writeWeather(weatherDto);
	}
	
	@Override
	public void updateAutoIncrementCrimeRate() {
		crimeDao.updateAutoIncrementCrimeRate();
	}

	@Override
	public void deleteCrimeRate() {
		crimeDao.deleteCrimeRate();
	}

	@Override
	public void updateAutoIncrementCrimeCount() {
		crimeDao.updateAutoIncrementCrimeCount();
	}

	@Override
	public void deleteCrimeCount() {
		crimeDao.deleteCrimeCount();
	}

	@Override
	public void updateAutoIncrementWeather() {
		weatherDao.updateAutoIncrementWeather();
		
	}

	@Override
	public void deleteWeather() {
		weatherDao.deleteWeather();
	}
	
	
	
	
	@Override
	public void writeMonthRate(CrimeDto crimeDto) {
		crimeDao.writeMonthRate(crimeDto);
	}

	//(fixedDelay = 1000 * 15) = 15 초 마다
	//@Scheduled(cron = "0 59 23 * * *")
	// 서버 시작하자마 이 함수가 바로 시작되도록 하는 어노테이션 @PostConstruct
	//@PostConstruct	
	public void scheduleRun(){
		
		/*Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
		String presentDate = simpleDateFormat.format(date);*/
		
		CrimeXlsx crimeXlsx = new CrimeXlsx();
		WeatherXlsx weatherXlsx = new WeatherXlsx();
		RegionCode regionCode = new RegionCode();
		
		String[] crimeName = {"절도", "살인", "강도", "강간", "폭행"};
		String[] crimeCodeNum = {"001", "002", "003", "004", "005"};	// theft, murder, robbery, rape, assault
		HashMap <String, String> region = regionCode.getRegionCode();
	
		/*deleteCrimeRate();			// 올해 범죄율만 저장하도록 기존 데이터 삭제
		updateAutoIncrementCrimeRate();		// crimeRate_table은 cId값이 1부터 증가하도록 초기화
		deleteCrimeCount();
		updateAutoIncrementCrimeCount();
		deleteWeather();
		updateAutoIncrementWeather();*/
		
		int regionCodeNumCount = 0;		// 16개 행정구를 위한 변수
		while(regionCodeNumCount < 16){
			double[][][] weather = weatherXlsx.readWeatherXlsx(region.get(regionCode.regionName[regionCodeNumCount]));
			double[][][] crime = crimeXlsx.readCrimeXlsx(region.get(regionCode.regionName[regionCodeNumCount]));	// 회귀분석에 사용할 실수형 범죄, 지역명을 인자로넘겨줌	
			double[][] presentWeather = weatherXlsx.getPresentWeather();	// 올해(예측년) 날씨 가져옴
			double[][] preYearWeather = weatherXlsx.getPreYearWeather();
			double[][] predictionCrime = new double[5][12];			// 예측년(15년도) 5대 범죄당 12달 범죄건수
			double[][] predictionCrimeRate = new double[5][12];		// 예측년(15년도)5대 범죄당 12달 범죄율
			double[][] regressionR2 = new double[5][12];			// 5대 범죄당 12달, 결정계수R2
			double[] totalCrime = new double[5];					// 예측년(15년도) 1년치 총 범죄건수
			double[] preTotalCrime = new double[5];					// 전년도(14년도) 1년치 총 범죄 건수
			double[][] preYearCrime = crimeXlsx.preYearCrime(region.get(regionCode.regionName[regionCodeNumCount]));	// 전년도(14년도) 5대범죄당 12달 범죄건수
			double[][] preYearCrimeRate = new double[5][12];	// 전년도(14년도)5대 범죄당 12달 범죄율
			
			
			WeatherDto weatherDto = new WeatherDto();
			CrimeDto crimeDto = new CrimeDto();
			
			/* 날씨 데이터 DB에 저장 */
			//15년도 날씨 데이터 저장
			/*for(int i = 0; i < 12; i++){	// while문에 지역별로 16번 돌기 때문에 for문으로는 12달만 계산하면됨
				if(i>8)
					weatherDto.setDate("2015" + (i + 1) + "00");	
				else
					weatherDto.setDate("20150" + (i + 1) + "00");
				weatherDto.setRegionCodeNum(regionCode.regionNum[regionCodeNumCount]);
				weatherDto.setTemperature(presentWeather[i][0]);
				weatherDto.setHumidity(presentWeather[i][1]);
				writeWeather(weatherDto);
			}*/
			
			//14년도 날씨 데이터 저장
			/*for(int i = 0; i < 12; i++){	// while문에 지역별로 16번 돌기 때문에 for문으로는 12달만 계산하면됨
				if(i>8)
					weatherDto.setDate("2014" + (i + 1) + "00");	
				else
					weatherDto.setDate("20140" + (i + 1) + "00");
				weatherDto.setRegionCodeNum(regionCode.regionNum[regionCodeNumCount]);
				weatherDto.setTemperature(preYearWeather[i][0]);
				weatherDto.setHumidity(preYearWeather[i][1]);
				writeWeather(weatherDto);
			}*/
			
			//회귀분석 - 예측년도(2015년) 범죄건수 계산 
			/*for (int i = 0; i < crimeCodeNum.length; i++) {				//	crimeName.length = 5
				for (int j = 0; j < presentWeather.length; j++){		//  weather.getWeatherData().length = 12
					RegressionMatrix regression = new RegressionMatrix(weather[i], crime[i][j]);
					// 예측 범죄 건수 계산 
					predictionCrime[i][j] = Math.round(regression.beta(0) + regression.beta(1) * presentWeather[i][0] 
							+ regression.beta(2) * presentWeather[i][1]);	
					regressionR2[i][j] = regression.R2();
					if(predictionCrime[i][j] < 0 )
						predictionCrime[i][j] = 0; //예측범죄건수가 음수값나오면 0으로 대입
				}
			}*/
	
			// 14년, 15년 5대 범죄별 1년치 '범죄 건수' 합계
			/*for(int i = 0; i < crimeCodeNum.length; i++){		// crimeCodeNum.length = 5
				for(int j = 0; j < presentWeather.length; j++){	// presentWeather.length = 12
					totalCrime[i] += predictionCrime[i][j];	 	// 15년 범죄별 범죄 건수 합계
					preTotalCrime[i] += preYearCrime[i][j];		// 14년 범죄별 범죄 건수 합계
				}
			}*/
			 
			
			/*int total=0;
			int preTotal =0;
			double[] regionCrimeRate1 = new double[12];
			double[] regionCrimeRate2 = new double[12];
			double[] tmp1 = new double[12];
			double[] tmp2 = new double[12];
			
			//preTotalCrime[0] 절도 1년치 다 더한것
			//preYearCrime[0][0] + preYearCrime[1][0] + preYearCrime[2][0] ... => 5대범죄 1월달치 다 더한것
			for(int i= 0; i < 12; i++){
				for(int j=0; j< 5; j++){
					tmp1[i] += predictionCrime[j][i];
					tmp2[i] += preYearCrime[j][i];
				}
			}
	
			// 1년 범죄건수 총합계
			for(int i = 0; i < 5; i++){
				for(int j = 0; j < 12; j++){
					total += predictionCrime[i][j];
					preTotal += preYearCrime[i][j];
				}
			}
			
			// 지역별 월별 범죄율 계산 = 각월 총범죄건수 / 전체 범죄건수
			for(int i = 0; i < 12; i++){
				regionCrimeRate1[i] = Math.round(tmp1[i] / total * 100 * 10) /10.0;
				regionCrimeRate2[i] = Math.round(tmp2[i] / preTotal * 100 * 10) /10.0;
			}
			
			// 파이 차트에 쓸 2015년 월별 범죄율 데이터 삽입
			for(int i = 0; i < 12; i++){ 
				if(i>8)
					crimeDto.setDate("2015" + (i + 1) + "00");	
				else
					crimeDto.setDate("20150" + (i + 1) + "00");	
				crimeDto.setCrimeRate(regionCrimeRate1[i]);
				crimeDto.setRegionCodeNum(regionCode.regionNum[regionCodeNumCount]);
				writeMonthRate(crimeDto);
			
			}
			// 파이 차트에 쓸 2014년 월별 범죄율 데이터 삽입
			for(int i = 0; i < 12; i++){ 
				if(i>8)
					crimeDto.setDate("2014" + (i + 1) + "00");	
				else
					crimeDto.setDate("20140" + (i + 1) + "00");	
				crimeDto.setCrimeRate(regionCrimeRate2[i]);
				crimeDto.setRegionCodeNum(regionCode.regionNum[regionCodeNumCount]);
				writeMonthRate(crimeDto);
			
			}*/
			
			
			
			
			// 14년, 15년 5대 범죄별 월별 '범죄율' 계산
			/*for(int i = 0; i < crimeCodeNum.length; i++){
				for(int j = 0; j < presentWeather.length; j++){
					preYearCrimeRate[i][j] = Math.round(preYearCrime[i][j] / preTotalCrime[i] * 100 * 10) / 10.0;
					predictionCrimeRate[i][j] = Math.round(predictionCrime[i][j] / totalCrime[i] * 100 * 10) / 10.0;
					if(predictionCrimeRate[i][j] < 0 )
						predictionCrimeRate[i][j] = 0;	// 범죄예측율이 음수값나오면 0으로 대입
				}
			}*/
			
			
			/* 15년도 범죄예측율 및 범죄예측건수 DB에 저장 */
			/*for(int i = 0; i < 5; i++){ // i = 5대 범죄당 j = 12달
				if(i == 2)		// 2015년 강도는 제외 
					continue;
				if(regionCode.regionNum[regionCodeNumCount].equals("053") && i == 3)	// 053(대구)이면서 004(강간)이면 제외
					continue;
				for(int j = 0; j < presentWeather.length; j++){
					if(j>8)
						crimeDto.setDate("2015" + (j + 1) + "00");	
					else
						crimeDto.setDate("20150" + (j + 1) + "00");	
					crimeDto.setCrimeRate(predictionCrimeRate[i][j]);
					crimeDto.setRegionCodeNum(regionCode.regionNum[regionCodeNumCount]);
					crimeDto.setCrimeCodeNum(crimeCodeNum[i]);
					crimeDto.setCrimeCount(predictionCrime[i][j]);
				//	writeHistoryCrimeRate(crimeDto);	// 예측범죄율 히스토리 저장
				//	writeCrimeRate(crimeDto);			// 예측범죄율 저장
				//	writeCrimeCount(crimeDto);			// 예측범죄 건수 저장
				}
			}*/
			
			/* 대구(053)의 강간(004)는 15년도에 2014년도 데이터 갖다 씀 */
			/*if(regionCode.regionNum[regionCodeNumCount].equals("053")){
				for(int i=0; i < 12; i++){
					if(i > 8)
						crimeDto.setDate("2015" + (i + 1) + "00");	
					else
						crimeDto.setDate("20150" + (i + 1) + "00");	
					crimeDto.setCrimeRate(preYearCrimeRate[3][i]);
					crimeDto.setRegionCodeNum(regionCode.regionNum[regionCodeNumCount]);
					crimeDto.setCrimeCodeNum(crimeCodeNum[3]);
					crimeDto.setCrimeCount(preYearCrime[3][i]);
				//	writeHistoryCrimeRate(crimeDto);	// 범죄율 히스토리 저장
				//	writeCrimeRate(crimeDto);			// 범죄율 저장
				//	writeCrimeCount(crimeDto);			// 범죄 건수 저장
				}
			}*/
			
			
			
			/* 2015년 강도 데이터에만 2014년 강도데이터를 삽입 */
			/*for(int i = 0; i < 12; i++){
				if(i > 8)
					crimeDto.setDate("2015" + (i + 1) + "00");	
				else
					crimeDto.setDate("20150" + (i + 1) + "00");	
				crimeDto.setCrimeRate(preYearCrimeRate[2][i]);
				crimeDto.setRegionCodeNum(regionCode.regionNum[regionCodeNumCount]);
				crimeDto.setCrimeCodeNum(crimeCodeNum[2]);
				crimeDto.setCrimeCount(preYearCrime[2][i]);
			//	writeHistoryCrimeRate(crimeDto);	// 범죄율 히스토리 저장
			//	writeCrimeRate(crimeDto);			// 범죄율 저장
			//	writeCrimeCount(crimeDto);			// 범죄 건수 저장
			}*/
			
			/* 전년도(2014) 범죄율 및 범죄건수 DB에 저장 */
			/*for(int i=0;i<5;i++){
				for(int j=0; j<12;j++){
					
					if(j>8)
						crimeDto.setDate("2014" + (j + 1) + "00");	
					else
						crimeDto.setDate("20140" + (j + 1) + "00");	
					crimeDto.setCrimeRate(preYearCrimeRate[i][j]);
					crimeDto.setRegionCodeNum(regionCode.regionNum[regionCodeNumCount]);
					crimeDto.setCrimeCodeNum(crimeCodeNum[i]);
					crimeDto.setCrimeCount(preYearCrime[i][j]);
				//	writeCrimeRate(crimeDto);			// 14년도 예측범죄율 저장
				//	writeCrimeCount(crimeDto);			// 14년도 예측범죄 건수 저장
				}
			}*/
			
			// 행정구별 '범죄 예측률' 콘솔창에 출력
						/*for(int i =0 ; i< 5;i++){
							int month = 1;
							System.out.println(" ** 범죄명 : " + crimeName[i] + " **");
							for(int j=0; j < presentWeather.length;j++){
								System.out.print("2015년 ");
								System.out.printf("%2d 월 ", month++);
								System.out.print(regionCode.regionName[regionCodeNumCount] + " " + crimeName[i] + " 예측 범죄건수 = ");
								System.out.print((int)predictionCrime[i][j]);
								System.out.printf(",  예측 범죄율 = %.2f %%", predictionCrimeRate[i][j]);
								System.out.printf(",  (결정계수 R^2 = %.2f)\n" ,regressionR2[i][j]);
							}
							System.out.println();
						}*/
			regionCodeNumCount++;	
		}
		
		
	}
}