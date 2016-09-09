package com.uoa.cbr.localsimilarity.similarityfunctions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uoa.cbr.localsimilarity.model.SimilarityMatrix;
import com.uoa.cbr.localsimilarity.service.SimilarityService;

@Service
public class DurationSimilarity extends LocalSimilarityFunction{
	
	
	@Override
	public Double getSimilarity(String targetValue, String sourceValue){

		Double targetDoubleValue = Double.valueOf(targetValue);
		Double sourceDoubleValue = Double.valueOf(sourceValue);
		
		Double durationDifference = sourceDoubleValue - targetDoubleValue;
		if(durationDifference <0){
			durationDifference = -durationDifference;
		}
		
		Double similarity = 1 - ((durationDifference) * 10) / targetDoubleValue;
		if(similarity > 0){
			return similarity;
		}else{
			return 0.0;
		}
		
	}

}
