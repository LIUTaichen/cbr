package com.uoa.cbr.localsimilarity.similarityfunctions;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uoa.cbr.cases.service.TravelCaseService;
import com.uoa.cbr.localsimilarity.model.SimilarityMatrix;
import com.uoa.cbr.localsimilarity.service.SimilarityService;

@Service
public class AccommodationSimilarity extends LocalSimilarityFunction{
	
	private static final String ACCOMMODATION_VALUE_TYPE = "Accommodation";
	private static final String ACCOMMODATION_COLUMN_NAME = "accommodation";
	
	@Autowired
	private SimilarityService matrixService;
	
	private List<String> accommodationValues = new ArrayList<String>();

	private SimilarityMatrix[][] matrix;
	
	@Autowired
	private TravelCaseService travelCaseService;

	
	@PostConstruct
	public void init() {
		accommodationValues = travelCaseService.getValueList(ACCOMMODATION_COLUMN_NAME);
		
		matrix =  new SimilarityMatrix[accommodationValues.size()][accommodationValues.size()];

		List<SimilarityMatrix> similarities = matrixService.listForValueType(ACCOMMODATION_VALUE_TYPE);
		for (SimilarityMatrix similarity : similarities) {
			int sourceIndex = accommodationValues.indexOf(similarity.getSourceValue());
			int targetIndex = accommodationValues.indexOf(similarity.getTargetValue());
			matrix[sourceIndex][targetIndex] = similarity;

		}

	}
	
	
	@Override
	public Double getSimilarity(String targetValue, String sourceValue){
		int sourceIndex = accommodationValues.indexOf(sourceValue);
		int targetIndex = accommodationValues.indexOf(targetValue);
		SimilarityMatrix similarity =matrix[sourceIndex][targetIndex];
		if(similarity == null){
			return 0.0;
		}
		else{
			return similarity.getSimilarity();
		}
	}

}
