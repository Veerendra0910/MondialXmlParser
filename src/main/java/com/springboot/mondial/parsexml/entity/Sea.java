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
@Table(name = DBConstants.DB_SEA_TABLE)
public class Sea implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = DBConstants.DB_SEA_COLUMN_ID)
	private String seaId;

	@Column(name = DBConstants.DB_SEA_COLUMN_NAME)
	private String seaName;

	@Column(name = DBConstants.DB_SEA_COLUMN_DEPTH)
	private String seaDepth;

	@Convert(converter = StringSetConverter.class)
	@Column(name = DBConstants.DB_SEA_COLUMN_COUNTRIES)
	private Set<String> seaCountry;

	@Convert(converter = StringSetConverter.class)
	@Column(name = DBConstants.DB_SEA_COLUMN_PROVINCES)
	private Set<String> seaProvince;
}
