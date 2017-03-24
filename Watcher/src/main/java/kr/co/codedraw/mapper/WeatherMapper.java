package kr.co.codedraw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.co.codedraw.dto.WeatherDto;

public interface WeatherMapper {
	public List<WeatherDto> readWeather();
	public void writeWeather(@Param("weatherDto") WeatherDto weatherDto);
	public void updateAutoIncrementWeather();
	public void deleteWeather();
}