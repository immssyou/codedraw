package kr.co.codedraw.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.codedraw.dao.StatisticsMapDao;
import kr.co.codedraw.dto.CrimeInfo;
import kr.co.codedraw.dto.CrimeViewDto;
import kr.co.codedraw.dto.StatisticsMapLog;
import kr.co.codedraw.dto.WeatherDto;
import kr.co.codedraw.mapper.StatisticsMapMapper;

@Component
public class StatisticsMapDaoImpl implements StatisticsMapDao {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<CrimeViewDto> getCrimeRateofArea(String cDate) {
		StatisticsMapMapper mapper = sqlSession.getMapper(StatisticsMapMapper.class);
		CrimeViewDto tmp = new CrimeViewDto();
		tmp.setcDate(cDate);
		List<CrimeViewDto> list =  mapper.getCrimeRateofArea(cDate);
		List<CrimeViewDto> listSum = mapper.loadSumCrimeCountOfArea2015();
		for(int i=0; i <list.size(); i++) {
			Double result = (new Double(list.get(i).getCrimeCount()) / new Double(listSum.get(i).getCrimeCount())) * 100d;
			list.get(i).setCrimeRate(Math.round(result * 100) / 100d);
			System.out.println(list.get(i).getCrimeRate());
		}
		return list;
	}

	@Override
	public void insertStatisticsMapLog(StatisticsMapLog sml) {
		StatisticsMapMapper mapper = sqlSession.getMapper(StatisticsMapMapper.class);
		 mapper.insertStatisticsMapLog(sml);
	}

	@Override
	public List<CrimeInfo> loadSimul(WeatherDto wDto) {
		StatisticsMapMapper mapper = sqlSession.getMapper(StatisticsMapMapper.class);
		return mapper.loadSimul(wDto);
	}

	@Override
	public List<CrimeViewDto> load5CrimeRateofArea(String cDate) {
		StatisticsMapMapper mapper = sqlSession.getMapper(StatisticsMapMapper.class);
		return mapper.load5CrimeRateofArea(cDate);
	}

	@Override
	public List<CrimeViewDto> loadAvgCrimeRateofAreaAllMonth2015() {
		StatisticsMapMapper mapper = sqlSession.getMapper(StatisticsMapMapper.class);
		List<CrimeViewDto> list = mapper.loadAvgCrimeRateofAreaAllMonth2015();
		List<CrimeViewDto> listSum = mapper.loadSumCrimeCountOfArea2015();
		for(int i=0; i< list.size(); i++){
			Integer sCount = list.get(i).getCrimeCount();
			Integer mCount = listSum.get(i / 12).getCrimeCount();
			Double result = (new Double(sCount) /new Double( mCount)) * 100d;
			list.get(i).setCrimeRate(Math.round(result *100)/100d);
		}
		return list;
	}

	@Override
	public List<CrimeViewDto>  load5CrimeRateofAreaForRegionCode(CrimeViewDto dto){
		StatisticsMapMapper mapper = sqlSession.getMapper(StatisticsMapMapper.class);
		return mapper.load5CrimeRateofAreaForRegionCode(dto);
	}
	
	@Override
	public List<CrimeViewDto>  loadAvgCrimeRateofAreaForRegionCode(CrimeViewDto dto){
		StatisticsMapMapper mapper = sqlSession.getMapper(StatisticsMapMapper.class);
		List<CrimeViewDto> list = mapper.loadAvgCrimeRateofAreaForRegionCode(dto);
		List<CrimeViewDto> listSum = mapper.loadSumCrimeCountOfArea2014();
		for(int i=0; i< listSum.size(); i++) {
			if(listSum.get(i).getRegionCode().equals(list.get(0).getRegionCode())){
				Double result = (new Double(list.get(0).getCrimeCount())/new Double(listSum.get(i).getCrimeCount())) *100d ;
				list.get(0).setCrimeRate(Math.round(result * 100) / 100d);
			}
		}
		 return list;
	}
	
	
}
