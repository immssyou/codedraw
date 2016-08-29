package crawler.crawler;

public class Board {
	private String title; //제목
	private String content; //내용
	private String writer; //작성자
	private Long count; //조회수
	private String registDate; // 작성일
	private Integer likes; //추천수
	private String url;
	private String registTime;//작성시간
	
	private String content_c; //comment 내용
	private String writer_c; //comment  작성자
	private String registDate_c; // comment 작성일
	private Integer likes_c; //comment 추천수
	
	private Integer communityNo; //커뮤니티 분류
	
	private String year;
	private String month;
	private String day;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getRegistDate() {
		return registDate;
	}
	public void setRegistDate(String registDate) {
		this.registDate = registDate;
	}
	public String getContent_c() {
		return content_c;
	}
	public void setContent_c(String content_c) {
		this.content_c = content_c;
	}
	public String getWriter_c() {
		return writer_c;
	}
	public void setWriter_c(String writer_c) {
		this.writer_c = writer_c;
	}
	public String getRegistDate_c() {
		return registDate_c;
	}
	public void setRegistDate_c(String registDate_c) {
		this.registDate_c = registDate_c;
	}
	public Integer getLikes_c() {
		return likes_c;
	}
	public void setLikes_c(Integer likes_c) {
		this.likes_c = likes_c;
	}
	public int getCommunityNo() {
		return communityNo;
	}
	public void setCommunityNo(int communityNo) {
		this.communityNo = communityNo;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Integer getLikes() {
		return likes;
	}
	public void setLikes(Integer likes) {
		this.likes = likes;
	}
	public void setCommunityNo(Integer communityNo) {
		this.communityNo = communityNo;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getRegistTime() {
		return registTime;
	}
	public void setRegistTime(String registTime) {
		this.registTime = registTime;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		if(this.registDate != null)
			return this.registDate.substring(4, 6);
		else
			return null;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDay() {
		if(this.registDate != null)
			return this.registDate.substring(6, 8);
		else
			return null;
	}
	public void setDay(String day) {
		this.day = day;
	}
	
	
}
