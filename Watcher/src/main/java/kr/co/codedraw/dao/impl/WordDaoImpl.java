package kr.co.codedraw.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.codedraw.dao.WordDao;
import kr.co.codedraw.dto.RealwordDto;
import kr.co.codedraw.dto.WordDto;
import kr.co.codedraw.mapper.WordMapper;

@Component
public class WordDaoImpl implements WordDao{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<List<WordDto>>  listDao(String presentDate) { 
		WordMapper mapper = sqlSession.getMapper(WordMapper.class);
		List<List<WordDto>> list = new ArrayList<List<WordDto>>() ; 
		for(int i=1; i < 7; i++ ){
			WordDto dto = new WordDto();
			dto.setDate(presentDate);
			dto.setCountryCode("00"+i);
			List<WordDto> tmplist  = mapper.listDao(dto);
			list.add(tmplist);
		}
		return list;
	}

	@Override
	public List<RealwordDto> keyWordList(String presentDate) {
		WordMapper mapper = sqlSession.getMapper(WordMapper.class);
		
		return mapper.keyWordList(presentDate);
	}

}
