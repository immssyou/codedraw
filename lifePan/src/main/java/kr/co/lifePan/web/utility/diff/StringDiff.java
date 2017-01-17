package kr.co.lifePan.web.utility.diff;

import java.util.ArrayList;
import java.util.List;

public class StringDiff {

	private static Diff diff = new Diff();
	
	public static ResultDiff procTextDiff(String strA, String strB, boolean trimSpace, boolean ignoreSpace, boolean ignoreCase) throws Exception {
		
		Item[] item = diff.diffText(strA, strB, trimSpace, ignoreSpace, ignoreCase);
			
		return new ResultDiff(
				strA.split("\r\n"),
				strB.split("\r\n"),
				item);
	}

	public static List<ResultViewDiff> textDiff(String left, String right) throws Exception {
		return textDiff(left, right, false, false, false);		
	}
	public static List<ResultViewDiff> textDiff(String left, String right, boolean trimSpace, boolean ignoreSpace, boolean ignoreCase) throws Exception {
		return loop(procTextDiff(left, right, trimSpace, ignoreSpace, ignoreCase));		
	}
		
	private static List<ResultViewDiff> loop(ResultDiff result) {
		List<ResultViewDiff> list = new ArrayList<>();
		
		String[] aLines = result.getLineA();
		String[] bLines = result.getLineB();
		Item[] items = result.getItem();
		
		int n = 0, x = 0;
		for(int fidx=0;fidx<items.length;++fidx) {
			Item item = items[fidx];

			while(n < item.getStartA()) {
				list.add(new ResultViewDiff(Integer.toString(n+1), aLines[n], "", ""));
				++x;
				++n;
			}

			for(int i=0;i<item.getDeletedA();++i) {
				list.add(new ResultViewDiff(Integer.toString(n+1), aLines[n], "", ""));
				++x;
				++n;
			}

			for(int i=0;i<item.getInsertedB() - item.getDeletedA();++i) {
				list.add(new ResultViewDiff("", "", "", ""));
				++x;
			}
		}
		for(int i=n;i<aLines.length;++i) {
			list.add(new ResultViewDiff(Integer.toString(i+1), aLines[i], "", ""));
			++x;
		}
		
		n = 0; x = 0;
		for(int fidx=0;fidx<items.length;++fidx) {
			Item item = items[fidx];

			while(n < item.getStartB()) {
				if(x < list.size()) {
					list.get(x).setRight(bLines[n]);
					list.get(x).setRightLine(Integer.toString(n+1));
				} else {
					list.add(new ResultViewDiff("", "", Integer.toString(n+1),  bLines[n]));
				}
				++x;
				++n;
			}

			for(int i=0;i<item.getInsertedB();++i) {
				if(n < list.size()) {
					list.get(x).setRight(bLines[n]);
					list.get(x).setRightLine(Integer.toString(n+1));
				} else {
					list.add(new ResultViewDiff("", "", Integer.toString(n+1), bLines[n]));
				}
				++x;
				++n;
			}

			for(int i=0;i<item.getDeletedA() - item.getInsertedB();++i) {
				if(n < list.size()) {
					list.get(x).setRight("");
					list.get(x).setRightLine("");
				} else {
					list.add(new ResultViewDiff("", "", "", ""));
				}
				++x;
			}
		}
		for(int i=n;i<bLines.length;++i) {
			if(n < list.size()) {
				list.get(x).setRight(bLines[i]);
				list.get(x).setRightLine(Integer.toString(i+1));
			} else {
				list.add(new ResultViewDiff("", "", Integer.toString(i+1), bLines[i]));
			}
			++x;
		}
		
		return list;
	}
}
