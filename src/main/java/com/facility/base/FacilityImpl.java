package com.facility.base;

public class FacilityImpl implements Facility {
	
	private int facilityID;
	private FacilityDetail facilityDetail;
		
	public FacilityImpl() {}
	
	public FacilityDetail getFacilityDetail() {
		return facilityDetail;
	}

	public void setFacilityDetail(FacilityDetail facilityDetail) {
		this.facilityDetail = facilityDetail;
	}

	public void setFacilityID(int facilityID) {
		this.facilityID = facilityID;
	}

	public int getFacilityID() {
		return facilityID;
	}


	
}
