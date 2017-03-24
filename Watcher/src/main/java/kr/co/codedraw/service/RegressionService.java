package kr.co.codedraw.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.co.codedraw.dto.CrimeDto;
import kr.co.codedraw.dto.WeatherDto;

public interface RegressionService {
	
	public void scheduleRun();
	
	public List<CrimeDto> readCrimeRate();
	public void writeCrimeRate(@Param("crimeDto") CrimeDto crimeDto);
	public void writeHistoryCrimeRate(@Param("crimeDto") CrimeDto crimeDto);
	public void writeCrimeCount(@Param("crimeDto") CrimeDto crimeDto);
	public void updateAutoIncrementCrimeRate();
	public void deleteCrimeRate();
	public void updateAutoIncrementCrimeCount();
	public void deleteCrimeCount();
	
	
	public List<WeatherDto> readWeather();
	public void writeWeather(@Param("weatherDto") WeatherDto weatherDto);
	public void updateAutoIncrementWeather();
	public void deleteWeather();

	public void writeMonthRate(@Param("crimeDto") CrimeDto crimeDto);
}
