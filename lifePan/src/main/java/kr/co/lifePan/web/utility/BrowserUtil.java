package kr.co.lifePan.web.utility;

import javax.servlet.http.HttpServletRequest;

public class BrowserUtil {

	public static boolean isExplorer(HttpServletRequest request) {
		if (request != null) {
			String userAgent = request.getHeader("User-Agent");
			if (userAgent != null && userAgent.toLowerCase().indexOf("msie") != -1) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isExplorer6(HttpServletRequest request) {
		if (request != null) {
			String userAgent = request.getHeader("User-Agent");
			if (userAgent != null && (userAgent.toLowerCase().indexOf("msie 5") != -1 || userAgent.toLowerCase().indexOf("msie 6") != -1)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isExplorer7(HttpServletRequest request) {
		if (request != null) {
			String userAgent = request.getHeader("User-Agent");
			if (userAgent != null && userAgent.toLowerCase().indexOf("msie 7") != -1) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isExplorer8(HttpServletRequest request) {
		if (request != null) {
			String userAgent = request.getHeader("User-Agent");
			if (userAgent != null && userAgent.toLowerCase().indexOf("msie 9") != -1) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isExplorer9(HttpServletRequest request) {
		if (request != null) {
			String userAgent = request.getHeader("User-Agent");
			if (userAgent != null && userAgent.toLowerCase().indexOf("msie 9") != -1) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isChrome(HttpServletRequest request) {
		if (request != null) {
			String userAgent = request.getHeader("User-Agent");
			if (userAgent != null && userAgent.toLowerCase().indexOf("chrome") != -1) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isSafari(HttpServletRequest request) {
		if (request != null) {
			String userAgent = request.getHeader("User-Agent");
			if (userAgent != null && userAgent.toLowerCase().indexOf("applewebkit/") != -1) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isFirefox(HttpServletRequest request) {
		if (request != null) {
			String userAgent = request.getHeader("User-Agent");
			if (userAgent != null && userAgent.toLowerCase().indexOf("gecko") != -1 && userAgent.toLowerCase().indexOf("khtml") == -1) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isMobile(HttpServletRequest request) {
		if (request != null) {
			String userAgent = request.getHeader("User-Agent");
			if (userAgent != null && userAgent.toLowerCase().indexOf("mobile") != -1) {
				return true;
			}
		}
		return false;
	}
}
