package kr.co.lifePan.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import kr.co.lifePan.web.dao.KlifeBoardDao;
import kr.co.lifePan.web.dao.UserDao;
import kr.co.lifePan.web.domain.Board;
import kr.co.lifePan.web.domain.KlifeBoard;
import kr.co.lifePan.web.service.KlifeBoardService;

@Service("klifeBoardService")
public class KlifeBoardServiceImpl implements KlifeBoardService{
	@Autowired
	private KlifeBoardDao klifeBoardDao;
	@Override
	public void insertKlifeBoard(KlifeBoard board) {
		klifeBoardDao.insertKlifeBoard(board);
	};
	
	@Override
	public List<KlifeBoard> getKlifeBoardList(){
		return klifeBoardDao.getKlifeBoardList();
	}

	@Override
	public List<Board> getData() {
		return klifeBoardDao.getData();
	};
}
