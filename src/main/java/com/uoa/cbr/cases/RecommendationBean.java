package com.uoa.cbr.cases;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.uoa.cbr.cases.service.TravelCaseService;
import com.uoa.cbr.localsimilarity.similaritymatrix.HolidayTypeSimilarityMatrixBean;

@Controller
@Scope("request")
public class RecommendationBean {
	
	private static final String ACCOMMODATION_COLUMN_NAME = "accommodation";
	
	private static final String REGION_COLUMN_NAME = "region";

	private static final String SEASON_COLUMN_NAME = "season";

	private static final String TRANSPORTATION_COLUMN_NAME = "transportation";

	private static final String HOLIDAY_TYPE_COLUMN_NAME = "holidayType";

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(RecommendationBean.class.getName());

	private String holidayType;
	
	private Integer price;
	
	private Integer numberOfPersons = 0;
	
	private String region;
	
	private String transportation;
	
	private Integer duration;
	
	private String season;
	
	private String accommodation;
	
	private String hotel;
	
	private List<String> holidayTypeValueList;
	
	private List<String> regionValueList;
	
	private List<String> transportationValueList;
	
	private List<String> seasonValueList;
	
	private List<String> accommodationValueList;
	
	private List<TravelCase> nearestNeighbours;
	
	
	@Autowired
	private TravelCaseService travelCaseService;
	
	@PostConstruct
	public void init() {
		holidayTypeValueList = travelCaseService.getValueList(HOLIDAY_TYPE_COLUMN_NAME);
		setRegionValueList(travelCaseService.getValueList(REGION_COLUMN_NAME));
		transportationValueList = travelCaseService.getValueList(TRANSPORTATION_COLUMN_NAME);
		seasonValueList = new ArrayList<String>();
		seasonValueList.add("January");
		seasonValueList.add("February");
		seasonValueList.add("March");
		seasonValueList.add("April");
		seasonValueList.add("May");
		seasonValueList.add("June");
		seasonValueList.add("July");
		seasonValueList.add("August");
		seasonValueList.add("September");
		seasonValueList.add("October");
		seasonValueList.add("November");
		seasonValueList.add("December");
		
		accommodationValueList = travelCaseService.getValueList(ACCOMMODATION_COLUMN_NAME);
	}
	
	public String onSubmit(){
		logger.info("making recommendation");
		TravelCase targetCase = new TravelCase();
		targetCase.setAccommodation(accommodation);
		targetCase.setDuration(duration);
		targetCase.setHolidayType(holidayType);
		targetCase.setNumberOfPersons(numberOfPersons);
		targetCase.setPrice(price);
		targetCase.setRegion(region);
		targetCase.setSeason(season);
		targetCase.setTransportation(transportation);
		nearestNeighbours = travelCaseService.getRecommendation(targetCase);
		
		return "Recommendation";
	}
	
	public String getHolidayType() {
		return holidayType;
	}

	public void setHolidayType(String holidayType) {
		this.holidayType = holidayType;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
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




	public List<String> getRegionValueList() {
		return regionValueList;
	}

	public void setRegionValueList(List<String> regionValueList) {
		this.regionValueList = regionValueList;
	}

	public List<TravelCase> getNearestNeighbours() {
		return nearestNeighbours;
	}

	public void setNearestNeighbours(List<TravelCase> nearestNeighbours) {
		this.nearestNeighbours = nearestNeighbours;
	}
	

}
