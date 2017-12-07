package com.facility.use;

import com.facility.base.Facility;

public interface Inspection {
	public int getInspectionID();
	public void setInspectionID(int inspectionID);
	public int getFacilityID();
	public void setFacilityID(int facilityID);
	public String getInspection_type();
	public void setInspection_type(String inspection_type);
	public String getInspection_detail();
	public void setInspection_detail(String inspection_detail);
	public Facility getFacility();
	public void setFacility(Facility facility);
}
