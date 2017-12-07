package com.facility.service;

import com.facility.dal.*;
import com.facility.use.FacilityUse;

import java.time.LocalDate;
import java.util.List;

import com.facility.base.*;

public class FacilityService {
	
	private UseDAO useDAO = new UseDAO();
	private FacilityHibernateDAO facilityHibernateDAO = new FacilityHibernateDAO();
	
	//Insert a new facility in the DB
	public void addNewFacility(Facility facility) {
		try {
			facilityHibernateDAO.addNewFacility(facility);
	    } catch (Exception se) {
	      System.err.println("FacilityService: Threw an Exception adding new facility.");
	      System.err.println(se.getMessage());
	    }
	}
	
	public FacilityDetail getFacilityInformation(int facilityID) {
		
		try {
			FacilityDetail fac = facilityHibernateDAO.getFacilityInformation(facilityID);
	    	return fac;
	    } catch (Exception se) {
	      System.err.println("FacilityService: Threw an Exception retrieving facility.");
	      System.err.println(se.getMessage());
	    }
		return null;
	}
	
	public void removeFacility(Facility fac) {
		
		try {
			facilityHibernateDAO.removeFacilityUse(fac.getFacilityID());
			facilityHibernateDAO.removeFacility(fac);
	    } catch (Exception se) {
	      System.err.println("FacilityService: Threw an Exception removing facility.");
	      System.err.println(se.getMessage());
	    }
	}
	
	public void addFacilityDetail(int ID, int phoneNumber) {
		try {
			facilityHibernateDAO.addFacilityDetail(ID, phoneNumber);
	    } catch (Exception se) {
	      System.err.println("FacilityService: Threw an Exception updating phone in facility_detail.");
	      System.err.println(se.getMessage());
	    }
	}
	
	public List<Facility> listFacilities() {
		try {
			return facilityHibernateDAO.listFacilities();
	    } catch (Exception se) {
	      System.err.println("FacilityService: Threw an Exception retrieving list of facilities.");
	      System.err.println(se.getMessage());
	    }
		
		return null;
	}
	
	public int requestAvailableCapacity(Facility fac) {
		
		try {
			
			List<FacilityUse> usage = useDAO.listActualUsage(fac);
			int roomsInUse = 0;
			if (usage.size() > 0) {
				for (FacilityUse facUse : usage) {
					//if currently in use
					if ((LocalDate.now().equals(facUse.getStartDate()) || LocalDate.now().isAfter(facUse.getStartDate())) &
							LocalDate.now().equals(facUse.getEndDate()) || LocalDate.now().isBefore(facUse.getEndDate())) {
						if (facUse.getRoomNumber() == 0) {
							return 0;
						} else {
							roomsInUse = roomsInUse + 1;
						}
					}
				}
			}
			
			return fac.getFacilityDetail().getNumberOfRooms() - roomsInUse;
			
	    } catch (Exception se) {
	      System.err.println("UseService: Threw an Exception requesting the available capacity of a facility.");
	      System.err.println(se.getMessage());
	    }
		
		return 0;
	}
}
