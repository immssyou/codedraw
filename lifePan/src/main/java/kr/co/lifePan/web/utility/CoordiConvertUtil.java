package kr.co.lifePan.web.utility;

import java.util.StringTokenizer;

public class CoordiConvertUtil {

	public static float strToDoubleLongitude(String strLon) {
		double dResult = 0;
		double dTmp;
		int nTokenNumber = 0;

		try {
			byte[] bTemp = strLon.getBytes();
			for(int i=0 ; i<strLon.length() ; i++) {
				if(bTemp[i] == 0x2D) nTokenNumber++;
			}
			
			StringTokenizer st = new StringTokenizer(strLon,"-");
			dTmp = Double.parseDouble(st.nextToken());
			
			dResult += dTmp;
			dTmp = Double.parseDouble(st.nextToken());
			
			dResult += (dTmp/60);
			dTmp = Double.parseDouble(st.nextToken());
			
			dResult += (dTmp/3600);
			
			if(nTokenNumber == 3) {
				dResult = -dResult;
			}
		} catch(Exception e) {
			dResult = 0;
		}
		
		return (float)dResult;
	}
	
	public static float strToDoubleLatitude(String strLat) {
		double dResult = 0;
		double dTmp;
		int nTokenNumber = 0;

		try {
			byte[] bTemp = strLat.getBytes();
			for(int i=0 ; i<strLat.length() ; i++) {
				if(bTemp[i] == 0x2D) nTokenNumber++;
			}

			StringTokenizer st = new StringTokenizer(strLat,"-");
			dTmp = Double.parseDouble(st.nextToken());
			dResult += dTmp;

			dTmp = Double.parseDouble(st.nextToken());
			dResult += (dTmp/60);

			dTmp = Double.parseDouble(st.nextToken());
			dResult += (dTmp/3600);

			if(nTokenNumber == 3) {
				dResult = -dResult;
			}
		} catch(Exception e) {
			dResult = 0;
		}
		
		return (float)dResult;
	}
}
