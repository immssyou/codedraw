package kr.co.codedraw.service;

import java.util.List;

import kr.co.codedraw.dto.CrimeCountDto;
import kr.co.codedraw.dto.CrimeDto;
import kr.co.codedraw.dto.CrimeMonthRateDto;

public interface CrimeCountService {
	public List<CrimeDto> crimeCountList(CrimeCountDto crimeCountDto);
	public List<CrimeMonthRateDto> crimePIchart(CrimeCountDto crimeCountDto);
}
