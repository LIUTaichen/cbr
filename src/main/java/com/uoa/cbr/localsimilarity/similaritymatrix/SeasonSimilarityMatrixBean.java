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
public class SeasonSimilarityMatrixBean {

	private static final String SEASON_VALUE_TYPE = "Season";
	
	private static final String SEASON_COLUMN_NAME = "season";

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(SeasonSimilarityMatrixBean.class.getName());

	private List<String> seasonValues = new ArrayList<String>();

	private SimilarityMatrix[][] matrix;

	@Autowired
	SimilarityService similarityService;
	

	@Autowired
	private TravelCaseService travelCaseService;

	@PostConstruct
	public void init() {
		logger.info("initiating");
		seasonValues = travelCaseService.getValueList(SEASON_COLUMN_NAME);
		
		matrix =  new SimilarityMatrix[seasonValues.size()][seasonValues.size()];

		List<SimilarityMatrix> similarities = similarityService.listForValueType(SEASON_VALUE_TYPE);
		for (SimilarityMatrix similarity : similarities) {
			int sourceIndex = seasonValues.indexOf(similarity.getSourceValue());
			int targetIndex = seasonValues.indexOf(similarity.getTargetValue());
			matrix[sourceIndex][targetIndex] = similarity;

		}

		for (int i = 0; i <seasonValues.size(); i++) {
			for (int j = 0; j <seasonValues.size(); j++) {
				if (matrix[i][j] == null) {
					matrix[i][j] = new SimilarityMatrix();
					matrix[i][j].setValueType(SEASON_VALUE_TYPE);
					matrix[i][j].setSourceValue(seasonValues.get(i));
					matrix[i][j].setTargetValue(seasonValues.get(j));
				}
			}
		}

		/*
		 * for(int i = 0; i < seasonValues.length; i++){
		 * 
		 * String sourceValue = seasonValues[i];
		 * 
		 * 
		 * for(int j = 0; j < seasonValues.length; j++){
		 * 
		 * String targetValue = seasonValues[j];
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

	public List<String> getSeasonValues() {
		return seasonValues;
	}

	public void setSeasonValues(List<String> seasonValues) {
		this.seasonValues = seasonValues;
	}

	public String onSave() {
		for (int i = 0; i <seasonValues.size(); i++) {
			for (int j = 0; j <seasonValues.size(); j++) {
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
