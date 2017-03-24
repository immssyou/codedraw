package kr.co.codedraw.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.co.codedraw.dto.CrimeCountDto;
import kr.co.codedraw.dto.CrimeDto;
import kr.co.codedraw.dto.CrimeMonthRateDto;

public interface CrimeDao {
	public List<CrimeDto> readCrimeRate();
	public void writeCrimeRate(@Param("crimeDto") CrimeDto crimeDto);
	public void writeHistoryCrimeRate(@Param("crimeDto") CrimeDto crimeDto);
	public void writeCrimeCount(@Param("crimeDto") CrimeDto crimeDto);
	
	public void updateAutoIncrementCrimeRate();
	public void deleteCrimeRate();
	public void updateAutoIncrementCrimeCount();
	public void deleteCrimeCount();
	public List<CrimeDto> crimeCountList(CrimeCountDto crimeCountDto);
	
	public void writeMonthRate(@Param("crimeDto") CrimeDto crimeDto);
	
	public List<CrimeMonthRateDto> crimePIchart(@Param("CrimeCountDto") CrimeCountDto crimeCountDto);
}