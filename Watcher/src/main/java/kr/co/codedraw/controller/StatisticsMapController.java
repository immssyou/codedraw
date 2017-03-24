package kr.co.codedraw.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.codedraw.dto.CrimeViewDto;
import kr.co.codedraw.dto.WeatherDto;
import kr.co.codedraw.service.StatisticsMapService;
import net.sf.json.JSONObject;

@Controller
public class StatisticsMapController {

	@Autowired
	private StatisticsMapService statisticsMapService;
	
	@RequestMapping(value="/makeStatisticsMap")
	public String makeStatisticsMap(HttpServletRequest request, Model model){
		
		for(int i =1; i< 13; i++){
			String cDate = "2015";
			if(i <10)
				cDate = cDate + "0"+i+"00";
			else
				cDate = cDate+ i +"00";
			
			statisticsMapService.makeStatisticsMap(cDate);
		}
		return "/mainPage";
	}
	
	@RequestMapping(value = "/getCrimeRateofArea")
	@ResponseBody
	public ModelMap getCrimeRateofArea(@RequestParam("selMonth") String selMonth ,HttpServletRequest request){
		ModelMap map = new ModelMap();
		String cDate = "2015"+selMonth+"00";
		List<CrimeViewDto> crimeInfoList = statisticsMapService.getCrimeRateofArea(cDate);
		map.put("data", crimeInfoList);
		return map;
	}
	
	
	@RequestMapping(value = "/load5CrimeRateofArea")
	@ResponseBody
	public ModelMap load5CrimeRateofArea(@RequestParam("selMonth") String selMonth ,HttpServletRequest request){
		ModelMap map = new ModelMap();
		List<List<CrimeViewDto>>  list = statisticsMapService.load5CrimeRateofArea(selMonth);
		map.put("data", list);
		return map;
	}

	@RequestMapping(value = "/loadSimul")
	@ResponseBody
	public ModelMap loadSimul( @RequestParam("temperature") Double temperature, @RequestParam("humidity") Double humidity ,HttpServletRequest request){
		//서비스 단에서 데이터 가져오고
		//지도 만들고
		//지도 이름 가져와야한다.
		temperature = (float)9 / 5 * temperature + 32;
		
		ModelMap  map = statisticsMapService.loadSimulKml(temperature, humidity);
		return map;
	}
	
	@RequestMapping(value = "/loadCrimeRate5AndAvg")
	@ResponseBody
	public ModelMap loadCrimeRate5AndAvg( @RequestParam("cDate") String cDate,@RequestParam("regionCode") String regionCode, HttpServletRequest request){
		ModelMap  map = statisticsMapService.loadCrimeRate5AndAvg(cDate, regionCode);
		return map;
	}
}
