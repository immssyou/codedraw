package crawler.crawler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import crawler.domain.Pann; 
 
public class WebScrapper { 
	public static void main(String args[]) throws Exception {
		
		try {
		int i =  329478016; //2016년 1월1일
		int errorIndex = 0; 
		String month = "01";
		while(true){
			if(errorIndex > 200){ //200개 까지 없으면 롤빽임;
				i = i-errorIndex;
				errorIndex = 0 ;
				System.out.println("없나 테스트");
			}
			try {
				
				String url = "http://pann.nate.com/talk/"+i;
				Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36").timeout(0).get();
				Pann pann = new Pann();
				Board board = pann.readLogData(doc);
				board.setUrl(url); 
				
				if(board.getMonth() != null){
					month = board.getMonth();
				}
				
				File targetFile = new File("D:\\SNS_DATA\\pan_output_"+month+".txt");
				targetFile.createNewFile();
				BufferedWriter output = new BufferedWriter(new FileWriter(targetFile.getPath(), true));
				
//	 		ACTION CREATE TEXT FILE
				System.out.println("index: " + i);
					if(board.getTitle() != null){
						output.write(board.getTitle());
						output.write("$");
						System.out.println(board.getTitle());
						errorIndex = 0;
					} else {
						errorIndex++;
					}
					
					if(board.getContent() != null){
						output.write(board.getContent());
						output.write("$");
						System.out.println(board.getContent());
					}
					if(board.getRegistDate() != null){
						output.write(board.getRegistDate());
						output.write("$"); 
						System.out.println(board.getRegistDate() );
					}
					
					if(board.getTitle() != null){ // 내용이 있으면 url을 등록해준다.
						output.write(board.getUrl());
						output.newLine();
						System.out.println(board.getUrl());
					}
					output.close();			
			} catch (Exception e) {
				e.printStackTrace();
			}
	
			Thread.sleep(400);
			i = i+10 ;
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
	}
}