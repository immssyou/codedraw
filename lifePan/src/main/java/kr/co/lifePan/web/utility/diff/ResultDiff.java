package kr.co.lifePan.web.utility.diff;

public class ResultDiff {

	private final String[] lineA, lineB; 
	private final Item[] item;
	
	public ResultDiff(String[] lineA, String[] lineB, Item[] item) {
		this.lineA = lineA;
		this.lineB = lineB;
		this.item = item;
	}
	
	public String[] getLineA() {
		return lineA;
	}
	public String[] getLineB() {
		return lineB;
	}
	public Item[] getItem() {
		return item;
	}
}
