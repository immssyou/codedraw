package kr.co.lifePan.web.utility.diff;

public class ResultViewDiff {

	private String leftLine, rightLine;
	private String left, right;
	private Boolean compare;
	private long leftFileNo, rightFileNo;
	private long leftMilliTime, rightMilliTime;	
	
	public ResultViewDiff() {
		this.leftLine = "";
		this.rightLine = "";
		this.left = "";
		this.right = "";
		this.compare = true;
	}
	public ResultViewDiff(String leftLine, String left, String rightLine, String right) {
		this.leftLine = leftLine;
		this.rightLine = rightLine;
		this.left = left;
		this.right = right;
		if(!"".equals(left) && !"".equals(right)) {
			this.compare = left.compareTo(right) == 0 ? true : false;
		} else {
			this.compare = null;
		}
	}
	
	public String getLeftLine() {
		return leftLine;
	}
	
	public void setLeftLine(String leftLine) {
		this.leftLine = leftLine;
	}
	
	public String getRightLine() {
		return rightLine;
	}
	
	public void setRightLine(String rightLine) {
		this.rightLine = rightLine;
	}
	
	public String getLeft() {
		return left;
	}

	public void setLeft(String left) {
		this.left = left;
		
		if(!"".equals(left) && !"".equals(right)) {
			this.compare = left.compareTo(right) == 0 ? true : false;
		} else {
			this.compare = null;
		}
	}

	public String getRight() {
		return right;
	}

	public void setRight(String right) {
		this.right = right;
		if(!"".equals(left) && !"".equals(right)) {
			this.compare = left.compareTo(right) == 0 ? true : false;
		} else {
			this.compare = null;
		}
	}
	
	public Boolean getCompare() {
		return compare;
	}
	
	public long getLeftFileNo() {
		return leftFileNo;
	}
	
	public void setLeftFileNo(long leftFileNo) {
		this.leftFileNo = leftFileNo;
	}
	
	public long getRightFileNo() {
		return rightFileNo;
	}
	
	public void setRightFileNo(long rightFileNo) {
		this.rightFileNo = rightFileNo;
	}
	
	public long getLeftMilliTime() {
		return leftMilliTime;
	}
	
	public void setLeftMilliTime(long leftMilliTime) {
		this.leftMilliTime = leftMilliTime;
	}
	
	public long getRightMilliTime() {
		return rightMilliTime;
	}
	
	public void setRightMilliTime(long rightMilliTime) {
		this.rightMilliTime = rightMilliTime;
	}
	
	@Override
	public String toString() {
		return String.format("%s: %s / %s: %s | %b",leftLine,left,rightLine,right,compare);
	}
}
