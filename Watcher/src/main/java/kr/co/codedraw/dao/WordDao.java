package kr.co.codedraw.dao;

import java.util.List;

import kr.co.codedraw.dto.RealwordDto;
import kr.co.codedraw.dto.WordDto;

public interface WordDao {
	public List<List<WordDto>>  listDao(String presentDate);
	
	public List<RealwordDto> keyWordList(String presentDate);
	
}
