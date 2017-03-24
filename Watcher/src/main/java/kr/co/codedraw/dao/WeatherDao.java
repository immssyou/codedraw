package kr.co.codedraw.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.co.codedraw.dto.CrimeDto;
import kr.co.codedraw.dto.WeatherDto;

public interface WeatherDao {
	public List<WeatherDto> readWeather();
	public void writeWeather(@Param("weatherDto") WeatherDto weatherDto);
	public void updateAutoIncrementWeather();
	public void deleteWeather();
}