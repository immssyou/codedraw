package kr.co.lifePan.web.utility.diff;

import java.util.ArrayList;
import java.util.Hashtable;

final class Diff {
	
	/**
	 * Find the difference in 2 texts, comparing by textlines.
	 * @param textA - A-version of the text (usualy the old one)
	 * @param textB - B-version of the text (usualy the new one)
	 * @return Returns a array of Items that describe the differences.
	 * @exception Exception
	 */
	public Item[] diffText(String textA, String textB) throws Exception {
		return diffText(textA, textB, false, false, false);
	}
	
	/**
	 * Find the difference in 2 text documents, comparing by textlines.
	 * The algorithm itself is comparing 2 arrays of numbers so when comparing 2 text documents
	 * each line is converted into a (hash) number. This hash-value is computed by storing all
	 * textlines into a common hashtable so i can find dublicates in there, and generating a
	 * new number each time a new textline is inserted.
	 * 
	 * @param textA - A-version of the text (usualy the old one)
	 * @param textB - B-version of the text (usualy the new one)
	 * @param trimSpace - When set to true, all leading and trailing whitespace characters are stripped out before the comparation is done.
	 * @param ignoreSpace - When set to true, all whitespace characters are converted to a single space character before the comparation is done.
	 * @param ignoreCase - When set to true, all characters are converted to their lowercase equivivalence before the comparation is done.
	 * @return Returns a array of Items that describe the differences.
	 * @exception Exception
	 */
	public Item[] diffText(String textA, String textB, boolean trimSpace, boolean ignoreSpace, boolean ignoreCase) throws Exception {
		Hashtable<String,Integer> table = new Hashtable<String,Integer>(textA.length() + textB.length());
		DiffData dataA = new DiffData(diffCodes(textA, table, trimSpace, ignoreSpace, ignoreCase));
		DiffData dataB = new DiffData(diffCodes(textB, table, trimSpace, ignoreSpace, ignoreCase));
		
		table.clear();
		table = null;
		int max = dataA.length + dataB.length + 1;
		int[] downVector = new int[2 * max + 2];
		int[] upVector = new int[2 * max + 2];
		
		lcs(dataA, 0, dataA.length, dataB, 0, dataB.length, downVector, upVector);
		
		optimize(dataA);
		optimize(dataB);
				
		return createDiffs(dataA, dataB);
	}
	
	private void optimize(DiffData data) {
		int startPos, endPos;
		startPos = 0;
		while(startPos < data.length) {
			while((startPos < data.length) && (data.modified[startPos] == false))
				startPos++;
			endPos = startPos;
			
			while((endPos < data.length) && (data.modified[endPos] == true)) 
				endPos++;
			
			if((endPos < data.length) && (data.data[startPos] == data.data[endPos])) {
				data.modified[startPos] = false;
				data.modified[endPos] = true;
			} else {
				startPos = endPos;
			}
		}
	}
	
	public Item[] diffInt(int[] arrayA, int[] arrayB) throws Exception {
		DiffData dataA = new DiffData(arrayA);
		DiffData dataB = new DiffData(arrayB);
		
		int max = dataA.length + dataB.length + 1;
		int[] downVector = new int[2 * max + 2];
		int[] upVector = new int[2 * max + 2];
		
		lcs(dataA, 0, dataA.length, dataB, 0, dataB.length, downVector, upVector);
		return createDiffs(dataA, dataB);
	}
	
	private Item[] createDiffs(DiffData dataA, DiffData dataB) {
		ArrayList<Item> list = new ArrayList<Item>();
		
		int startA, startB;
		int lineA=0, lineB=0;
		
		while(lineA < dataA.length || lineB < dataB.length) {
			if((lineA < dataA.length) && (!dataA.modified[lineA]) && (lineB < dataB.length) && (!dataB.modified[lineB])) {
				lineA++;
				lineB++;
			} else {
				startA = lineA;
				startB = lineB;
				
				while(lineA < dataA.length && (lineB >= dataB.length || dataA.modified[lineA]))
					lineA++;
				
				while(lineB < dataB.length && (lineA >= dataA.length || dataB.modified[lineB]))
					lineB++;
				
				if((startA < lineA) || (startB < lineB)) {
					list.add(new Item(startA, startB, lineA-startA, lineB-startB));
				}
			}
		}
		
		Item[] result = new Item[list.size()];
		list.toArray(result);
		
		return result;
	}
	
