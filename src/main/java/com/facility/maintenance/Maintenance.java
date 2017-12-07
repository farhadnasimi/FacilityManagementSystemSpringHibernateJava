package com.facility.maintenance;

import com.facility.base.Facility;

public interface Maintenance {
	public int getMaintenanceID();
	public void setMaintenanceID(int maintenanceID);
	public int getMaintenanceRequestID();
	public void setMaintenanceRequestID(int maintenanceRequestID);
	public int getFacilityID();
	public void setFacilityID(int facilityID);
	public String getDetails();
	public void setDetails(String details);
	public int getCost();
	public void setCost(int cost);
	public Facility getFacility();
	public void setFacility(Facility facility);
}
