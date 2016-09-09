package com.uoa.cbr.localsimilarity.similarityfunctions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uoa.cbr.localsimilarity.model.SimilarityMatrix;
import com.uoa.cbr.localsimilarity.service.SimilarityService;

@Service
public class TransportationSimilarity extends LocalSimilarityFunction{
	
	@Autowired
	private SimilarityService matrixService;
	
	@Override
	public Double getSimilarity(String targetValue, String sourceValue){
		List<SimilarityMatrix> similarity = matrixService.find("Transportation", sourceValue, targetValue);
		if(similarity.isEmpty()){
			return 0.0;
		}
		else{
			return similarity.get(0).getSimilarity();
		}
	}

}
