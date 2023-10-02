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
@Table(name = DBConstants.DB_LAKE_TABLE)
public class Lake implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = DBConstants.DB_LAKE_COLUMN_ID)
	private String lakeId;

	@Column(name = DBConstants.DB_LAKE_COLUMN_NAME)
	private String lakeName;

	@Column(name = DBConstants.DB_LAKE_COLUMN_AREA)
	private String lakeArea;

	@Convert(converter = StringSetConverter.class)
	@Column(name = DBConstants.DB_LAKE_COLUMN_COUNTRIES)
	private Set<String> lakeCountry;

	@Convert(converter = StringSetConverter.class)
	@Column(name = DBConstants.DB_LAKE_COLUMN_PROVINCES)
	private Set<String> lakeProvince;
}
