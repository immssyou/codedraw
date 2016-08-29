package crawler.parser;

import org.jsoup.nodes.Document;

import crawler.crawler.Board;

public interface parser {
	public Board readLogData(Document doc );
	
//	public String convertDate(String date);
}
