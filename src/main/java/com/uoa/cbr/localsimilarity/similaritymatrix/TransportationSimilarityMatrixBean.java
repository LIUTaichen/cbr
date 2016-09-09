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
public class TransportationSimilarityMatrixBean {

	private static final String  TRANSPORTATION_VALUE_TYPE = "Transportation";

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(TransportationSimilarityMatrixBean.class.getName());

	private List<String> transportationValues = new ArrayList<String>();
	private static final String TRANSPORTATION_COLUMN_NAME = "transportation";

	private SimilarityMatrix[][] matrix;

	@Autowired
	SimilarityService similarityService;
	
	@Autowired
	private TravelCaseService travelCaseService;
	
	

	@PostConstruct
	public void init() {
		logger.info("initiating");
		
		transportationValues = travelCaseService.getValueList(TRANSPORTATION_COLUMN_NAME);
		
		matrix =  new SimilarityMatrix[transportationValues.size()][transportationValues.size()];

		List<SimilarityMatrix> similarities = similarityService.listForValueType(TRANSPORTATION_VALUE_TYPE);
		for (SimilarityMatrix similarity : similarities) {
			int sourceIndex = transportationValues.indexOf(similarity.getSourceValue());
			int targetIndex = transportationValues.indexOf(similarity.getTargetValue());
			matrix[sourceIndex][targetIndex] = similarity;

		}

		for (int i = 0; i < transportationValues.size(); i++) {
			for (int j = 0; j < transportationValues.size(); j++) {
				if (matrix[i][j] == null) {
					matrix[i][j] = new SimilarityMatrix();
					matrix[i][j].setValueType(TRANSPORTATION_VALUE_TYPE);
					matrix[i][j].setSourceValue(transportationValues.get(i));
					matrix[i][j].setTargetValue(transportationValues.get(j));
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

	public List<String> getTransportationValues() {
		return transportationValues;
	}

	public void setTransportationValues(List<String> holidayTypeValues) {
		this.transportationValues = holidayTypeValues;
	}

	public String onSave() {
		for (int i = 0; i < transportationValues.size(); i++) {
			for (int j = 0; j < transportationValues.size(); j++) {
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
