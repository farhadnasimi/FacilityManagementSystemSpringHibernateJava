package com.facility.view;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.facility.base.*;
import com.facility.maintenance.Maintenance;
import com.facility.service.MaintenanceService;

public class MaintenanceClient {

	public MaintenanceClient() throws Exception {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/app-context.xml");
        System.out.println("***************** Application Context instantiated! ******************");
		
        MaintenanceService maintenanceService = (MaintenanceService) context.getBean("maintenanceService");
		
/*		//set up facilities for dummy data
		Facility fact1 = new FacilityImpl();
		FacilityDetail factDet1 = new FacilityDetailImpl();
		fact1.setFacilityID(1);
		factDet1.setNumberOfRooms(2);
		fact1.setFacilityDetail(factDet1);
		
		Facility fact3 = new FacilityImpl();
		FacilityDetail factDet3 = new FacilityDetailImpl();
		fact3.setFacilityID(3);
		factDet3.setNumberOfRooms(6);
		fact3.setFacilityDetail(factDet3);
		
		Facility fact4 = new FacilityImpl();
		FacilityDetail factDet4 = new FacilityDetailImpl();
		fact4.setFacilityID(4);
		factDet4.setNumberOfRooms(5);
		fact4.setFacilityDetail(factDet4);
*/
		
		Facility fact7 = (Facility) context.getBean("facility");
		FacilityDetail factDet7 = (FacilityDetail) context.getBean("facilityDetail");
		fact7.setFacilityID(7);
		factDet7.setFacilityID(7);
		factDet7.setFacility(fact7);
		factDet7.setNumberOfRooms(10);
		fact7.setFacilityDetail(factDet7);
        
		
		System.out.println("\nMaintenanceClient: *********** Creating new facility maintenance request *****************");
		Maintenance maintenance = maintenanceService.makeFacilityMaintRequest(fact7, "testing maintenance", 100);
		System.out.println("\nMaintenanceClient: *********** Maintenance request created *****************");
		
		
		System.out.println("\nMaintenanceClient: *********** Scheduling this maintenance request *****************");
		maintenanceService.scheduleMaintenance(maintenance);
		System.out.println("\nMaintenanceClient: *********** Maintenance request scheduled *****************");
		
		System.out.println("\nMaintenanceClient: *********** Calculate total maintenance cost of a facility *****************");
		int totalCost = maintenanceService.calcMaintenanceCostForFacility(fact7);
		System.out.println("The total cost of maintenance already completed at Facility #" + fact7.getFacilityID() + " is $" + totalCost + ".");
		
		
		//uses sample data to list maintenance requests, formatted as a table
		System.out.println("\nMaintenanceClient: *********** List current maintenance requests at a facility *****************");
		List<Maintenance> maintRequestList = maintenanceService.listMaintRequests(fact7);
		Object[][] requests = new Object[maintRequestList.size() + 1][2];
		requests[0] = new Object[] {"Maintenance Request Details", "Cost"};
		for (int i = 1; i <= maintRequestList.size(); i++) {
			requests[i] = new Object[] {maintRequestList.get(i-1).getDetails(), maintRequestList.get(i-1).getCost()};
		}
		System.out.println("Current maintenance requests at Facility #" + fact7.getFacilityID() + ":");
		for (Object[] row : requests) {
			System.out.format("   %-29s%6s\n", row);
		}
		
		//uses sample data to list completed maintenance, formatted as a table
		System.out.println("\nMaintenanceClient: *********** List maintenance completed at a facility *****************");
		List<Maintenance> maintenanceList = maintenanceService.listMaintenance(fact7);
		Object[][] maintenanceTable = new Object[maintenanceList.size() + 1][2];
		maintenanceTable[0] = new Object[] {"Maintenance Details", "Cost"};
		for (int i = 1; i <= maintenanceList.size(); i++) {
			maintenanceTable[i] = new Object[] {maintenanceList.get(i-1).getDetails(), maintenanceList.get(i-1).getCost()};
		}
		System.out.println("Maintenance completed at Facility #" + fact7.getFacilityID() + ":");
		for (Object[] row : maintenanceTable) {
			System.out.format("   %-30s%6s\n", row);
		}
		
		//uses sample data to list facility problems, formatted as a table
		System.out.println("\nMaintenanceClient: *********** List all problems that have affected a facility *****************");
		List<Maintenance> facilityProblemsList = maintenanceService.listFacilityProblems(fact7);
		Object[][] problems = new Object[facilityProblemsList.size() + 1][2];
		problems[0] = new Object[] {"Problem Details", "Cost"};
		for (int i = 1; i <= facilityProblemsList.size(); i++) {
			problems[i] = new Object[] {facilityProblemsList.get(i-1).getDetails(), facilityProblemsList.get(i-1).getCost()};
		}
		System.out.println("Problems at Facility #" + fact7.getFacilityID() + ":");
		for (Object[] row : problems) {
			System.out.format("   %-30s%6s\n", row);
		}
		
		System.out.println("\nMaintenanceClient: *********** Calculate the down time for a facility *****************");
		int downTime = maintenanceService.calcDownTimeForFacility(fact7);
		System.out.println("Facility #" + fact7.getFacilityID() + " was down for maintenance for " + downTime + " days total, "
				+ "assuming each completed maintenance request took 7 days to complete.");
	
		System.out.println("\nMaintenanceClient: *********** Calculate the problem rate for a facility *****************");
		double problemRate = maintenanceService.calcProblemRateForFacility(fact7) * 100;
		System.out.print("\nThe problem rate at Facility #" + fact7.getFacilityID() + " is ");
		System.out.format("%.2f", problemRate);
		System.out.print("%.");
				
	}
	
}
