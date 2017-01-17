package kr.co.lifePan.web.utility.diff;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ResultMessageDiff {

	private String leftFileName;
	private String rightFileName;
	private String leftHeader;
	private String rightHeader;
	private List<ResultViewDiff> list;
	
	public String getLeftFileName() {
		return leftFileName;
	}
	public void setLeftFileName(String leftFileName) {
		this.leftFileName = leftFileName;
	}
	public String getRightFileName() {
		return rightFileName;
	}
	public void setRightFileName(String rightFileName) {
		this.rightFileName = rightFileName;
	}
	public String getLeftHeader() {
		return leftHeader;
	}
	public void setLeftHeader(String leftHeader) {
		this.leftHeader = leftHeader;
	}
	public String getRightHeader() {
		return rightHeader;
	}
	public void setRightHeader(String rightHeader) {
		this.rightHeader = rightHeader;
	}
	public List<ResultViewDiff> getList() {
		return list;
	}
	public void setList(List<ResultViewDiff> list) {
		this.list = list;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}		
}
