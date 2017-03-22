package kr.co.codedraw.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.codedraw.dao.WordDao;
import kr.co.codedraw.dto.RealwordDto;
import kr.co.codedraw.dto.WordDto;
import kr.co.codedraw.service.WordService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Component
public class WordServiceImpl implements WordService{

	@Autowired
	private WordDao wordDao;
	
	@Override
	public List<List<WordDto>>   listDao(String presentDate) {
		List<List<WordDto>>  list = wordDao.listDao(presentDate);
		return list;
	}

	@Override
	public List<RealwordDto> keyWordList(String presentDate) {
		return wordDao.keyWordList(presentDate);
	}
	
}
