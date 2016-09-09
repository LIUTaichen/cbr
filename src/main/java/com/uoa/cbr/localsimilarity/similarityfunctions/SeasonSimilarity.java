package com.uoa.cbr.localsimilarity.similarityfunctions;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uoa.cbr.cases.service.TravelCaseService;
import com.uoa.cbr.localsimilarity.model.SimilarityMatrix;
import com.uoa.cbr.localsimilarity.service.SimilarityService;

@Service
public class SeasonSimilarity extends LocalSimilarityFunction{
	
private static final String SEASON_VALUE_TYPE = "Season";
	
	private static final String SEASON_COLUMN_NAME = "season";

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(SeasonSimilarity.class.getName());

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

		}


	
	@Override
	public Double getSimilarity(String targetValue, String sourceValue){
		int sourceIndex = seasonValues.indexOf(sourceValue);
		int targetIndex = seasonValues.indexOf(targetValue);
		SimilarityMatrix similarity =matrix[sourceIndex][targetIndex];
		if(similarity == null){
			return 0.0;
		}
		else{
			return similarity.getSimilarity();
		}
	}

}
