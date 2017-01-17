package kr.co.lifePan.web.utility;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	public static final Pattern SPACE_PATTERN = Pattern.compile("(\\s)+");
	public static final Pattern FIRST_SPACE_PATTERN = Pattern.compile("^(\\s)+");
	public static final Pattern LAST_SPACE_PATTERN = Pattern.compile("(\\s)+$");
	public static final Pattern MORE_SPACE_PATTERN = Pattern.compile("(\\s){2,}");
	private static final Pattern PATTERN_ZIP = Pattern.compile("^(\\d\\d\\d)?([-])?(\\d\\d\\d)?$");
	private static final Pattern PATTERN_PHONE_NO = Pattern.compile("^(\\+)?(\\d)+(([\\s\\-])*(\\d)+)+$");
	private static final Pattern PATTERN_PHONE = Pattern.compile("^(\\d\\d(\\d)?(\\d)?)?([-])?(\\d\\d\\d(\\d)?)+([-])?(\\d\\d\\d\\d)+$");
	private static final Pattern PATTERN_KOREAN_PHONE = Pattern.compile("^(02|031|062|053|042|051|032|052|033|055|054|061|063|064|041|043|070|0303|010|011|016|017|018|019)?([-])?(\\d\\d\\d(\\d)?)+([-])?(\\d\\d\\d\\d)+$");
	private static final Pattern PATTERN_KOREAN_MOBILE = Pattern.compile("^(010|011|016|017|018|019)?([-])?(\\d\\d\\d(\\d)?)+([-])?(\\d\\d\\d\\d)+$");
	private static final Pattern PATTERN_EMAIL = Pattern.compile("^(.+)?@(.+\\.[a-z]+)?$");
	private static final Pattern PATTERN_ALPHABET = Pattern.compile("^[a-zA-Z]+$");
	private static final Pattern PATTERN_LOWER_ALPHABET = Pattern.compile("^[a-z]+$");
	private static final Pattern PATTERN_UPPER_ALPHABET = Pattern.compile("^[A-Z]+$");
	private static final Pattern PATTERN_ALPHABET_DIGIT = Pattern.compile("^[a-zA-Z0-9]+$");
	private static final Pattern PATTERN_DIGIT = Pattern.compile("^[0-9]+$");
	private static final Pattern PATTERN_LOWER_ALPHABET_DIGIT = Pattern.compile("^[a-z0-9]+$");
	private static final Pattern PATTERN_UPPER_ALPHABET_DIGIT = Pattern.compile("^[A-Z0-9]+$");
	private static final Pattern PATTERN_ALPHABET_DIGIT_WITH_WHITESPACE = Pattern.compile("^[a-zA-Z0-9\\s]+$");
	private static final Pattern PATTERN_TRIM_FIRST_DIGITS = Pattern.compile("^([0-9])+");
	private static final Pattern PATTERN_MACID = Pattern.compile("^([0-9a-fA-F]{0,2})?(:)?([0-9a-fA-F]{0,2})?(:)?([0-9a-fA-F]{0,2})?(:)?([0-9a-fA-F]{0,2})?(:)?([0-9a-fA-F]{0,2})?(:)?([0-9a-fA-F]{0,2})?$");
	private static final Pattern PATTERN_MACID_EXACT = Pattern.compile("^(([a-fA-F0-9]{2}(:)){5,6}([a-fA-F0-9]{2}))?$");

	private StringUtil() {
	}

	/**
	 * 반각문자로 변경한다
	 * @param src 변경할 값
	 * @return String 변경된 값
	 */
	public static String halfChar(String src) {
		if (src == null) {
			return "";
		}
		StringBuilder strBuf = new StringBuilder();
		char c = 0;
		int nSrcLength = src.length();
		for (int i = 0; i < nSrcLength; i++) {
			c = src.charAt(i);
			if (c >= '！' && c <= '～') {
				c -= 0xfee0;
			} else if (c == '　') {
				c = 0x20;
			}

			strBuf.append(c);
		}
		return strBuf.toString();
	}

	/**
	 * 전각문자로 변경한다.
	 * @param src 변경할 값
	 * @return String 변경된 값
	 */
	public static String fullChar(String src) {
		if (src == null) {
			return "";
		}

		StringBuilder strBuf = new StringBuilder();
		char c = 0;
		int nSrcLength = src.length();
		for (int i = 0; i < nSrcLength; i++) {
			c = src.charAt(i);
			if (c >= 0x21 && c <= 0x7e) {
				c += 0xfee0;
			} else if (c == 0x20) {
				c = 0x3000;
			}
			strBuf.append(c);
		}
		return strBuf.toString();
	}

	public static String regNo(String bizRegNo) {
		if (bizRegNo == null) {
			return "";
		}
		bizRegNo = bizRegNo.trim();
		if (bizRegNo.length() == 10) {
			return bizRegNo.substring(0, 3) + "-" + bizRegNo.substring(3, 5) + "-" + bizRegNo.substring(5);
		}
		if (bizRegNo.length() == 13) {
			return bizRegNo.substring(0, 6) + "-" + bizRegNo.substring(6);
		}
		return bizRegNo;
	}

	public static boolean isEmptyString(String value) {
		if (value == null) {
			return true;
		}
		if (value.trim().isEmpty()) {
			return true;
		}

		return false;
	}

	public static boolean isNullString(String value) {
		if (value == null) {
			return true;
		}

		return false;
	}

	public static String stringAt(String numberValue, String posValue) {
		if (numberValue == null || "".equals(numberValue.trim())) {
			return "";
		}
		int pos = Integer.parseInt(posValue);
		numberValue = numberValue.trim();
		int len = numberValue.length();
		if (pos > 0 && len >= pos) {
			return numberValue.substring(len - pos, len - (pos - 1));
		}
		return "";
	}

