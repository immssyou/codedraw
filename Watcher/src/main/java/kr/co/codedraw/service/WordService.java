package kr.co.codedraw.service;

import java.util.List;

import kr.co.codedraw.dto.RealwordDto;
import kr.co.codedraw.dto.WordDto;

public interface WordService {
	public List<List<WordDto>>  listDao(String presentDate);
	
	public List<RealwordDto> keyWordList(String presentDate);
}
