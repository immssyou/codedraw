package kr.co.lifePan.web.service;

import java.util.List;

import kr.co.lifePan.web.domain.Board;
import kr.co.lifePan.web.domain.KlifeBoard;

public interface KlifeBoardService {
	public void insertKlifeBoard(KlifeBoard board);
	
	public List<KlifeBoard> getKlifeBoardList() ;

	public List<Board> getData();	
}
