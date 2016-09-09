package com.uoa.cbr.cases.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uoa.cbr.cases.TravelCase;
import com.uoa.cbr.globalsimilarity.GlobalSimilarityFunction;
import com.uoa.cbr.globalsimilarity.SimilarityComparator;


@Service
public class TravelCaseService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private GlobalSimilarityFunction similarityFunction;
	
	 public List<TravelCase> listAll() {
	        return em.createQuery("SELECT u FROM TravelCase u", TravelCase.class).getResultList();
	    }
	 
	 public List<String> getValueList(String valueName){
		 Query query =em.createQuery("SELECT distinct u." + valueName + " FROM TravelCase u order by u." + valueName, String.class);
		 return query.getResultList();
	 }
	 
		public List<TravelCase> getRecommendation(TravelCase targetCase){
			List<TravelCase> allCases = listAll();
			for(TravelCase travelCase: allCases){
				travelCase.setSimilarity(similarityFunction.getSimilarity(targetCase, travelCase));
			}
			Collections.sort(allCases, Collections.reverseOrder(new SimilarityComparator()));
			
			List<TravelCase> resultList = new ArrayList<TravelCase>();
			for(int i = 0; i <5; i ++){
				resultList.add(allCases.get(i));
			}
			return resultList;
		}

}
