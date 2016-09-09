package com.uoa.cbr.cases.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.uoa.cbr.cases.TravelCase;


@Service
public class TravelCaseService {
	
	@PersistenceContext
	private EntityManager em;
	
	 public List<TravelCase> listAll() {
	        return em.createQuery("SELECT u FROM TravelCase u", TravelCase.class).getResultList();
	    }
	 
	 public List<String> getValueList(String valueName){
		 Query query =em.createQuery("SELECT distinct u." + valueName + " FROM TravelCase u", String.class);
		 return query.getResultList();
	 }

}
