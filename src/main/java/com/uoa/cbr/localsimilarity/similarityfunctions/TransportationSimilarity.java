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
import com.uoa.cbr.localsimilarity.similaritymatrix.TransportationSimilarityMatrixBean;

@Service
public class TransportationSimilarity extends LocalSimilarityFunction{
	
	private static final String  TRANSPORTATION_VALUE_TYPE = "Transportation";

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(TransportationSimilarity.class.getName());

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

		}
	
	@Override
	public Double getSimilarity(String targetValue, String sourceValue){
		int sourceIndex = transportationValues.indexOf(sourceValue);
		int targetIndex = transportationValues.indexOf(targetValue);
		SimilarityMatrix similarity =matrix[sourceIndex][targetIndex];
		if(similarity == null){
			return 0.0;
		}
		else{
			return similarity.getSimilarity();
		}
	}

}