//	public static Date currentDate() {
//		return DateUtil.now().getTime();
//	}
//
//	public static String currentDate(String pattern) {
//		return DateUtil.now().format(pattern);
//	}
	public static String zip1(String value) {
		if (value == null || "".equals(value.trim())) {
			return "";
		}
		value = value.trim();
		Matcher matcher = PATTERN_ZIP.matcher(value);
		if (matcher.find()) {
			String retValue = matcher.group(1);
			if (retValue == null) {
				return "";
			}
			return retValue;
		}
		return "";
	}

	public static String zip2(String value) {
		if (value == null || "".equals(value.trim())) {
			return "";
		}
		value = value.trim();
		Matcher matcher = PATTERN_ZIP.matcher(value);
		if (matcher.find()) {
			String retValue = matcher.group(3);
			if (retValue == null) {
				return "";
			}
			return retValue;
		}
		return "";
	}

	public static String zip(String value) {
		if (value == null || "".equals(value.trim())) {
			return "";
		}
		String retValue = "";
		value = value.trim();
		Matcher matcher = PATTERN_ZIP.matcher(value);
		if (matcher.find()) {
			String zip1 = matcher.group(1);
			String zip2 = matcher.group(3);
			if (zip1 != null && !"".equals(zip1.trim())) {
				retValue = zip1 + "-";
			}
			if (zip2 != null && !"".equals(zip2.trim())) {
				retValue = retValue + zip2;
			}
		}
		return retValue;
	}

	public static String telNo1(String value) {
		if (value == null || "".equals(value.trim())) {
			return "";
		}
		value = value.trim();
		Matcher matcher = PATTERN_PHONE.matcher(value);
		if (matcher.find()) {
			String retValue = matcher.group(1);
			if (retValue == null) {
				return "";
			}
			return retValue;
		}
		return "";
	}

	public static String telNo2(String value) {
		if (value == null || "".equals(value.trim())) {
			return "";
		}
		value = value.trim();
		Matcher matcher = PATTERN_PHONE.matcher(value);
		if (matcher.find()) {
			String retValue = matcher.group(5);
			if (retValue == null) {
				return "";
			}
			return retValue;
		}
		return "";
	}

	public static String telNo3(String value) {
		if (value == null || "".equals(value.trim())) {
			return "";
		}
		value = value.trim();
		Matcher matcher = PATTERN_PHONE.matcher(value);
		if (matcher.find()) {
			String retValue = matcher.group(8);
			if (retValue == null) {
				return "";
			}
			return retValue;
		}
		return "";
	}

	public static String telNo(String value) {
		if (value == null || "".equals(value.trim())) {
			return "";
		}
		value = value.trim();
		StringBuilder sb = new StringBuilder();
		Matcher matcher = PATTERN_PHONE.matcher(value);
		if (matcher.find()) {
			String telNo1 = matcher.group(1);
			String telNo2 = matcher.group(5);
			String telNo3 = matcher.group(8);
			if (telNo1 != null && !"".equals(telNo1.trim())) {
				sb.append(telNo1).append("-");
			}
			if (telNo2 != null && !"".equals(telNo2.trim())) {
				sb.append(telNo2).append("-");
			}
			if (telNo3 != null && !"".equals(telNo3.trim())) {
				sb.append(telNo3);
			}
		}
		return sb.toString();
	}

	public static String koreanTelNo(String value) {
		if (value == null || "".equals(value.trim())) {
			return "";
		}
		value = value.trim();
		StringBuilder sb = new StringBuilder();
		Matcher matcher = PATTERN_KOREAN_PHONE.matcher(value);
		if (matcher.find()) {
			String telNo1 = matcher.group(1);
			String telNo2 = matcher.group(3);
			String telNo3 = matcher.group(6);
			if (telNo1 != null && !"".equals(telNo1.trim())) {
				sb.append(telNo1).append("-");
			}
			if (telNo2 != null && !"".equals(telNo2.trim())) {
				sb.append(telNo2).append("-");
			}
			if (telNo3 != null && !"".equals(telNo3.trim())) {
				sb.append(telNo3);
			}
		}
		return sb.toString();
	}

	public static String koreanTelNo1(String value) {
		if (value == null || "".equals(value.trim())) {
			return "";
		}
		value = value.trim();
		Matcher matcher = PATTERN_KOREAN_PHONE.matcher(value);
		if (matcher.find()) {
			String retValue = matcher.group(1);
			if (retValue == null) {
				return "";
			}
			return retValue;
		}
		return "";
	}

	public static String koreanTelNo2(String value) {
		if (value == null || "".equals(value.trim())) {
			return "";
		}
		value = value.trim();
		Matcher matcher = PATTERN_KOREAN_PHONE.matcher(value);
		if (matcher.find()) {
			String retValue = matcher.group(3);
			if (retValue == null) {
				return "";
			}
			return retValue;
		}
		return "";
	}

	public static String koreanTelNo3(String value) {
		if (value == null || "".equals(value.trim())) {
			return "";
		}
		value = value.trim();
		Matcher matcher = PATTERN_KOREAN_PHONE.matcher(value);
		if (matcher.find()) {
			String retValue = matcher.group(6);
			if (retValue == null) {
				return "";
			}
			return retValue;
		}
		return "";
	}

	public static String email(String value) {
		if (value == null || "".equals(value.trim())) {
			return "";
		}
		value = value.trim();
		Matcher matcher = PATTERN_EMAIL.matcher(value);
		if (matcher.find()) {
			String emailId = matcher.group(1);
			if (emailId == null) {
				emailId = "";
			}
			String emailDomain = matcher.group(2);
			if (emailDomain == null) {
				emailDomain = "";
			}
			if (!"".equals(emailId) && !"".equals(emailDomain)) {
				return value;
			}
		}
		return "";
	}

	public static String emailId(String value) {
		if (value == null || "".equals(value.trim())) {
			return "";
		}
		value = value.trim();
		Matcher matcher = PATTERN_EMAIL.matcher(value);
		if (matcher.find()) {
			String retValue = matcher.group(1);
			if (retValue == null) {
				return "";
			}
			return retValue;
		}
		return "";
	}

	public static String emailDomain(String value) {
		if (value == null || "".equals(value.trim())) {
			return "";
		}
		value = value.trim();
		Matcher matcher = PATTERN_EMAIL.matcher(value);
		if (matcher.find()) {
			String retValue = matcher.group(2);
			if (retValue == null) {
				return "";
			}
			return retValue;
		}
		return "";
	}

	public static String macId(String value, int index) {
		if (value == null || "".equals(value.trim())) {
			return "";
		}
		int idx = 0;
		switch (index) {
			case 1:
				idx = 1;
				break;
			case 2:
				idx = 3;
				break;
			case 3:
				idx = 5;
				break;
			case 4:
				idx = 7;
				break;
			case 5:
				idx = 9;
				break;
			case 6:
				idx = 11;
				break;
			default:
				idx = 0;
		}
		value = value.trim();
		Matcher matcher = PATTERN_MACID.matcher(value);
		if (matcher.find()) {
			String retValue = matcher.group(idx);
			if (retValue == null) {
				return "";
			}
			return retValue;
		}
		return "";
	}

	/**
	 * Decodes the passed UTF-8 String using an algorithm that's compatible with
	 * JavaScript's <code>decodeURIComponent</code> function. Returns
	 * <code>null</code> if the String is <code>null</code>.
	 *
	 * @param s The UTF-8 encoded String to be decoded
	 * @param charset
	 * @return the decoded String
	 */
	public static String decodeURIComponent(String s, String charset) {
		if (s == null) {
			return null;
		}

		String result = null;

		try {
			result = URLDecoder.decode(s, charset);
		} // This exception should never occur.
		catch (UnsupportedEncodingException e) {
			result = s;
		}

		return result;
	}

	/**
	 * Decodes the passed UTF-8 String using an algorithm that's compatible with
	 * JavaScript's <code>decodeURIComponent</code> function. Returns
	 * <code>null</code> if the String is <code>null</code>.
	 *
	 * @param s The UTF-8 encoded String to be decoded
	 * @return the decoded String
	 */
	public static String decodeURIComponent(String s) {
		return decodeURIComponent(s, "UTF-8");
	}

	/**
	 * Encodes the passed String as UTF-8 using an algorithm that's compatible
	 * with JavaScript's <code>encodeURIComponent</code> function. Returns
	 * <code>null</code> if the String is <code>null</code>.
	 *
	 * @param s The String to be encoded
	 * @param charset 
	 * @return the encoded String
	 */
	public static String encodeURIComponent(String s, String charset) {
		String result = null;

		try {
			result = URLEncoder.encode(s, charset).replaceAll("\\+", "%20").replaceAll("\\%21", "!").replaceAll("\\%27", "'").replaceAll("\\%28", "(").replaceAll("\\%29", ")").replaceAll("\\%7E", "~");
		} // This exception should never occur.
		catch (UnsupportedEncodingException e) {
			result = s;
		}

		return result;
	}

	/**
	 * Encodes the passed String as UTF-8 using an algorithm that's compatible
	 * with JavaScript's <code>encodeURIComponent</code> function. Returns
	 * <code>null</code> if the String is <code>null</code>.
	 *
	 * @param s The String to be encoded
	 * @return the encoded String
	 */
	public static String encodeURIComponent(String s) {
		return encodeURIComponent(s, "UTF-8");
	}

	public static String trimTag(String source) {
		return (source == null ? source : source.replaceAll("\\<.*?\\>", ""));
	}

	public static String ltrim(String source) {
		try {
			StringBuffer output = new StringBuffer();
			Matcher matcher = FIRST_SPACE_PATTERN.matcher(source);
			while (matcher.find()) {
				matcher.appendReplacement(output, "");
			}
			matcher.appendTail(output);
			return output.toString();
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
			return source;
		}
	}

	public static String rtrim(String source) {
		try {
			StringBuffer output = new StringBuffer();
			Matcher matcher = LAST_SPACE_PATTERN.matcher(source);
			while (matcher.find()) {
				matcher.appendReplacement(output, "");
			}
			matcher.appendTail(output);
			return output.toString();
		} catch (Exception ex) {
			return source;
		}
	}

	public static String trimAll(String source) {
		try {
			StringBuffer output = new StringBuffer();
			Matcher matcher = SPACE_PATTERN.matcher(source);
			while (matcher.find()) {
				matcher.appendReplacement(output, "");
			}
			matcher.appendTail(output);
			return output.toString();
		} catch (Exception ex) {
			return source;
		}
	}

	public static String trimMore(String source) {
		try {
			StringBuffer output = new StringBuffer();
			Matcher matcher = MORE_SPACE_PATTERN.matcher(source);
			while (matcher.find()) {
				matcher.appendReplacement(output, " ");
			}
			matcher.appendTail(output);
			return output.toString();
		} catch (Exception ex) {
			return source;
		}
	}

	public static String paddingLeft(String value, String padding, int length, int maxlength) {
		if (value != null && value.length() > maxlength) {
			value = value.substring(0, maxlength);
		}
		return paddingLeft(value, padding, length);
	}

	public static String paddingLeft(String value, String padding, int length) {
		if (value == null || "".equals(value.trim())) {
			return repeat(padding, length);
		}
		if (value.length() > length) {
			return value;
		}
		return repeat(padding, length - value.length()) + value;
	}

	public static String paddingRight(String value, String padding, int length, int maxlength) {
		if (value != null && value.length() > maxlength) {
			value = value.substring(0, maxlength);
		}
		return paddingRight(value, padding, length);
	}

	public static String paddingRight(String value, String padding, int length) {
		if (value == null || "".equals(value.trim())) {
			return repeat(padding, length);
		}
		if (value.length() > length) {
			return value;
		}
		return value + repeat(padding, length - value.length());
	}

	public static String repeat(String value, int length) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append(value);
		}
		return sb.toString();
	}

	public static boolean isValidURL(String url) {
		return URLValidator.isValidUrl(url);
	}

	public static String html2special(String source) {
		source = (source == null ? "" : source.trim());
		source = special2html(source);
		source = source.replaceAll("&", "&amp;");
		source = source.replaceAll("\"", "&quot;");
		source = source.replaceAll("<", "&lt;");
		source = source.replaceAll(">", "&gt;");
		return source;
	}

	public static String special2html(String source) {
		source = (source == null ? "" : source.trim());
		source = source.replaceAll("&gt;", ">");
		source = source.replaceAll("&lt;", "<");
		source = source.replaceAll("&quot;", "\"");
		source = source.replaceAll("&amp;", "&");
		return source;
	}

	public static String newline2br(String source) {
		source = (source == null ? "" : source.trim());
		source = source.replaceAll("\n", "<br/>\n");
		return source;
	}

	public static String newline2string(String source, String target) {
		source = (source == null ? "" : source.trim());
		source = source.replaceAll("\n", "\n" + target);
		return source;
	}

	public static String plain2html(String source) {
		source = (source == null ? "" : source.trim());
		source = html2special(source);
		source = newline2br(source);
		return source;
	}

	public static String activeXParameterString(String source) {
		source = (source == null ? "" : source.trim());
		source = source.replaceAll("\"", "&quot;");
		source = source.replaceAll("\\\\", "\\\\\\\\");
		source = source.replaceAll("&quot;", "\\\\\"");
		return source;
	}

	public static String jsString(String source) {
		if (source == null) {
			return "";
		}
		source = source.replaceAll("\\\\", "\\\\\\\\");
		source = source.replaceAll("\"", "\\\\\"");
		source = source.replaceAll("\r", "\\\\r");
		source = source.replaceAll("\n", "\\\\n");
		return source;
	}

	public static String replace(String source, String src, String dest) {
		if (source == null || src == null || dest == null) {
			return source;
		}
		int fromIndex = 0;
		while (source.indexOf(src, fromIndex) != -1) {
			String prefixstr = source.substring(0, source.indexOf(src, fromIndex));
			String surfixstr = source.substring(source.indexOf(src, fromIndex) + src.length(), source.length());
			source = prefixstr + dest + surfixstr;
			fromIndex = prefixstr.length() + dest.length();
		}
		return source;
	}

	public static String replaceChars(String source, String replacement) {
		if (source == null || source == null) {
			return source;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0, len = source.length(); i < len; i++) {
			sb.append(replacement);
		}
		return sb.toString();
	}

	public static String removeTag(String source) {
		if (source == null) {
			return source;
		}
		return source.replaceAll("\\<.*?\\>", "");
	}
	
	public static boolean isNumeric(String value) {
		if (value == null) {
			return false;
		}
		return PATTERN_DIGIT.matcher(value).matches();
	}
	
	public static boolean isMacId(String macId) {
		return PATTERN_MACID_EXACT.matcher(macId).matches();
	}

	public static boolean isValidEmail(String email) {
		return PATTERN_EMAIL.matcher(email).matches();
	}

	public static boolean isValidZip(String zip) {
		return PATTERN_ZIP.matcher(zip).matches();
	}

	public static boolean isValidPhone(String phone) {
		return PATTERN_PHONE.matcher(phone).matches();
	}
	
	public static boolean isValidPhoneNo(String phone) {
		return PATTERN_PHONE_NO.matcher(phone).matches();
	}

	public static boolean isValidMobile(String mobile) {
		return PATTERN_KOREAN_MOBILE.matcher(mobile).matches();
	}

	public static boolean isValidAlphabet(String value) {
		return PATTERN_ALPHABET.matcher(value).matches();
	}

	public static boolean isValidLowerAlphabet(String value) {
		return PATTERN_LOWER_ALPHABET.matcher(value).matches();
	}

	public static boolean isValidUpperAlphabet(String value) {
		return PATTERN_UPPER_ALPHABET.matcher(value).matches();
	}

	public static boolean isValidAlphaDigit(String value) {
		return PATTERN_ALPHABET_DIGIT.matcher(value).matches();
	}

	public static boolean isValidLowerAlphaDigit(String value) {
		return PATTERN_LOWER_ALPHABET_DIGIT.matcher(value).matches();
	}

	public static boolean isValidUpperAlphaDigit(String value) {
		return PATTERN_UPPER_ALPHABET_DIGIT.matcher(value).matches();
	}

	public static boolean isValidAlphaDigitWithWhiteSpace(String value) {
		return PATTERN_ALPHABET_DIGIT_WITH_WHITESPACE.matcher(value).matches();
	}

	public static String ltrimDigits(String source) {
		try {
			StringBuffer output = new StringBuffer();
			Matcher matcher = PATTERN_TRIM_FIRST_DIGITS.matcher(source);
			while (matcher.find()) {
				matcher.appendReplacement(output, "");
			}
			matcher.appendTail(output);
			return output.toString();
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
			return source;
		}
	}
	
	public static String join(String[] sources, String value) {
		if (sources == null || sources.length == 0) {
			return "";
		}
		
		if (sources.length == 0) {
			return sources[0];
		}
		
		StringBuilder sb = new StringBuilder();
		
		if (value == null) {
			value = "";
		}
		
		int index = 0;
		for (String source : sources) {
			sb.append(source == null ? "" : source);
			index++;
			if (index < sources.length) {
				sb.append(value);
			}
		}
		
		return sb.toString();
	}
}
