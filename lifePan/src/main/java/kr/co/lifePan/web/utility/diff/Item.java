package kr.co.lifePan.web.utility.diff;

public class Item {
	
	private final int startA, startB;
	private final int deletedA, insertedB;
	
	public Item(int startA, int startB, int deletedA, int insertedB) {
		this.startA = startA;
		this.startB = startB;
		this.deletedA = deletedA;
		this.insertedB = insertedB;
	}
	/**
	 * Start Line number in Data A.
	 */
	public int getStartA() {
		return startA;
	}
	
	/**
	 * Start Line number in Data B.
	 */
	public int getStartB() {
		return startB;
	}
	
	/**
	 * Number of changes in Data A.
	 */
	public int getDeletedA() {
		return deletedA;
	}
	
	/**
	 * Number of changes in Data B.
	 */
	public int getInsertedB() {
		return insertedB;
	}
	
	@Override
	public String toString() {
		return String.format("A[start:%d, deleted:%d] B[start:%d, inserted:%d]", startA, deletedA, startB, insertedB);
	}
}
