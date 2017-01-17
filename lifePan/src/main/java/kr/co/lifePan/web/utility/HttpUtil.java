package kr.co.lifePan.web.utility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpUtil {

	public static boolean isAjaxRequest(HttpServletRequest request) {
		return isAjaxRequest(request, null);
	}

	public static boolean isAjaxRequest(HttpServletRequest request, HttpServletResponse response) {
		if ("XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))) {
			return true;
		}
		if (response != null && response.getContentType() != null && response.getContentType().toLowerCase().startsWith("application/json")) {
			return true;
		}
		return false;
	}
}
