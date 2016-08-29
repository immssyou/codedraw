package crawler.domain;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import crawler.crawler.Board;
import crawler.parser.parser;

public class Ou implements parser{

	public Board readLogData(Document doc) {  
		Board board = new Board();
		board.setCommunityNo(1);
		Elements titleInfo = doc.select("div.writerInfoContents");
		Elements posting = doc.select("div.viewContent");
		//Elements upDownInfo = doc.select("#container > div.content.sub > div.viewarea > div.view-wrap > div.updown.f_clear");
		//Elements cmt_betInfo_1 = doc.select("#bepleDiv > div.cmt_best > dl.cmt_item.f_line");
		 
	for (Element e : titleInfo) {
			String title = e.child(1).child(0).text().replaceAll("'","''");
			title = title.replaceAll("$", ""); // 구분자를 없애줌
			board.setTitle(title);
			
			String registDate = e.child(6).text().replaceAll("'","''");
			
			board.setRegistDate(convertDate(registDate));
		}

		for (Element e : posting) {
			String content = e.text().replaceAll("'","''");
			content = content.replaceAll("$", ""); // 구분자를 없애줌
			board.setContent(content);
			//System.out.println("content : "+content);
		}
		
		return board;
	}
 
	public String convertDate(String date){
		 //등록시간 : 2016/01/01 00:09:03
		String [] temp = date.split(" ");
		String [] tempDate = temp[2].split("/");
		return tempDate[0]+tempDate[1]+tempDate[2];
	}
}
