package com.uoa.cbr.localsimilarity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class SimilarityMatrix {
	
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;
	
	@Column(name = "ValueType")
	private String valueType;
	
	@Column(name = "SourceValue")
	private String sourceValue;
	
	@Column(name = "TargetValue")
	private String targetValue;
	
	
	@Column(name = "Similarity")
	private Double similarity;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getValueType() {
		return valueType;
	}


	public void setValueType(String valueType) {
		this.valueType = valueType;
	}


	public String getSourceValue() {
		return sourceValue;
	}


	public void setSourceValue(String sourceValue) {
		this.sourceValue = sourceValue;
	}


	public String getTargetValue() {
		return targetValue;
	}


	public void setTargetValue(String targeValue) {
		this.targetValue = targeValue;
	}


	public Double getSimilarity() {
		return similarity;
	}


	public void setSimilarity(Double similarity) {
		this.similarity = similarity;
	}

}
