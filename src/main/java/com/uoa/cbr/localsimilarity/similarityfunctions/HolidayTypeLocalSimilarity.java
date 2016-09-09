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
import com.uoa.cbr.localsimilarity.similaritymatrix.HolidayTypeSimilarityMatrixBean;

@Service
public class HolidayTypeLocalSimilarity extends LocalSimilarityFunction{
	
	private static final Logger logger = Logger.getLogger(HolidayTypeLocalSimilarity.class.getName());
	
	@Autowired
	private SimilarityService matrixService;

	@Autowired
	private TravelCaseService travelCaseService;
	
	private static final String HOLIDAY_TYPE_VALUE_TYPE = "HolidayType";
	private static final String HOLIDAY_TYPE_COLUMN_NAME = "holidayType";
	private SimilarityMatrix[][] matrix;
	private List<String> holidayTypeValues = new ArrayList<String>();
	@PostConstruct
	public void init() {
		logger.info("initiating");

		holidayTypeValues = travelCaseService.getValueList(HOLIDAY_TYPE_COLUMN_NAME);
		
		matrix = new SimilarityMatrix[holidayTypeValues.size()][holidayTypeValues.size()];

		List<SimilarityMatrix> similarities = matrixService.listForValueType(HOLIDAY_TYPE_VALUE_TYPE);
		for (SimilarityMatrix similarity : similarities) {
			int sourceIndex = holidayTypeValues.indexOf(similarity.getSourceValue());
			int targetIndex = holidayTypeValues.indexOf(similarity.getTargetValue());
			matrix[sourceIndex][targetIndex] = similarity;

		}


	}
	@Override
	public Double getSimilarity(String targetValue, String sourceValue){
		int sourceIndex = holidayTypeValues.indexOf(sourceValue);
		int targetIndex = holidayTypeValues.indexOf(targetValue);
		SimilarityMatrix similarity =matrix[sourceIndex][targetIndex];
		if(similarity == null){
			return 0.0;
		}
		else{
			return similarity.getSimilarity();
		}
	}
	

}
