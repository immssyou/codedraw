package kr.co.lifePan.web.domain;

import org.apache.ibatis.type.Alias;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Alias("klifeSubContent")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class klifeSubContent {

	/*klifeSubContent*/
	private Long subContentNo;
	private Long boardNo;
	private Integer userNo;
	private String content;
	private Integer likeCount;
	private String registDate;
	private String modifiedDate;
	private char used;
	
	
	public Long getSubContentNo() {
		return subContentNo;
	}
	public void setSubContentNo(Long subContentNo) {
		this.subContentNo = subContentNo;
	}
	public Long getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(Long boardNo) {
		this.boardNo = boardNo;
	}
	public Integer getUserNo() {
		return userNo;
	}
	public void setUserNo(Integer userNo) {
		this.userNo = userNo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}
	public String getRegistDate() {
		return registDate;
	}
	public void setRegistDate(String registDate) {
		this.registDate = registDate;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public char getUsed() {
		return used;
	}
	public void setUsed(char used) {
		this.used = used;
	}
	
	
	
}
