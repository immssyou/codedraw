package kr.co.codedraw.mapper;

import java.util.List;

import kr.co.codedraw.dto.RealwordDto;
import kr.co.codedraw.dto.WordDto;

public interface WordMapper {
	public List<WordDto> listDao(WordDto dto);
	
	public List<RealwordDto> keyWordList(String presentDate);
}
