package kr.co.codedraw.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.codedraw.dto.CrimeCountDto;
import kr.co.codedraw.dto.CrimeDto;
import kr.co.codedraw.dto.CrimeMonthRateDto;
import kr.co.codedraw.dto.RealwordDto;
import kr.co.codedraw.dto.WordDto;
import kr.co.codedraw.service.CrimeCountService;
import kr.co.codedraw.service.WordService;

@Controller
public class WatcherConroller {
	;
	
	@Autowired
	private WordService wordService;

	@Autowired
	private CrimeCountService crimeService;
	
	

	
	@RequestMapping(value="/")
	public String introPage(){
		
		return "/introPage";
	}
	
	@RequestMapping(value="/mainPage")
	public String mainPage(){
		
		return "/mainPage";
	}
	
	@RequestMapping(value="/keywordPage")
	public String keywordPage(){
		
		return "/keywordPage";
	}
	
	@RequestMapping(value="/wordcloudPage")
	public String wordcloudPage(){
		return "/wordcloudPage";
	}
	
	@RequestMapping(value="/simulationPage")
	public String simulationPage(){
		
		return "/simulationPage";
	}
	
	@RequestMapping(value="/developerInfo")
	public String developerInfo(){
		
		return "/developerInfo";
	}
	

	@RequestMapping(value="/wordList")
	@ResponseBody
	public ModelMap wordList(HttpServletRequest request, ModelMap model){		// 워드 클라우드 데이터값 리턴함
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	    Calendar c1 = Calendar.getInstance();
	    c1.add(Calendar.DATE, -1);
		String presentDate =sdf.format(c1.getTime());
		
		List<List<WordDto>>  list = wordService.listDao(presentDate);
		model.addAttribute("wordList", list);
		return model;
	}
	
	@RequestMapping(value="/crimeCount")
	@ResponseBody
	public ModelMap crimeCount(HttpServletRequest request, ModelMap model){		
		//selMonth , regionCode
		CrimeCountDto crimeCountDto = new CrimeCountDto();
		crimeCountDto.setPreYearDate("2014" + request.getParameter("selMonth") + "00");
		crimeCountDto.setPresentDate("2015" + request.getParameter("selMonth") + "00");
		crimeCountDto.setRegionCodeNum(request.getParameter("regionCode"));
	
		
		List<CrimeDto> list  = crimeService.crimeCountList(crimeCountDto);
		model.addAttribute("crimeCountList", list);

		return model;
	}
	
	@RequestMapping(value="/crimePIchart")
	@ResponseBody
	public ModelMap crimePIchart(HttpServletRequest request, ModelMap model){		
		CrimeCountDto crimeCountDto = new CrimeCountDto();
		crimeCountDto.setPreYearDate("2014" + request.getParameter("selMonth") + "00");
		crimeCountDto.setPresentDate("2015" + request.getParameter("selMonth") + "00");
		crimeCountDto.setRegionCodeNum(request.getParameter("regionCode"));

		List<CrimeMonthRateDto> list  = crimeService.crimePIchart(crimeCountDto);
		model.addAttribute("crimePIchart", list);

		return model;
	}
	
	@RequestMapping(value="/keyWordList")
	@ResponseBody
	public ModelMap keyWordList(HttpServletRequest request, ModelMap model){		
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	    Calendar c1 = Calendar.getInstance();
	    c1.add(Calendar.DATE, -1);
		String presentDate =sdf.format(c1.getTime());
		List<RealwordDto> list = wordService.keyWordList(presentDate);
		model.addAttribute("wordList", list);
		return model;
	}
	

}
