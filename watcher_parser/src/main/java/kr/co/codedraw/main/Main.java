package kr.co.codedraw.main;

import kr.co.codedraw.mysql.dao.BoardDao;
import kr.co.codedraw.mysql.dto.BoardDTO;

public class Main {

	
	public static void main(String[] args) throws Exception {
		
		 
		BoardDao bDao = new BoardDao();
		
		BoardDTO bDto = new BoardDTO();
		 bDao.add(bDto);
	}

}
