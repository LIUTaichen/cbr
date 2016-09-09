package com.uoa.cbr.localsimilarity.similaritymatrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.uoa.cbr.cases.service.TravelCaseService;
import com.uoa.cbr.localsimilarity.model.SimilarityMatrix;
import com.uoa.cbr.localsimilarity.service.SimilarityService;

@Controller
@Scope("request")
public class HolidayTypeSimilarityMatrixBean {

	private static final String HOLIDAY_TYPE_VALUE_TYPE = "HolidayType";
	private static final String HOLIDAY_TYPE_COLUMN_NAME = "holidayType";

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(HolidayTypeSimilarityMatrixBean.class.getName());

	private List<String> holidayTypeValues = new ArrayList<String>();

	private SimilarityMatrix[][] matrix;

	@Autowired
	SimilarityService similarityService;

	@Autowired
	private TravelCaseService travelCaseService;

	@PostConstruct
	public void init() {
		logger.info("initiating");

		holidayTypeValues = travelCaseService.getValueList(HOLIDAY_TYPE_COLUMN_NAME);
		
		matrix = new SimilarityMatrix[holidayTypeValues.size()][holidayTypeValues.size()];

		List<SimilarityMatrix> similarities = similarityService.listForValueType(HOLIDAY_TYPE_VALUE_TYPE);
		for (SimilarityMatrix similarity : similarities) {
			int sourceIndex = holidayTypeValues.indexOf(similarity.getSourceValue());
			int targetIndex = holidayTypeValues.indexOf(similarity.getTargetValue());
			matrix[sourceIndex][targetIndex] = similarity;

		}

		for (int i = 0; i <holidayTypeValues.size(); i++) {
			for (int j = 0; j <holidayTypeValues.size(); j++) {
				if (matrix[i][j] == null) {
					matrix[i][j] = new SimilarityMatrix();
					matrix[i][j].setValueType(HOLIDAY_TYPE_VALUE_TYPE);
					matrix[i][j].setSourceValue(holidayTypeValues.get(i));
					matrix[i][j].setTargetValue(holidayTypeValues.get(j));
				}
			}
		}

	}

	public SimilarityMatrix[][] getMatrix() {
		return matrix;
	}

	public void setSimilarityMatrix(SimilarityMatrix[][] matrix) {
		this.matrix = matrix;
	}

	public List<String> getHolidayTypeValues() {
		return holidayTypeValues;
	}

	public void setHolidayTypeValues(List<String> holidayTypeValues) {
		this.holidayTypeValues = holidayTypeValues;
	}

	public String onSave() {
		for (int i = 0; i <holidayTypeValues.size(); i++) {
			for (int j = 0; j <holidayTypeValues.size(); j++) {
				SimilarityMatrix item = matrix[i][j];
				if (item.getSimilarity() == null) {
					item.setSimilarity(0.0);
				}
				similarityService.saveSimilarity(item.getValueType(), item.getSourceValue(), item.getTargetValue(),
						item.getSimilarity());
			}
		}
		return "Recommendation";
	}

}
