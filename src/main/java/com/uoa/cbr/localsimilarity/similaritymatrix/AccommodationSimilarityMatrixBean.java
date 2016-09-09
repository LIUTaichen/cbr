package com.uoa.cbr.localsimilarity.similaritymatrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.uoa.cbr.cases.service.TravelCaseService;
import com.uoa.cbr.localsimilarity.model.SimilarityMatrix;
import com.uoa.cbr.localsimilarity.service.SimilarityService;

@Controller
@Scope("request")
public class AccommodationSimilarityMatrixBean {

	private static final String ACCOMMODATION_VALUE_TYPE = "Accommodation";
	private static final String ACCOMMODATION_COLUMN_NAME = "accommodation";

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(AccommodationSimilarityMatrixBean.class.getName());

	private List<String> accommodationValues = new ArrayList<String>();

	private SimilarityMatrix[][] matrix;

	@Autowired
	SimilarityService similarityService;
	

	@Autowired
	private TravelCaseService travelCaseService;

	@PostConstruct
	public void init() {
		logger.info("initiating");
		accommodationValues = travelCaseService.getValueList(ACCOMMODATION_COLUMN_NAME);
		
		matrix =  new SimilarityMatrix[accommodationValues.size()][accommodationValues.size()];

		List<SimilarityMatrix> similarities = similarityService.listForValueType(ACCOMMODATION_VALUE_TYPE);
		for (SimilarityMatrix similarity : similarities) {
			int sourceIndex = accommodationValues.indexOf(similarity.getSourceValue());
			int targetIndex = accommodationValues.indexOf(similarity.getTargetValue());
			matrix[sourceIndex][targetIndex] = similarity;

		}

		for (int i = 0; i <accommodationValues.size(); i++) {
			for (int j = 0; j <accommodationValues.size(); j++) {
				if (matrix[i][j] == null) {
					matrix[i][j] = new SimilarityMatrix();
					matrix[i][j].setValueType(ACCOMMODATION_VALUE_TYPE);
					matrix[i][j].setSourceValue(accommodationValues.get(i));
					matrix[i][j].setTargetValue(accommodationValues.get(j));
				}
			}
		}

		/*
		 * for(int i = 0; i < accommodationValues.length; i++){
		 * 
		 * String sourceValue = accommodationValues[i];
		 * 
		 * 
		 * for(int j = 0; j < accommodationValues.length; j++){
		 * 
		 * String targetValue = accommodationValues[j];
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

	public List<String> getAccommodationValues() {
		return accommodationValues;
	}

	public void setAccommodationValues(List<String> accommodationValues) {
		this.accommodationValues = accommodationValues;
	}

	public String onSave() {
		for (int i = 0; i <accommodationValues.size(); i++) {
			for (int j = 0; j <accommodationValues.size(); j++) {
				SimilarityMatrix item = matrix[i][j];
				if(item.getSimilarity() == null){
					item.setSimilarity(0.0);
				}
				similarityService.saveSimilarity(item.getValueType(), item.getSourceValue(), item.getTargetValue(),
						item.getSimilarity());
			}
		}
		return "Recommendation";
	}

}
