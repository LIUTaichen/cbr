package com.uoa.cbr.globalsimilarity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uoa.cbr.cases.TravelCase;
import com.uoa.cbr.localsimilarity.similarityfunctions.AccommodationSimilarity;
import com.uoa.cbr.localsimilarity.similarityfunctions.DurationSimilarity;
import com.uoa.cbr.localsimilarity.similarityfunctions.HolidayTypeLocalSimilarity;
import com.uoa.cbr.localsimilarity.similarityfunctions.HotelSimilarity;
import com.uoa.cbr.localsimilarity.similarityfunctions.NumberOfPersonsLocalSimilarity;
import com.uoa.cbr.localsimilarity.similarityfunctions.PriceLocalSimilarity;
import com.uoa.cbr.localsimilarity.similarityfunctions.RegionSimilarity;
import com.uoa.cbr.localsimilarity.similarityfunctions.SeasonSimilarity;
import com.uoa.cbr.localsimilarity.similarityfunctions.TransportationSimilarity;

@Service
public class GlobalSimilarityFunction {

	private int hotelWeight = 1;
	private int transportWeight = 3;
	private int seasonWeight = 3;
	private int durationWeight = 5;
	private int holidayTypeWeight = 5;
	private int numberOfPersonsWeight = 8;
	private int priceWeight = 8;
	private int regionWeight = 8;
	private int accommodationWeight = 8;
	@Autowired
	private AccommodationSimilarity accommodationSimilarity;
	@Autowired
	private DurationSimilarity durationSimilarity;
	@Autowired
	private HolidayTypeLocalSimilarity holidayTypeSimilarity;
	@Autowired
	private HotelSimilarity hotelSimilarity;
	@Autowired
	private NumberOfPersonsLocalSimilarity numberOfPersonsSimilarity;
	@Autowired
	private PriceLocalSimilarity priceSimilarity;
	@Autowired
	private RegionSimilarity regionSimilarity;
	@Autowired
	private SeasonSimilarity seasonSimilarity;
	@Autowired
	private TransportationSimilarity transportSimilarity;

	public Double getSimilarity(TravelCase targetCase, TravelCase sourceCase) {
		return (accommodationSimilarity.getSimilarity(targetCase.getAccommodation(), sourceCase.getAccommodation())
				* accommodationWeight
				+ durationSimilarity.getSimilarity(targetCase.getDuration() + "", sourceCase.getDuration() + "")
						* durationWeight
				+ holidayTypeSimilarity.getSimilarity(targetCase.getHolidayType(), sourceCase.getHolidayType())
						* holidayTypeWeight
				//+ hotelSimilarity.getSimilarity(targetCase.getHotel(), sourceCase.getHotel()) * hotelWeight
				+ numberOfPersonsSimilarity.getSimilarity(targetCase.getNumberOfPersons() + "",
						sourceCase.getNumberOfPersons() + "") * numberOfPersonsWeight
				+ priceSimilarity.getSimilarity(targetCase.getPrice() + "", sourceCase.getPrice() + "") * priceWeight
				+ regionSimilarity.getSimilarity(targetCase.getRegion(), sourceCase.getRegion()) * regionWeight
				+ seasonSimilarity.getSimilarity(targetCase.getSeason(), sourceCase.getSeason()) * seasonWeight
				+ transportSimilarity.getSimilarity(targetCase.getTransportation(), sourceCase.getTransportation())
						* transportWeight)
				/ (hotelWeight + regionWeight + accommodationWeight + transportWeight + seasonWeight + durationWeight
						+ holidayTypeWeight + numberOfPersonsWeight + priceWeight);
	}

}
