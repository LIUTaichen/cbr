package com.uoa.cbr.localsimilarity.similarityfunctions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uoa.cbr.localsimilarity.model.SimilarityMatrix;
import com.uoa.cbr.localsimilarity.service.SimilarityService;

@Service
public class NumberOfPersonsLocalSimilarity extends LocalSimilarityFunction{
	
	
	@Override
	public Double getSimilarity(String targetValue, String sourceValue){
		if (targetValue.equals(sourceValue)){
			return 1.0;
		}else{
			return 0.0;
		}
	}

}
