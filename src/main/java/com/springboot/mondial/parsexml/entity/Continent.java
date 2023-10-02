package com.springboot.mondial.parsexml.entity;

import java.io.Serializable;

import com.springboot.mondial.parsexml.constants.DBConstants;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = DBConstants.DB_CONTINENT_TABLE)
public class Continent implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = DBConstants.DB_CONTINENT_COLUMN_ID)
	private String continentId;
	@Column(name = DBConstants.DB_CONTINENT_COLUMN_NAME)
	private String continentName;
}