	private int[] diffCodes(String text, Hashtable<String,Integer> h, boolean trimSpace, boolean ignoreSpace, boolean ignoreCase) {
		String[] lines;
		int[] codes;
		int lastUsedCode = h.size();
		Object aCode;
		String s;
		
		text = text.replace("\r", "");
		lines = text.split("\n");
		
		codes = new int[lines.length];
		
		for(int i=0;i<lines.length;++i) {
			s = lines[i];
			if(trimSpace)
				s = s.trim();
			
			if(ignoreSpace)
				s = s.replaceAll("\\s+", " ");
			
			if(ignoreCase)
				s = s.toLowerCase();
			
			aCode = h.get(s);
			if(aCode == null) {
				lastUsedCode++;
				h.put(s, lastUsedCode);
				codes[i] = lastUsedCode;
			} else {
				codes[i] = (int)aCode;
			}
		}
		return codes;
	}			
	
	private void lcs(DiffData dataA, int lowerA, int upperA, DiffData dataB, int lowerB, int upperB, int[] downVector, int[] upVector) throws Exception {
		while(lowerA < upperA && lowerB < upperB && dataA.data[lowerA] == dataB.data[lowerB]) {
			lowerA++;
			lowerB++;
		}
		
		while(lowerA < upperA && lowerB < upperB && dataA.data[upperA-1] == dataB.data[upperB-1]) {
			--upperA;
			--upperB;
		}
		
		if(lowerA == upperA) {
			while(lowerB < upperB)
				dataB.modified[lowerB++] = true;
		} else if(lowerB == upperB) {
			while(lowerA < upperA)
				dataA.modified[lowerA++] = true;
		} else {
			SMSRD smsrd = sms(dataA, lowerA, upperA, dataB, lowerB, upperB, downVector, upVector);
			
			lcs(dataA, lowerA, smsrd.x, dataB, lowerB, smsrd.y, downVector, upVector);
			lcs(dataA, smsrd.x, upperA, dataB, smsrd.y, upperB, downVector, upVector);
		}
	}		
	
	private SMSRD sms(DiffData dataA, int lowerA, int upperA, DiffData dataB, int lowerB, int upperB, int[] downVector, int[] upVector) throws Exception {
		SMSRD ret = new SMSRD();
		int max = dataA.length + dataB.length + 1;
		
		int downK = lowerA - lowerB;
		int upK = upperA - upperB;
		
		int Delta = (upperA - lowerA) - (upperB - lowerB);
		boolean oddDelta = (Delta & 1) != 0;
		
		int downOffset = max - downK;
		int upOffset = max - upK;
		
		int maxD = ((upperA - lowerA + upperB - lowerB) / 2) + 1;
		
		downVector[downOffset + downK + 1] = lowerA;
		upVector[upOffset + upK - 1] = upperA;
		
		for(int i=0;i<=maxD;i++) {
			for(int j=downK-i;j<=downK+i;j+=2) {
				int x,y;
				if(j == downK - i) {
					x = downVector[downOffset + j + 1];
				} else {
					x = downVector[downOffset + j - 1] + 1;
					if((j < downK + i) && (downVector[downOffset + j + 1] >= x))
						x = downVector[downOffset + j + 1];
				}
				y = x - j;
				
				while((x < upperA) && (y < upperB) && (dataA.data[x] == dataB.data[y])) {
					x++;
					y++;
				}
				downVector[downOffset + j] = x;
				
				if(oddDelta && (upK - i < j) && (j < upK + i)) {
					if(upVector[upOffset + j] <= downVector[downOffset + j]) {
						ret.x = downVector[downOffset + j];
						ret.y = downVector[downOffset + j] - j;
						return ret;
					}
				}
			}
			
			for(int k=upK-i;k<=upK+i;k+=2) {
				int x,y;
				if(k == upK + i) {
					x = upVector[upOffset + k - 1];
				} else {
					x = upVector[upOffset + k + 1] - 1;
					if((k > upK - i) && (upVector[upOffset + k - 1] < x))
						x = upVector[upOffset + k - 1];
				}
				y = x - k;

				while((x > lowerA) && (y > lowerB) && (dataA.data[x-1] == dataB.data[y-1])) {
					x--;
					y--;
				}
				upVector[upOffset + k] = x;

				if(!oddDelta && (downK - i <= k) && (k <= downK + i)) {
					if(upVector[upOffset + k] <= downVector[downOffset + k])
					{
						ret.x = downVector[downOffset + k];
						ret.y = downVector[downOffset + k] - k;
						return (ret);
					}
				}
			}
		}
		
		throw new Exception("the algorithm should never come here.");
	}

	private class SMSRD {
		public int x, y;
	}
	
	private class DiffData {
		
		private int length;
		private int[] data;
		private boolean[] modified;
		
		public DiffData(int[] initData) {
			this.data = initData;
			length = initData.length;
			modified = new boolean[length+2];
		}		
	}
}
