package com.springboot.mondial.parsexml.entity;

import java.io.Serializable;
import java.util.Set;

import com.springboot.mondial.parsexml.constants.DBConstants;
import com.springboot.mondial.parsexml.utility.StringSetConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = DBConstants.DB_DESERT_TABLE)
public class Desert implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = DBConstants.DB_DESERT_COLUMN_ID)
	private String desertId;

	@Column(name = DBConstants.DB_DESERT_COLUMN_NAME)
	private String desertName;

	@Column(name = DBConstants.DB_DESERT_COLUMN_AREA)
	private String desertArea;

	@Convert(converter = StringSetConverter.class)
	@Column(name = DBConstants.DB_DESERT_COLUMN_COUNTRIES)
	private Set<String> desertCountry;

	@Convert(converter = StringSetConverter.class)
	@Column(name = DBConstants.DB_DESERT_COLUMN_PROVINCES)
	private Set<String> desertProvince;
}
