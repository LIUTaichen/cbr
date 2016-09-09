package com.uoa.cbr.localsimilarity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.uoa.cbr.localsimilarity.model.SimilarityMatrix;
import com.uoa.cbr.localsimilarity.service.SimilarityService;

@Controller
@Scope("request")
public class SimilarityMatrixBean {

	private static final String VALUE_TYPE = "HolidayType";

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(SimilarityMatrixBean.class.getName());

	private String[] valuesArray = { "Bathing", "Education", "City", "Wandering", "Language", "Active", "Recreation",
			"Skiiing" };
	private List<String> holidayTypeValues = new ArrayList<String>();

	private SimilarityMatrix[][] matrix = new SimilarityMatrix[valuesArray.length][valuesArray.length];

	@Autowired
	SimilarityService similarityService;

	@PostConstruct
	public void init() {
		logger.info("initiating");
		for (int i = 0; i < valuesArray.length; i++) {
			holidayTypeValues.add(valuesArray[i]);
		}

		List<SimilarityMatrix> similarities = similarityService.listForValueType(VALUE_TYPE);
		for (SimilarityMatrix similarity : similarities) {
			int sourceIndex = holidayTypeValues.indexOf(similarity.getSourceValue());
			int targetIndex = holidayTypeValues.indexOf(similarity.getTargetValue());
			matrix[sourceIndex][targetIndex] = similarity;

		}

		for (int i = 0; i < valuesArray.length; i++) {
			for (int j = 0; j < valuesArray.length; j++) {
				if (matrix[i][j] == null) {
					matrix[i][j] = new SimilarityMatrix();
					matrix[i][j].setValueType(VALUE_TYPE);
					matrix[i][j].setSourceValue(holidayTypeValues.get(i));
					matrix[i][j].setTargetValue(holidayTypeValues.get(j));
				}
			}
		}

		/*
		 * for(int i = 0; i < holidayTypeValues.length; i++){
		 * 
		 * String sourceValue = holidayTypeValues[i];
		 * 
		 * 
		 * for(int j = 0; j < holidayTypeValues.length; j++){
		 * 
		 * String targetValue = holidayTypeValues[j];
		 * 
		 * 
		 * } }
		 */

	}

	public SimilarityMatrix[][] getMatrix() {
		return matrix;
	}

	public void setSimilarityMatrix(SimilarityMatrix[][] matrix) {
		this.matrix = matrix;
	}

	public List<String> getHolidayTypeValues() {
		return holidayTypeValues;
	}

	public void setHolidayTypeValues(List<String> holidayTypeValues) {
		this.holidayTypeValues = holidayTypeValues;
	}

	public void onSave() {
		for (int i = 0; i < valuesArray.length; i++) {
			for (int j = 0; j < valuesArray.length; j++) {
				SimilarityMatrix item = matrix[i][j];
				similarityService.saveSimilarity(item.getValueType(), item.getSourceValue(), item.getTargetValue(),
						item.getSimilarity());
			}
		}
	}

}
