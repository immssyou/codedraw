package kr.co.lifePan.web.utility;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class FormUtil {

	private Map formMap;
	private Map formBeanMap;
	private Locale locale;

	public FormUtil() {
		this.formMap = new HashMap();
		this.formBeanMap = new HashMap();
		this.locale = Locale.getDefault();
	}

	public FormUtil(HttpServletRequest request) {
		this.formMap = new HashMap();
		this.formBeanMap = new HashMap();
		this.locale = Locale.getDefault();
		this.refreshFormData(request);
	}

	/**
	 * 폼 값을 다시 읽는다.
	 * @param request {@link HttpServletRequest}
	 */
	@SuppressWarnings("unchecked")
	public final void refreshFormData(HttpServletRequest request) {
		clear();
		locale = request.getLocale();
		Enumeration formEr = request.getParameterNames();
		while (formEr.hasMoreElements()) {
			String formName = (String) formEr.nextElement();
			formMap.put(formName, request.getParameterValues(formName));
		}
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	@SuppressWarnings("unchecked")
	public String[] getNames() {
		return ArrayUtil.toStringArray(formMap.keySet());
	}

	public Iterator getNameIterator() {
		return formMap.keySet().iterator();
	}

	public String getValue(String name) {
		String[] retValue = (String[]) formMap.get(name);
		if (retValue == null) {
			return null;
		} else {
			if ("".equals(retValue[0])) {
				return null;
			}
			return retValue[0];
		}
	}

	public String getValue(String name, int index) {
		String[] retValue = (String[]) formMap.get(name);
		if (retValue == null) {
			return null;
		} else {
			String value = (index < retValue.length) ? retValue[index] : null;
			if ("".equals(value)) {
				return null;
			}
			return value;
		}
	}

	public String[] getValues(String name) {
		String[] retValue = (String[]) formMap.get(name);
		return retValue;
	}

	public String getParameter(String name) {
		String[] retValue = (String[]) formMap.get(name);
		if (retValue == null) {
			return null;
		} else {
			return retValue[0];
		}
	}

	public String getParameter(String name, int index) {
		String[] retValue = (String[]) formMap.get(name);
		if (retValue == null) {
			return null;
		} else {
			return (index < retValue.length) ? retValue[index] : null;
		}
	}

	public String[] getParameterValues(String name) {
		String[] retValue = (String[]) formMap.get(name);
		if (retValue == null) {
			return new String[0];
		} else {
			return retValue;
		}
	}

	public String getString(String name) {
		String check_value = getParameter(name);
		if (check_value == null) {
			return "";
		}
		return check_value.trim();
	}

	public String getString(String name, int index) {
		String[] retValue = (String[]) formMap.get(name);
		if (retValue == null) {
			return "";
		} else {
			return (index < retValue.length) ? retValue[index] : "";
		}
	}

	public String getString(String name, String default_value) {
		String check_value = getParameter(name);
		if (check_value == null || check_value.trim().equals("")) {
			return default_value;
		}
		return check_value.trim();
	}

	public String[] getStringArray(String name) {
		return getParameterValues(name);
	}

	public boolean getBoolean(String name) {
		return Boolean.valueOf(getParameter(name)).booleanValue();
	}

	public boolean getBoolean(String name, boolean default_value) {
		String check_value = getParameter(name);
		if (check_value == null || check_value.trim().equals("")) {
			return default_value;
		}
		return Boolean.valueOf(check_value).booleanValue();
	}

	public boolean[] getBooleanArray(String name) {
		String[] check_value = getParameterValues(name);
		boolean[] ret_values = new boolean[check_value.length];
		for (int i = 0; i < check_value.length; i++) {
			ret_values[i] = Boolean.valueOf(check_value[i]).booleanValue();
		}
		return ret_values;
	}

	public short getShort(String name) {
		String check_value = getParameter(name);
		return NumberUtil.getShortValue(check_value, locale);
	}

	public short getShort(String name, short default_value) {
		String check_value = getParameter(name);
		if (check_value == null || check_value.trim().equals("")) {
			return default_value;
		}
		return NumberUtil.getShortValue(check_value, locale);
	}

	public short[] getShortArray(String name) {
		String[] check_value = getParameterValues(name);
		short[] ret_values = new short[check_value.length];
		for (int i = 0; i < check_value.length; i++) {
			ret_values[i] = NumberUtil.getShortValue(check_value[i], locale);
		}
		return ret_values;
	}

	public int getInt(String name) {
		String check_value = getParameter(name);
		return NumberUtil.getIntValue(check_value, locale);
	}

	public int getInt(String name, int default_value) {
		String check_value = getParameter(name);
		if (check_value == null || check_value.trim().equals("")) {
			return default_value;
		}
		return NumberUtil.getIntValue(check_value, locale);
	}

	public int[] getIntArray(String name) {
		String[] check_value = getParameterValues(name);
		int[] ret_values = new int[check_value.length];
		for (int i = 0; i < check_value.length; i++) {
			ret_values[i] = NumberUtil.getIntValue(check_value[i], locale);
		}
		return ret_values;
	}

	public long getLong(String name) {
		String check_value = getParameter(name);
		return NumberUtil.getLongValue(check_value, locale);
	}

	public long getLong(String name, long default_value) {
		String check_value = getParameter(name);
		if (check_value == null || check_value.trim().equals("")) {
			return default_value;
		}
		return NumberUtil.getLongValue(check_value, locale);
	}

	public long[] getLongArray(String name) {
		String[] check_value = getParameterValues(name);
		long[] ret_values = new long[check_value.length];
		for (int i = 0; i < check_value.length; i++) {
			ret_values[i] = NumberUtil.getLongValue(check_value[i], locale);
		}
		return ret_values;
	}

	public float getFloat(String name) {
		String check_value = getParameter(name);
		return NumberUtil.getFloatValue(check_value, locale);
	}

	public float getFloat(String name, float default_value) {
		String check_value = getParameter(name);
		if (check_value == null || check_value.trim().equals("")) {
			return default_value;
		}
		return NumberUtil.getFloatValue(check_value, locale);
	}

	public float[] getFloatArray(String name) {
		String[] check_value = getParameterValues(name);
		float[] ret_values = new float[check_value.length];
		for (int i = 0; i < check_value.length; i++) {
			ret_values[i] = NumberUtil.getFloatValue(check_value[i], locale);
		}
		return ret_values;
	}

	public double getDouble(String name) {
		String check_value = getParameter(name);
		return NumberUtil.getDoubleValue(check_value, locale);
	}

	public double getDouble(String name, double default_value) {
		String check_value = getParameter(name);
		if (check_value == null || check_value.trim().equals("")) {
			return default_value;
		}
		return NumberUtil.getDoubleValue(check_value, locale);
	}

	public double[] getDoubleArray(String name) {
		String[] check_value = getParameterValues(name);
		double[] ret_values = new double[check_value.length];
		for (int i = 0; i < check_value.length; i++) {
			ret_values[i] = NumberUtil.getDoubleValue(check_value[i], locale);
		}
		return ret_values;
	}

	public BigInteger getBigInteger(String name) {
		String check_value = getParameter(name);
		if (check_value == null || check_value.trim().equals("")) {
			return BigInteger.ZERO;
		}
		try {
			return new BigInteger(NumberUtil.getNumber(name, locale).toString());
		} catch (ParseException nfe) {
			return BigInteger.ZERO;
		}
	}

	public BigInteger getBigInteger(String name, int radix) {
		String check_value = getParameter(name);
		if (check_value == null || check_value.trim().equals("")) {
			return BigInteger.ZERO;
		}
		try {
			return new BigInteger(NumberUtil.getNumber(name, locale).toString(), radix);
		} catch (ParseException nfe) {
			return BigInteger.ZERO;
		}
	}

	public BigInteger getBigInteger(String name, BigInteger default_value) {
		String check_value = getParameter(name);
		if (check_value == null || check_value.trim().equals("")) {
			return default_value;
		}
		try {
			return new BigInteger(NumberUtil.getNumber(name, locale).toString());
		} catch (ParseException nfe) {
			return default_value;
		}
	}

	public BigInteger getBigInteger(String name, BigInteger default_value, int radix) {
		String check_value = getParameter(name);
		if (check_value == null || check_value.trim().equals("")) {
			return default_value;
		}
		try {
			return new BigInteger(NumberUtil.getNumber(name, locale).toString(), radix);
		} catch (ParseException nfe) {
			return default_value;
		}
	}

	public BigInteger[] getBigIntegerArray(String name) {
		String[] check_value = getParameterValues(name);
		BigInteger[] ret_values = new BigInteger[check_value.length];
		for (int i = 0; i < check_value.length; i++) {
			ret_values[i] = getBigInteger(check_value[i]);
		}
		return ret_values;
	}

	public BigDecimal getBigDecimal(String name) {
		String check_value = getParameter(name);
		if (check_value == null || check_value.trim().equals("")) {
			return new BigDecimal(BigInteger.ZERO);
		}
		try {
			return new BigDecimal(NumberUtil.getNumber(name, locale).toString());
		} catch (ParseException nfe) {
			return new BigDecimal(BigInteger.ZERO);
		}
	}

	public BigDecimal getBigDecimal(String name, BigDecimal default_value) {
		String check_value = getParameter(name);
		if (check_value == null || check_value.trim().equals("")) {
			return default_value;
		}
		try {
			return new BigDecimal(NumberUtil.getNumber(name, locale).toString());
		} catch (ParseException nfe) {
			return default_value;
		}
	}

	public BigDecimal[] getBigDecimalArray(String name) {
		String[] check_value = getParameterValues(name);
		BigDecimal[] ret_values = new BigDecimal[check_value.length];
		for (int i = 0; i < check_value.length; i++) {
			ret_values[i] = getBigDecimal(check_value[i]);
		}
		return ret_values;
	}

	public Date getDate(String name, String parse_pattern) throws ParseException, NullPointerException {
		String check_value = getParameter(name);
		if (check_value == null || check_value.trim().equals("")) {
			return Calendar.getInstance().getTime();
		}

		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(parse_pattern);
		return formatter.parse(check_value);
	}

	public Date getDate(String name, String parse_pattern, String default_value) throws ParseException, NullPointerException {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(parse_pattern);
		Date def_date = formatter.parse(default_value);

		String check_value = getParameter(name);
		if (check_value == null || check_value.trim().equals("")) {
			return def_date;
		}

		return formatter.parse(check_value);
	}

	public Date[] getDateArray(String name, String parse_pattern) throws ParseException, NullPointerException {
		String[] check_value = getParameterValues(name);
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(parse_pattern);
		Date[] ret_values = new Date[check_value.length];
		for (int i = 0; i < check_value.length; i++) {
			ret_values[i] = formatter.parse(check_value[i]);
		}
		return ret_values;
	}

	/**
	 * 저장된 폼 값을 지운다.
	 */
	public void clear() {
		formMap.clear();
		formBeanMap.clear();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Iterator iterator = formMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry entry = (Map.Entry) iterator.next();
			sb.append(entry.getKey()).append(" : ");
			Iterator valueIterator = Arrays.asList((String[]) entry.getValue()).iterator();
			while (valueIterator.hasNext()) {
				sb.append("[ ").append(valueIterator.next()).append(" ]");
				if (valueIterator.hasNext()) {
					sb.append(", ");
				}
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
