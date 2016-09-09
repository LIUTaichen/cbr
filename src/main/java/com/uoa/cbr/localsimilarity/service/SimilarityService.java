package com.uoa.cbr.localsimilarity.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uoa.cbr.localsimilarity.model.SimilarityMatrix;

@Service
public class SimilarityService {
	
	@PersistenceContext
	private EntityManager em;
	
	 public List<SimilarityMatrix> listAll() {
	        return em.createQuery("SELECT u FROM SimilarityMatrix u", SimilarityMatrix.class).getResultList();
	    }
	 
	 @Transactional
	 private void createNewSimilarity(String valueType, String sourceValue, String targetValue, Double similarity){
		 SimilarityMatrix similarityMatrixEntry = new SimilarityMatrix();
		 similarityMatrixEntry.setValueType(valueType);
		 similarityMatrixEntry.setSourceValue(sourceValue);
		 similarityMatrixEntry.setTargetValue(targetValue);
		 similarityMatrixEntry.setSimilarity(similarity);
		 em.persist(similarityMatrixEntry);
	 }
	 
	 @Transactional
	 public void saveSimilarity(String valueType, String sourceValue, String targetValue, Double similarity){
		 Query query = em.createQuery("Select sm from SimilarityMatrix sm where sm.valueType=:arg1 and sm.sourceValue = :arg2 and sm.targetValue=:arg3" , SimilarityMatrix.class);
		 query.setParameter("arg1", valueType);
		 query.setParameter("arg2", sourceValue);
		 query.setParameter("arg3", targetValue);
		 List<SimilarityMatrix> resultList = query.getResultList();
		 if (resultList.isEmpty()){
		 createNewSimilarity(valueType, sourceValue, targetValue, similarity);
		 }
		 else{
			 SimilarityMatrix existingEntry=  resultList.get(0);
			 existingEntry.setSimilarity(similarity);
			 em.merge(existingEntry);
		 }
	 }
	 
	 public List<SimilarityMatrix> listForValueType(String valueType){
		   Query query =em.createQuery("SELECT u FROM SimilarityMatrix u where u.valueType=:arg1", SimilarityMatrix.class);
		 query.setParameter("arg1", valueType);
		 return query.getResultList();
	 }
	 
	 public List<SimilarityMatrix> find(String valueType, String sourceValue, String targetValue){
		 Query query = em.createQuery("Select sm from SimilarityMatrix sm where sm.valueType=:arg1 and sm.sourceValue = :arg2 and sm.targetValue=:arg3" , SimilarityMatrix.class);
		 query.setParameter("arg1", valueType);
		 query.setParameter("arg2", sourceValue);
		 query.setParameter("arg3", targetValue);
		 List<SimilarityMatrix> resultList = query.getResultList();
		 return resultList;
	 }

}
