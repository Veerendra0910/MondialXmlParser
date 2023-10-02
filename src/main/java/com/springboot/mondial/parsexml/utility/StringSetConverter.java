package com.springboot.mondial.parsexml.utility;

import java.util.Collections;
import java.util.Set;

import jakarta.persistence.AttributeConverter;

public class StringSetConverter implements AttributeConverter<Set<String>, String> {

	private static final String SPLIT_CHAR = ";";
	
	@Override
	public String convertToDatabaseColumn(Set<String> stringList) {
		return stringList != null ? String.join(SPLIT_CHAR, stringList) : "";
	}

	@Override
	public Set<String> convertToEntityAttribute(String dbData) {
		return dbData!=null? Set.of(dbData.split(SPLIT_CHAR)) : Collections.emptySet();
	}

}
