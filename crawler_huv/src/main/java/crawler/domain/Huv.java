package crawler.domain;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import crawler.crawler.Board;
import crawler.parser.parser;

public class Huv implements parser{

	public Board readLogData(Document doc) {  
		Board board = new Board();
		board.setCommunityNo(1);
		Elements titleInfo = doc.select("#if_sub");
		Elements posting = doc.select("#wrap_body");
		Elements dateInfo = doc.select("#if_date");
		
		for (Element e : titleInfo) {
			String title = e.child(0).text().replaceAll("'","''");
			title = title.replaceAll("$", ""); // 구분자를 없애줌
			board.setTitle(title);
			System.out.println("title : " +title);
		}

		for (Element e : posting) {
			String content = e.child(0).text().replaceAll("'","''");
			content = content.replaceAll("$", ""); // 구분자를 없애줌
			board.setContent(content);
			System.out.println("content : "+content);
		}
		
		for (Element e : dateInfo) {
			String registDate = e.child(0).text();
			board.setRegistDate(convertDate(registDate));
			System.out.println("registDate : " + registDate);
		}
		return board;
	}

	public String convertDate(String date){
		//TODO 날짜 포맷 변경해야함  yyyy.MM.dd hh:mm TO yyyyMMddhhmmss
		String [] tmp = date.split(" ");
		String [] tmpDate = tmp[0].split("-");
		return tmpDate[0]+tmpDate[1]+tmpDate[2];
	}
}
