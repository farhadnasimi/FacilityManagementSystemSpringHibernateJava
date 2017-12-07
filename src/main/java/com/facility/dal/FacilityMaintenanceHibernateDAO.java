package com.facility.dal;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.facility.base.Facility;
import com.facility.maintenance.*;

public class FacilityMaintenanceHibernateDAO {
	public Maintenance makeFacilityMaintRequest(Facility fac, String maintenanceDetails, int cost) {
		
		Maintenance maint = new MaintenanceImpl();
		maint.setFacility(fac);
		maint.setFacilityID(fac.getFacilityID());
		maint.setDetails(maintenanceDetails);
		maint.setCost(cost);
		
		System.out.println("*************** Adding maintenance request to DB with ID ...  " + fac.getFacilityID());
		Session session = HibernatePGSQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save("maint_request", maint);
		session.getTransaction().commit();
		
		return maint;
	}
	
	public void scheduleMaintenanceAdd(Maintenance maintRequest) {
		System.out.println("*************** Adding maintenance to DB with ID ...  " + maintRequest.getFacilityID());
		Session session = HibernatePGSQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save("maintenance", maintRequest);
		session.getTransaction().commit();
	}
	
	public void scheduleMaintenanceDelete(Maintenance maintRequest) {
		System.out.println("*************** Removing maintenance request from DB with ID ...  " + maintRequest.getFacilityID());
		Session session = HibernatePGSQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete("maint_request", maintRequest);
		session.getTransaction().commit();
	}
	
	public List<Maintenance> listMaintRequests(Facility fac) {
		try {
			System.out.println("*************** Retrieving a list of maintenance requests for Facility ...  " + fac.getFacilityID());
			Session session = HibernatePGSQLHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			//System.out.println("*************** Hibernate session is created ..................\n" + session.toString());
			
			Query getMaintRequestsQuery = session.createQuery("From maint_request where facility_ID =" + fac.getFacilityID());		
			
			System.out.println("*************** Retrieve Query is ....>>\n" + getMaintRequestsQuery.toString()); 
			
			List<Maintenance> maintRequests = getMaintRequestsQuery.list();
			session.getTransaction().commit();
			
			return maintRequests;
		} catch (Exception e) {
			System.err.println("UseHibernateDAO: Threw an Exception retreiving "
			   		+ "list of maintenance requests.");
			   System.err.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Maintenance> listMaintenance(Facility fac) {
		try {
			System.out.println("*************** Retrieving a list of maintenance requests for Facility ...  " + fac.getFacilityID());
			Session session = HibernatePGSQLHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			//System.out.println("*************** Hibernate session is created ..................\n" + session.toString());
			
			Query getMaintRequestsQuery = session.createQuery("From maintenance where facility_ID =" + fac.getFacilityID());		
			
			System.out.println("*************** Retrieve Query is ....>>\n" + getMaintRequestsQuery.toString()); 
			
			List<Maintenance> maintRequests = getMaintRequestsQuery.list();
			session.getTransaction().commit();
			
			return maintRequests;
		} catch (Exception e) {
			System.err.println("UseHibernateDAO: Threw an Exception retreiving "
			   		+ "list of maintenance.");
			   System.err.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	public int calcMaintenanceCostForFacility(Facility fac) {
		try {
			System.out.println("*************** Calculating maintenance cost for Facility ...  " + fac.getFacilityID());
			Session session = HibernatePGSQLHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			//System.out.println("*************** Hibernate session is created ..................\n" + session.toString());
			
			Query calcMaintCostQuery = session.createQuery("select sum(cost) as int from maintenance where facility_ID =" + fac.getFacilityID());		
			
			System.out.println("*************** Retrieve Query is ....>>\n" + calcMaintCostQuery.toString()); 
			
			List<Long> cost = calcMaintCostQuery.list();
			session.getTransaction().commit();
			return cost.get(0).intValue();
		} catch (Exception e) {
			System.err.println("UseHibernateDAO: Threw an Exception calculating "
			   		+ "cost of maintenance.");
			   System.err.println(e.getMessage());
			e.printStackTrace();
		}
		return 0;
	}
}
