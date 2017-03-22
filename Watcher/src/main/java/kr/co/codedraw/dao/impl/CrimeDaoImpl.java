package kr.co.codedraw.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.codedraw.dao.CrimeDao;
import kr.co.codedraw.dto.CrimeCountDto;
import kr.co.codedraw.dto.CrimeDto;
import kr.co.codedraw.dto.CrimeMonthRateDto;
import kr.co.codedraw.mapper.CrimeMapper;

@Component
public class CrimeDaoImpl implements CrimeDao{

	

	@Autowired
	private SqlSession sqlSession;
	
	
	@Override
	public List<CrimeDto> crimeCountList(CrimeCountDto crimeCountDto) {
		CrimeMapper crimeMapper = sqlSession.getMapper(CrimeMapper.class);
		return crimeMapper.crimeCountList(crimeCountDto);
	}


	@Override
	public List<CrimeDto> readCrimeRate() {
		CrimeMapper crimeMapper = sqlSession.getMapper(CrimeMapper.class);
		return crimeMapper.readCrimeRate();
	}

	@Override
	public void writeCrimeCount(CrimeDto crimeDto) {
		CrimeMapper crimeMapper = sqlSession.getMapper(CrimeMapper.class);
		crimeMapper.writeCrimeCount(crimeDto);
	}

	@Override
	public void writeCrimeRate(CrimeDto crimeDto) {
		CrimeMapper crimeMapper = sqlSession.getMapper(CrimeMapper.class);
		crimeMapper.writeCrimeRate(crimeDto);
	}
	
	@Override
	public void writeHistoryCrimeRate(CrimeDto crimeDto) {
		CrimeMapper crimeMapper = sqlSession.getMapper(CrimeMapper.class);
		crimeMapper.writeHistoryCrimeRate(crimeDto);
	}

	@Override
	public void updateAutoIncrementCrimeRate() {
		CrimeMapper crimeMapper = sqlSession.getMapper(CrimeMapper.class);
		crimeMapper.updateAutoIncrementCrimeRate();
	}

	@Override
	public void deleteCrimeRate() {
		CrimeMapper crimeMapper = sqlSession.getMapper(CrimeMapper.class);
		crimeMapper.deleteCrimeRate();
	}

	@Override
	public void updateAutoIncrementCrimeCount() {
		CrimeMapper crimeMapper = sqlSession.getMapper(CrimeMapper.class);
		crimeMapper.updateAutoIncrementCrimeCount();
		
	}

	@Override
	public void deleteCrimeCount() {
		CrimeMapper crimeMapper = sqlSession.getMapper(CrimeMapper.class);
		crimeMapper.deleteCrimeCount();
		
	}


	@Override
	public void writeMonthRate(CrimeDto crimeDto) {
		CrimeMapper crimeMapper = sqlSession.getMapper(CrimeMapper.class);
		crimeMapper.writeMonthRate(crimeDto);
		
	}


	@Override
	public List<CrimeMonthRateDto> crimePIchart(CrimeCountDto crimeCountDto) {
		CrimeMapper crimeMapper = sqlSession.getMapper(CrimeMapper.class);
		return crimeMapper.crimePIchart(crimeCountDto);
		
	}

	
}