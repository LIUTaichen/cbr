package com.uoa.cbr.globalsimilarity;

import java.util.Comparator;

import com.uoa.cbr.cases.TravelCase;

public class SimilarityComparator implements Comparator<TravelCase> {


	@Override
	public int compare(TravelCase arg0, TravelCase arg1) {
		return arg0.getSimilarity().compareTo(arg1.getSimilarity());
	}

}
