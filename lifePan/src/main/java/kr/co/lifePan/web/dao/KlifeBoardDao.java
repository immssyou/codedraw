package kr.co.lifePan.web.dao;

import java.util.List;

import kr.co.lifePan.web.domain.Board;
import kr.co.lifePan.web.domain.KlifeBoard;

public interface KlifeBoardDao {

	public void insertKlifeBoard(KlifeBoard board);
	
	public List<KlifeBoard> getKlifeBoardList();
	
	public List<Board> getData();
}
