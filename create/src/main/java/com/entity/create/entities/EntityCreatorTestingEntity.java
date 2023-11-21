package com.entity.create.entities;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "ENTITY_CREATOR_TESTING")


public class EntityCreatorTestingEntity { 

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ENTITY_CREATOR_TESTING_ID")
	private Long entityCreatorTestingId;

	@Column(name = "COLUMN1")
	private String column1;

	@Column(name = "COLUMN2")
	private Long column2;

	@Column(name = "COLUMN3")
	private char column3;

	@Column(name = "COLUMN4")
	private String column4;

	@Column(name = "COLUMN5")
	private Long column5;

	@Column(name = "COLUMN6")
	private Long column6;

	@Column(name = "COLUMN7")
	private Boolean column7;

	@Column(name = "COLUMN8")
	private Date column8;

	@Column(name = "COLUMN9")
	private Date column9;

	}