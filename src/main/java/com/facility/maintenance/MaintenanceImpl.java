package com.facility.maintenance;

import com.facility.base.Facility;

public class MaintenanceImpl implements Maintenance {
	
	private int maintenanceID;
	private int maintenanceRequestID;
	private int facilityID;
	private String details;
	private int cost;
	
	private Facility facility;
	
	public MaintenanceImpl() {}
	
	public int getMaintenanceID() {
		return maintenanceID;
	}

	public void setMaintenanceID(int maintenanceID) {
		this.maintenanceID = maintenanceID;
	}

	public int getMaintenanceRequestID() {
		return maintenanceRequestID;
	}

	public void setMaintenanceRequestID(int maintenanceRequestID) {
		this.maintenanceRequestID = maintenanceRequestID;
	}

	public int getFacilityID() {
		return facilityID;
	}

	public void setFacilityID(int facilityID) {
		this.facilityID = facilityID;
	}

	public String getDetails() {
		return details;
	}
	
	public void setDetails(String details) {
		this.details = details;
	}
	
	public int getCost() {
		return cost;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}

	public Facility getFacility() {
		return facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}
	
}
