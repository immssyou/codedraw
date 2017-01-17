package kr.co.lifePan.web.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import kr.co.lifePan.web.dao.KlifeBoardDao;
import kr.co.lifePan.web.domain.Board;
import kr.co.lifePan.web.domain.KlifeBoard;
import kr.co.lifePan.web.persistence.mysql.mybatis.mapper.KlifeBoardMapper;

@Repository("klifeBoardDaoImpl")
public class KlifeBoardDaoImpl implements KlifeBoardDao{

	@Autowired 
	private KlifeBoardMapper klifeBoardMapper;
	@Override 
	public void insertKlifeBoard(KlifeBoard board) {
		klifeBoardMapper.insertKlifeBoard(board);
	}
	@Override
	public List<KlifeBoard> getKlifeBoardList() {
		return klifeBoardMapper.getKlifeBoardList();
	}
	@Override
	public List<Board> getData() {
		return klifeBoardMapper.getData();
	};
		
}
