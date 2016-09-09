package com.uoa.cbr.cases;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.uoa.cbr.cases.service.TravelCaseService;
import com.uoa.cbr.localsimilarity.SimilarityMatrixBean;

@Controller
@Scope("request")
public class RecommendationBean {
	
	private static final String ACCOMMODATION_COLUMN_NAME = "Accommodation";

	private static final String SEASON_COLUMN_NAME = "Season";

	private static final String TRANSPORTATION_COLUMN_NAME = "Transportation";

	private static final String HOLIDAY_TYPE_COLUMN_NAME = "HolidayType";

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(RecommendationBean.class.getName());

	private String holidayType;
	
	private String price;
	
	private Integer numberOfPersons;
	
	private String region;
	
	private String transportation;
	
	private Integer duration;
	
	private String season;
	
	private String accommodation;
	
	private String hotel;
	
	private List<String> holidayTypeValueList;
	
	private List<String> transportationValueList;
	
	private List<String> seasonValueList;
	
	private List<String> accommodationValueList;
	
	
	@Autowired
	private TravelCaseService travelCaseService;
	
	@PostConstruct
	public void init() {
		holidayTypeValueList = travelCaseService.getValueList(HOLIDAY_TYPE_COLUMN_NAME);
		transportationValueList = travelCaseService.getValueList(TRANSPORTATION_COLUMN_NAME);
		seasonValueList = travelCaseService.getValueList(SEASON_COLUMN_NAME);
		accommodationValueList = travelCaseService.getValueList(ACCOMMODATION_COLUMN_NAME);
	}
	
	public String getHolidayType() {
		return holidayType;
	}

	public void setHolidayType(String holidayType) {
		this.holidayType = holidayType;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Integer getNumberOfPersons() {
		return numberOfPersons;
	}

	public void setNumberOfPersons(Integer numberOfPersons) {
		this.numberOfPersons = numberOfPersons;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getTransportation() {
		return transportation;
	}

	public void setTransportation(String transportation) {
		this.transportation = transportation;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getAccommodation() {
		return accommodation;
	}

	public void setAccommodation(String accommodation) {
		this.accommodation = accommodation;
	}

	public String getHotel() {
		return hotel;
	}

	public void setHotel(String hotel) {
		this.hotel = hotel;
	}

	public List<String> getHolidayTypeValueList() {
		return holidayTypeValueList;
	}

	public void setHolidayTypeValueList(List<String> holidayTypeValueList) {
		this.holidayTypeValueList = holidayTypeValueList;
	}

	public List<String> getTransportationValueList() {
		return transportationValueList;
	}

	public void setTransportationValueList(List<String> transportationValueList) {
		this.transportationValueList = transportationValueList;
	}

	public List<String> getSeasonValueList() {
		return seasonValueList;
	}

	public void setSeasonValueList(List<String> seasonValueList) {
		this.seasonValueList = seasonValueList;
	}

	public List<String> getAccommodationValueList() {
		return accommodationValueList;
	}

	public void setAccommodationValueList(List<String> accommodationValueList) {
		this.accommodationValueList = accommodationValueList;
	}



	

}
