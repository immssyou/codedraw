package kr.co.codedraw.service;

import java.util.List;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.codedraw.dto.CrimeViewDto;
import kr.co.codedraw.dto.WeatherDto;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface StatisticsMapService {

	public void makeStatisticsMap(String cDate);
	
	public List<List<CrimeViewDto>>  load5CrimeRateofArea(String selMonth);
	
	public ModelMap  loadSimulKml(Double temperature,  Double humidity);
	
	public List<CrimeViewDto> getCrimeRateofArea(String cDate);
	
	public ModelMap loadCrimeRate5AndAvg(String cDate, String regionCode);
	
}
