package kr.co.lifePan.web.persistence.mysql.mybatis.mapper;

import java.util.List;

import kr.co.lifePan.web.domain.Board;
import kr.co.lifePan.web.domain.KlifeBoard;

public interface KlifeBoardMapper {
	
	public void insertKlifeBoard(KlifeBoard board);
	
	public List<KlifeBoard> getKlifeBoardList();
	
	public List<Board> getData();
}
