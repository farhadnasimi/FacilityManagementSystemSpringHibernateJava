/**
 * 
 */
package com.facility.base;

public interface FacilityDetail {
	public String getName();
	public void setName(String name);
	public int getFacilityID();
	public void setFacilityID(int facilityID);
	public int getNumberOfRooms();
	public void setNumberOfRooms(int numberOfRooms);
	public int getPhoneNumber();
	public void setPhoneNumber(int phoneNumber);
	public void setFacility(Facility facility);
	public Facility getFacility();
}
