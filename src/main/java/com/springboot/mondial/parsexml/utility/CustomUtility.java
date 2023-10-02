package com.springboot.mondial.parsexml.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CustomUtility {

	public static String formatDate(String originalDate) {
		LocalDate inputDate = LocalDate.parse(originalDate, DateTimeFormatter.ofPattern("dd MM yyyy"));
		return DateTimeFormatter.ofPattern("dd/MM/yyyy").format(inputDate);
	}

	public static boolean isObjectNotNull(Object value) {
		return null != value;
	}

	public static boolean isObjectNull(Object value) {
		return null == value;
	}

	@SuppressWarnings("rawtypes")
	public static boolean isListNotNull(List list) {
		return ((null != list) && (list.size() > 0));

	}

	public static boolean isStringNotNull(String value) {
		return (value != null && value.trim().length() != 0);
	}
}
