package com.facility.dal;

import java.time.LocalDate;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

import com.facility.base.Facility;
import com.facility.use.*;

public class FacilityUseHibernateDAO {

	public List<Inspection> listInspections(Facility fac) {
		try {
			System.out.println("*************** Retrieving a list of Inspections for Facility ...  " + fac.getFacilityID());
			Session session = HibernatePGSQLHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			//System.out.println("*************** Hibernate session is created ..................\n" + session.toString());
			
			Query getInspectionsQuery = session.createQuery("From InspectionImpl where facility_ID =" + fac.getFacilityID());		
			
			System.out.println("*************** Retrieve Query is ....>>\n" + getInspectionsQuery.toString()); 
			
			List<Inspection> inspections = getInspectionsQuery.list();
			session.getTransaction().commit();
			
			return inspections;
		} catch (Exception e) {
			System.err.println("UseHibernateDAO: Threw an Exception retreiving "
			   		+ "inspections from InspectionsImpl.");
			   System.err.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean isInUseDuringInterval(FacilityUse facUse) {
		boolean result = false;
		try {
			System.out.println("*************** Checking to see if Facility " + facUse.getFacilityID() + " is in use during an interval.");
			Session session = HibernatePGSQLHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			//System.out.println("*************** Hibernate session is created ..................\n" + session.toString());
			
			Query checkUseQuery = session.createQuery("From FacilityUseImpl where facility_ID =" + 
					facUse.getFacilityID() + " AND room_number =" + facUse.getRoomNumber());		
			
			System.out.println("*************** Retrieve Query is ....>>\n" + checkUseQuery.toString()); 
			
			List<FacilityUse> usage = checkUseQuery.list();
			for(FacilityUse useResult : usage) {
				LocalDate assignStart = useResult.getStartDate();
				LocalDate assignEnd = useResult.getEndDate();
				if (facUse.getStartDate().isBefore(assignEnd) && (assignStart.isBefore(facUse.getEndDate()) || 
	    				assignStart.equals(facUse.getEndDate()))) {
	    			result = true;
	    			break;
				}
			}
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			System.err.println("UseHibernateDAO: Threw an Exception retreiving "
			   		+ "checking if a facility is in use during an interval.");
			   System.err.println(e.getMessage());
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void assignFacilityToUse(FacilityUse facUse) {
		System.out.println("*************** Assigning to use Facility with ID ...  " + facUse.getFacilityID());
		Session session = HibernatePGSQLHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(facUse);
		session.getTransaction().commit();
	}
	
}
