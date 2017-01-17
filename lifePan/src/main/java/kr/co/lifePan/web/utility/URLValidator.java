package kr.co.lifePan.web.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLValidator {

	private static final Pattern PATTERN_URL = Pattern.compile("^((http(s)?|ftp):\\/\\/)?+((([^\\/:]\\S+\\.)?([[^\\/.:]&&\\S]+)?+)(:(\\p{Digit}+))?+)?+(\\/([\\S&&[^\\?#]])*)?(\\??(&?[\\S&&[^&=#]]+=?[\\S&&[^&=#]]+)*)?(#.*)?$");
	private boolean isValid;
	private String scheme;
	private String domain;
	private int port;
	private String path;
	private String queryString;
	private String fragmentId;
	private String url;

	public URLValidator(String url) {
		this.url = url;
		Matcher matcher = PATTERN_URL.matcher(url);
		isValid = matcher.matches();
		if (isValid) {
			scheme = matcher.group(2);
			if (matcher.group(3) != null) {
				scheme = scheme + matcher.group(3);
			}
			domain = matcher.group(5);
			if (matcher.group(9) != null) {
				try {
					port = Integer.parseInt(matcher.group(9));
				} catch (NumberFormatException ex) {
				}
			}
			if (port == 0) {
				if ("http".equalsIgnoreCase(scheme)) {
					port = 80;
				} else if ("https".equalsIgnoreCase(scheme)) {
					port = 443;
				} else if ("ftp".equalsIgnoreCase(scheme)) {
					port = 21;
				}
			}
			if (matcher.group(10) != null) {
				path = matcher.group(10);
			}
			if (matcher.group(12) != null) {
				queryString = matcher.group(12);
				if (queryString.startsWith("?")) {
					queryString = queryString.substring(1);
				}
			}
			if (matcher.group(14) != null) {
				fragmentId = matcher.group(14);
				if (fragmentId.startsWith("#")) {
					fragmentId = fragmentId.substring(1);
				}
			}
		}
	}

	public static boolean isValidUrl(String url) {
		return PATTERN_URL.matcher(url).matches();
	}

	public String getUrl() {
		return url;
	}

	public String getDomain() {
		return domain;
	}

	public String getFragmentId() {
		return fragmentId;
	}

	public boolean isValid() {
		return isValid;
	}

	public String getPath() {
		return path;
	}

	public int getPort() {
		return port;
	}

	public String getQueryString() {
		return queryString;
	}

	public String getScheme() {
		return scheme;
	}
}
