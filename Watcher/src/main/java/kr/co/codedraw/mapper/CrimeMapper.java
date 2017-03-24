package kr.co.codedraw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.co.codedraw.dto.CrimeCountDto;
import kr.co.codedraw.dto.CrimeDto;
import kr.co.codedraw.dto.CrimeMonthRateDto;

public interface CrimeMapper {
	public List<CrimeDto> readCrimeRate();
	public void writeCrimeRate(@Param("crimeDto") CrimeDto crimeDto);
	public void writeHistoryCrimeRate(@Param("crimeDto") CrimeDto crimeDto);
	public void writeCrimeCount(@Param("crimeDto") CrimeDto crimeDto);
	public void updateAutoIncrementCrimeRate();
	public void deleteCrimeRate();
	public void updateAutoIncrementCrimeCount();
	public void deleteCrimeCount();
	public List<CrimeDto> crimeCountList(@Param("crimeCountDto") CrimeCountDto crimeCountDto);	// @Param을 써야 mybatis에서 . 연산자로 멤버변수접근가능
	
	public void writeMonthRate(@Param("crimeDto") CrimeDto crimeDto);
	public List<CrimeMonthRateDto> crimePIchart(@Param("crimeCountDto") CrimeCountDto crimeCountDto);
}