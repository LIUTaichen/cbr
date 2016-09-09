package com.uoa.cbr.cases;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class TravelCase {
	
	@Id
	@Column(name = "CaseId")
	//@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int caseId;
	
	@Column(name = "HolidayType")
	private String holidayType;
	
	@Column(name = "Price")
	private int price;
	
	@Column(name = "NumberOfPersons")
	private int numberOfPersons;
	
	
	@Column(name = "Region")
	private String region;

	
	@Column(name = "Transportation")
	private String transportation;
	
	@Column(name = "Duration")
	private int duration;
	
	@Column(name = "Season")
	private String season;
	

	@Column(name = "Accommodation")
	private String accommodation;
	

	@Column(name = "Hotel")
	private String hotel;
	

	public int getCaseId() {
		return caseId;
	}


	public void setCaseId(int caseId) {
		this.caseId = caseId;
	}


	public String getHolidayType() {
		return holidayType;
	}


	public void setHolidayType(String holidayType) {
		this.holidayType = holidayType;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public int getNumberOfPersons() {
		return numberOfPersons;
	}


	public void setNumberOfPersons(int numberOfPersons) {
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


	public int getDuration() {
		return duration;
	}


	public void setDuration(int duration) {
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


	public void setAccommodation(String accomodation) {
		this.accommodation = accomodation;
	}


	public String getHotel() {
		return hotel;
	}


	public void setHotel(String hotel) {
		this.hotel = hotel;
	}




}
