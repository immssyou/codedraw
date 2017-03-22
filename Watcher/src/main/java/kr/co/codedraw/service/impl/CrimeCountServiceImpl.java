package kr.co.codedraw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.codedraw.dao.CrimeDao;
import kr.co.codedraw.dto.CrimeCountDto;
import kr.co.codedraw.dto.CrimeDto;
import kr.co.codedraw.dto.CrimeMonthRateDto;
import kr.co.codedraw.service.CrimeCountService;

@Component
public class CrimeCountServiceImpl implements CrimeCountService {

	@Autowired
	private CrimeDao crimeDao;
	
	@Override
	public List<CrimeDto> crimeCountList(CrimeCountDto crimeCountDto) {
		List<CrimeDto> list  = crimeDao.crimeCountList(crimeCountDto);
		
/*		JSONArray jsonArrayList = new JSONArray();
		JSONArray preYearList = new JSONArray();
		JSONArray presentList = new JSONArray();
		for(CrimeDto dto : list){
			JSONObject json = new JSONObject();
			json.put(dto.getCrimeCodeNum(), dto.getCrimeCount());
			if(dto.getDate().equals(crimeCountDto.getPreYearDate())){
				preYearList.add(json);
			}
			else if(dto.getDate().equals(crimeCountDto.getPresentDate())){
				presentList.add(json);
			}
		}
		jsonArrayList.add(preYearList);
		jsonArrayList.add(presentList);*/
		return list;
	}

	@Override
	public List<CrimeMonthRateDto> crimePIchart(CrimeCountDto crimeCountDto) {
		List<CrimeMonthRateDto> list = crimeDao.crimePIchart(crimeCountDto);
		return list;
	}

	

}
