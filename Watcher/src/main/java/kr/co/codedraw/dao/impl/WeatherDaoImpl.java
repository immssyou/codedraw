package kr.co.codedraw.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.codedraw.dao.WeatherDao;
import kr.co.codedraw.dto.WeatherDto;
import kr.co.codedraw.mapper.WeatherMapper;

@Component
public class WeatherDaoImpl implements WeatherDao{
	
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<WeatherDto> readWeather() {
		WeatherMapper mapper = sqlSession.getMapper(WeatherMapper.class);
		return mapper.readWeather();
	}

	@Override
	public void writeWeather(WeatherDto weatherDto) {
		WeatherMapper mapper = sqlSession.getMapper(WeatherMapper.class);
		mapper.writeWeather(weatherDto);
	}

	@Override
	public void deleteWeather() {
		WeatherMapper mapper = sqlSession.getMapper(WeatherMapper.class);
		mapper.deleteWeather();
	}

	@Override
	public void updateAutoIncrementWeather() {
		WeatherMapper mapper = sqlSession.getMapper(WeatherMapper.class);
		mapper.updateAutoIncrementWeather();
	}
	

}