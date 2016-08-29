package crawler_twitter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import twitter4j.FilterQuery;
import twitter4j.JSONException;
import twitter4j.JSONObject;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.RawStreamListener;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterConsumer_ko { 
 
	private static final String ACCESS_TOKEN = "768857033059606528-s7eqRPjshC3UWnU83u99HUWX26C7HYm";
    private static final String ACCESS_SECRET = "l4KlGN3aLfMeIC73bo3zfKrZiWx1whB5Iwjog8Kphv4Zf";
    private static final String CONSUMER_KEY = "aRcpGhi5HQCuAPK1lIr8oiBDV";
    private static final String CONSUMER_SECRET = "qhMwCSf5KSdeuLQ3qrlO6TUMwkwzipUHkdtn01wVUdomDXv6LN";
    private final String FEED_URL = "https://stream.twitter.com/1.1/statuses/filter.json?track=socurites,football";
    private static JSONObject jsonObj =null;
    
    
	public static void main(String[] args) throws TwitterException {
	    // twitter4j.properties를 이용해 트위터API에 접근합니다.
		
		BlockingQueue queue = new ArrayBlockingQueue(100); // 중복제거용 임시이름저장 queue
		ConfigurationBuilder cb = new ConfigurationBuilder();
	    cb.setDebugEnabled(true);
	    cb.setOAuthConsumerKey(CONSUMER_KEY);
	    cb.setOAuthConsumerSecret(CONSUMER_SECRET);
	    cb.setOAuthAccessToken(ACCESS_TOKEN);
	    cb.setOAuthAccessTokenSecret(ACCESS_SECRET);
	    
        TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();

       
       StatusListener listener = new StatusListener(){
    		@Override
    		public void onStatus(Status status) {
    			   TwitterDTO twit = new TwitterDTO();
            	   try {
            		   	String text = status.getText();
            		   	String lang = status.getLang();
            		   	Date created_at = status.getCreatedAt();
            		   	if(lang.equals("ko")){
        		   			if(filterWord(text)){ //필터링
        		   				if(!queue.contains(text)){//중복내용 제거
        		   					twit.setContent(text.replaceAll("'","''").replaceAll("$", "").replaceAll("\n", " "));
        		   					
        		   					String registDate = new SimpleDateFormat("yyyyMMdd").format(created_at);
        		   					twit.setRegistDate(registDate);
        		   					
        		   					File targetFile = new File(File.separator+"home"+File.separator+"big"+File.separator+"twitter_data"+File.separator+"twitter_korea_output_"+twit.getRegistDate()+".txt");
        		   					if(!targetFile.exists()){
        		   						targetFile.createNewFile();							
        		   					}
        		   					
        		   					BufferedWriter output = new BufferedWriter(new FileWriter(targetFile.getPath(), true));
        		   					
        		   					output.write("");
        		   					output.write("$");
        		   					
        		   					output.write(twit.getContent());
        		   					output.write("$");
        		   					System.out.println(twit.getContent());
        		   					
        		   					output.write(twit.getRegistDate());
        		   					output.write("$");
        		   					output.newLine();
        		   					System.out.println(twit.getRegistDate());
        	        		   					
        		   					output.close();	
        		   					if(queue.size() < 100){
        	    						queue.add(text);
        	    						
        	    					} else {
        	    						queue.take();
        	    						queue.add(text);
        	    					}
        		   				}
        		   			}
            		   	}
            		   	
    					} catch (Exception e) {
    						// TODO Auto-generated catch block
    						//e.printStackTrace();
    					}
    		}
			@Override
			public void onException(Exception arg0) {}
			@Override
			public void onDeletionNotice(StatusDeletionNotice arg0) {	}
			@Override
			public void onScrubGeo(long arg0, long arg1) {}
			@Override
			public void onStallWarning(StallWarning arg0) {}
			@Override
			public void onTrackLimitationNotice(int arg0) {}
	    	   
       };

/*       FilterQuery fq = new FilterQuery();
       String [ ] params =  {" "};//{"범죄","살인","강간","절도","강도","폭력"};
       fq.track(params);
       fq.language(new String[] { "ko" });*/
       twitterStream.addListener(listener);
       twitterStream.sample();
        
/*       Twitter twitter = TwitterFactory.getSingleton(); 
       
       twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
       
       AccessToken accessToken = null;
       accessToken = new AccessToken(ACCESS_TOKEN, ACCESS_SECRET);
       twitter.setOAuthAccessToken(accessToken);
*/      
       /* // 새 검색 조건을 만듭니다.
        Query query = new Query("\"범죄\"");

        // 검색 결과를 얻어옵니다.
        QueryResult result = twitter.search(query);
        //query.setCount(101);
        // 모든 결과를 반복처리합니다.
        for(Status tweet : result.getTweets()){
            // 트윗들을 화면에 표시합니다.
            System.out.println(tweet.getCreatedAt()
              + ":" 		
              + tweet.getUser().getScreenName() 
              + ":" 
              + tweet.getText());
        }*/
	}
	
	public static boolean filterWord(String text){
		String [ ] filterW = {"풀싸롱", "고액대출", "010","http","시스템베팅","WWW","www","O1O","구글광고전문", "가입문의", "OIO"};
		
		for(String s : filterW){
			if(text.contains(s))
				return false;
		}
		return true;
	}

}
