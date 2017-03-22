package kr.co.codedraw.dao;

import java.util.List;

import kr.co.codedraw.dto.CrimeInfo;
import kr.co.codedraw.dto.CrimeViewDto;
import kr.co.codedraw.dto.StatisticsMapLog;
import kr.co.codedraw.dto.WeatherDto;

public interface StatisticsMapDao {

	public List<CrimeViewDto> getCrimeRateofArea(String cDate);
	
	public void insertStatisticsMapLog(StatisticsMapLog sml);
	
	public List<CrimeInfo> loadSimul(WeatherDto wDto );
	
	public List<CrimeViewDto> load5CrimeRateofArea(String cDate);
	
	public List<CrimeViewDto> load5CrimeRateofAreaForRegionCode(CrimeViewDto dto);
	
	public List<CrimeViewDto> loadAvgCrimeRateofAreaForRegionCode(CrimeViewDto dto);
	
	public List<CrimeViewDto> loadAvgCrimeRateofAreaAllMonth2015();
	
	
}
