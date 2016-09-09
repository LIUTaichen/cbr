package com.uoa.cbr.localsimilarity.similarityfunctions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uoa.cbr.localsimilarity.model.SimilarityMatrix;
import com.uoa.cbr.localsimilarity.service.SimilarityService;

@Service
public class PriceLocalSimilarity extends LocalSimilarityFunction{
	
	
	@Override
	public Double getSimilarity(String targetValue, String sourceValue){
		
		Double targetIntValue = Double.valueOf(targetValue);
		Double sourceIntValue = Double.valueOf(sourceValue);
		
		if((sourceIntValue / targetIntValue) > 1.1){
			return 0.0;
		}else if(sourceIntValue <= targetIntValue){
			return 1.0;
			
		}else {
			return 1 - ((sourceIntValue - targetIntValue) * 10) / targetIntValue;
		}
	}


}
