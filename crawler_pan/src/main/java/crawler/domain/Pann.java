package crawler.domain;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import crawler.crawler.Board;
import crawler.parser.parser;

public class Pann implements parser{

	public Board readLogData(Document doc) {  
		Board board = new Board();
		board.setCommunityNo(1);
		Elements titleInfo = doc.select("#container > div.content.sub > div.viewarea > div.view-wrap > div.post-tit-info");
		Elements posting = doc.select("#container > div.content.sub > div.viewarea > div.view-wrap > div.posting");
		Elements upDownInfo = doc.select("#container > div.content.sub > div.viewarea > div.view-wrap > div.updown.f_clear");
		Elements cmt_betInfo_1 = doc.select("#bepleDiv > div.cmt_best > dl.cmt_item.f_line");
		for (Element e : titleInfo) {
			String title = e.child(0).text().replaceAll("'","''");
			title = title.replaceAll("$", ""); // 구분자를 없애줌
			String writer = e.child(1).child(0).text().replaceAll("'","''");
			String date = e.select(".date").text();
			String count = e.child(1).child(2).text().substring(2);
			count = count.replaceAll(",", "");
			String registDate = date.substring(0,10);
			//String registTime = date.substring(11,16);
			
			board.setTitle(title);
			board.setWriter(writer);
			board.setRegistDate(convertDate(registDate));  //2016.01.01 to 20160101
			//board.setRegistTime(registTime);
		//	board.setCount(Long.parseLong(count));
		//	System.out.println("title : " +title+ " writer : "+writer + " registDate : " + registDate+ " registTime:"+registTime+" count : " +count);
		}

		for (Element e : posting) {
			String content = e.text().replaceAll("'","''");
			content = content.replace("$", "");
			board.setContent(content);
		//	System.out.println("content : "+content);
		}
		
/*		for (Element e : upDownInfo) {
			Elements bues = e.select(".up").select(".count");;
			String likes= bues.text();
			board.setLikes(Integer.parseInt(likes));
			System.out.println("likes : "+likes);
		}
		*/
	/*	for (Element e : cmt_betInfo_1) {
			String writer_c = e.child(0).child(1).text().replaceAll("'","''");
		//	String date = e.child(0).child(2).text();
		//	String registDate_c  = convertDate(date);
			String content_c = e.child(2).text().replaceAll("'","''");
			board.setWriter_c(writer_c);
		//	board.setRegistDate_c(registDate_c);
			board.setContent_c(content_c);
			//System.out.println("writer : "+writer + " date : " + date + " content : "+content);
		}*/
		return board;
	}

	public String convertDate(String date){
		//TODO 날짜 포맷 변경해야함  yyyy.MM.dd hh:mm TO yyyyMMddhhmmss
		String[] result = date.split("\\.");
		String registDateStr = "";
		for(String s : result){
			registDateStr +=  s;
		}
		return registDateStr;
	}
}
