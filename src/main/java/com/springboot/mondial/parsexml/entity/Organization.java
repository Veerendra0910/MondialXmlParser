package com.springboot.mondial.parsexml.entity;

import java.io.Serializable;
import java.util.Map;

import com.springboot.mondial.parsexml.constants.DBConstants;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = DBConstants.DB_ORGANIZATION_TABLE)
public class Organization implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = DBConstants.DB_ORGANIZATION_COLUMN_ID)
	private String organizationId;

	@Column(name = DBConstants.DB_ORGANIZATION_COLUMN_NAME)
	private String organizationName;

	@Column(name = DBConstants.DB_ORGANIZATION_COLUMN_ABBREV)
	private String organizationAbbrev;

	@Column(name = DBConstants.DB_ORGANIZATION_COLUMN_ESTABLISHED)
	private String organizationEstablished;

	@Column(name = DBConstants.DB_ORGANIZATION_COLUMN_HEADQ)
	private String organizationHeadQuarter;
	
	@ElementCollection
	@CollectionTable(name=DBConstants.DB_ORGANIZATION_COLLECTIONTABLE_MEMBERS, joinColumns = @JoinColumn(name=DBConstants.DB_ORGANIZATION_COLUMN_ID))
	@MapKeyColumn(name=DBConstants.DB_ORGANIZATION_MEMBER_COUNTRY)
	@Column(name=DBConstants.DB_ORGANIZATION_MEMBER_TYPE)
	private Map<String, String> organizationMemberMap;
	
}
