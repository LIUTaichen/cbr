package com.uoa.cbr.cases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CaseLoader {

	@PersistenceContext
	private  EntityManager em;

	private static EntityManagerFactory factory;

	private static Logger log = LogManager.getLogger(CaseLoader.class);

	public static void main(String[] args) throws FileNotFoundException {

		CaseLoader caseLoader = new CaseLoader();
		caseLoader.run();

	}
	
	private void run(){
		factory = Persistence.createEntityManagerFactory("jpa-persistence");
		em = factory.createEntityManager();
		try {
			InputStream inp = new FileInputStream("TRAVEL.xlsx");
			Workbook wb = new XSSFWorkbook(inp);
			Sheet sheet = wb.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			em.getTransaction().begin();
			while (rowIterator.hasNext()) {
				TravelCase travelCase = new TravelCase();
				Row row = rowIterator.next();
				row = rowIterator.next();
				log.info("index " + row.getCell(2));
				travelCase.setCaseId((int) row.getCell(2).getNumericCellValue());
				row = rowIterator.next();
				log.info("holiday type " + row.getCell(2));
				String holidayType = row.getCell(2).getStringCellValue();
				holidayType = holidayType.replaceAll(",", "");
				travelCase.setHolidayType(holidayType);
				
				row = rowIterator.next();
				log.info("price " + row.getCell(2));
				travelCase.setPrice((int) row.getCell(2).getNumericCellValue());
				row = rowIterator.next();
				log.info("no of persons " + row.getCell(2));
				travelCase.setNumberOfPersons((int) row.getCell(2).getNumericCellValue());
				row = rowIterator.next();
				log.info("region " + row.getCell(2));
				String region = row.getCell(2).getStringCellValue();
				region = region.replaceAll(",", "");
				travelCase.setRegion(region);
				row = rowIterator.next();
				log.info("transportation" + row.getCell(2));
				String transportation = row.getCell(2).getStringCellValue();
				transportation = transportation.replaceAll(",", "");
				travelCase.setTransportation(transportation);
				row = rowIterator.next();
				log.info("duration " + row.getCell(2));
				travelCase.setDuration((int) row.getCell(2).getNumericCellValue());
				row = rowIterator.next();
				log.info("season " + row.getCell(2));
				String season = row.getCell(2).getStringCellValue();
				season = season.replaceAll(",", "");
				travelCase.setSeason(season);
				row = rowIterator.next();
				log.info("accomodation " + row.getCell(2));
				String accomodation = row.getCell(2).getStringCellValue();
				accomodation = accomodation.replaceAll(",", "");
				travelCase.setAccommodation(accomodation);
				row = rowIterator.next();
				log.info("hotel " + row.getCell(2));
				travelCase.setHotel(row.getCell(2).getStringCellValue());
				if (rowIterator.hasNext()) {
					row = rowIterator.next();
					row = rowIterator.next();
				}

				this.saveTravelCase(travelCase);

			}
			em.getTransaction().commit();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Transactional
	private void saveTravelCase(TravelCase travelCase) {
		em.persist(travelCase);

	}

}
