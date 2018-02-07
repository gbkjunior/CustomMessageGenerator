package com.kipsu.msggen.tests;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.kipsu.msggen.model.Company;
import com.kipsu.msggen.model.Guests;
import com.kipsu.msggen.model.Reservation;
import com.kipsu.msggen.templates.MessageTemplate;

public class TestOutput {
	public static final String guestfilePath = "resources/Guests.json";
	public static final String companyfilePath = "resources/Companies.json";
	public static final String templatefilePath = "resources/template.json";
	private Scanner userInput;
	
	protected ArrayList<Guests> getGuests(JSONArray guestArray)
	{
		ArrayList<Guests> guestList = new ArrayList<Guests>();
		
		
		
		for(int i=0; i<guestArray.size();i++)
		{
			 JSONObject guestObject = (JSONObject)guestArray.get(i);
			 long guestID = (long)guestObject.get("id");
			 String firstName = (String) guestObject.get("firstName");
			 String lastName = (String) guestObject.get("lastName");
			guestList.add(new Guests(guestID, firstName, lastName));
		}
		return guestList;
		
	}
	
	protected ArrayList<Reservation> getReservationDetails(JSONArray guestArray)
	{
		ArrayList<Reservation> reservationList = new ArrayList<Reservation>();
		
		
		
		for(int i=0; i<guestArray.size();i++)
		{
			 JSONObject reservationObject = (JSONObject) guestArray.get(i);
			 long guestID = (long) reservationObject.get("id");
			 JSONObject res =(JSONObject) reservationObject.get("reservation");
			 long roomNo = (long) res.get("roomNumber");
			 long startTimestamp = (long) res.get("startTimestamp");
			 long endTimestamp = (long) res.get("endTimestamp");
			 reservationList.add(new Reservation(guestID, roomNo, startTimestamp, endTimestamp));
		}
		return reservationList;
		
	}
	
	
	protected ArrayList<Company> getCompany(JSONArray companyArray)
	{
		ArrayList<Company> companyList = new ArrayList<Company>();
		for(int i=0; i<companyArray.size();i++)
		{
			 JSONObject companyObject = (JSONObject)companyArray.get(i);
			 long companyID = (long)companyObject.get("id");
			 String companyName = (String) companyObject.get("company");
			 String city = (String) companyObject.get("city");
			 String timeZone = (String) companyObject.get("timezone");
			 companyList.add(new Company(companyID, companyName, city, timeZone));
		}
		return companyList;
		
	}
	
	protected ArrayList<MessageTemplate> getTemplate(JSONArray templateArray)
	{
		ArrayList<MessageTemplate> templateList = new ArrayList<MessageTemplate>();
		for(int i=0; i<templateArray.size();i++)
		{
			 JSONObject templateObject = (JSONObject)templateArray.get(i);
			 long templateID = (long)templateObject.get("tID");
			 String templateMessage = (String) templateObject.get("templateMessage");
			 templateList.add(new MessageTemplate(templateID, templateMessage));
			
		}
		return templateList;
		
	}
	protected void displayGuestDetails(ArrayList<Guests> guestList)
	{
		for(int i=0; i<guestList.size();i++)
		{
			System.out.println(guestList.get(i).getGuestID()+ "   " + guestList.get(i).getFirstName() + "       "  + guestList.get(i).getLastName());
		}
		
	}
	
	protected void displayCompanyDetails(ArrayList<Company> companyList)
	{
		for(int i = 0; i< companyList.size(); i++)
		{
			System.out.println(companyList.get(i).getCompanyID() + "   " + companyList.get(i).getCompanyName());
		}
	}
	
	protected void displayTemplateDetails(ArrayList<MessageTemplate> templateList)
	{
		for(int i = 0; i< templateList.size(); i++)
		{
			System.out.println("" + templateList.get(i).getTemplateID() + " " + templateList.get(i).getTemplateMessage());
		}
	}
	
	protected boolean checkGuestID(int id, ArrayList<Guests> guestList)
	{
		boolean flag = false;
		for(int i=0; i< guestList.size();i++)
		{
			if(guestList.get(i).getGuestID() == id)
			{
				flag = true;
			}

		}
		return flag;
	}
	protected void writeNewTemplate(JSONArray jsonArrayTempObject, ArrayList<MessageTemplate> templateList, String messageTemplate)
	{
		JSONObject custTemp = new JSONObject();
		custTemp.put("tID", templateList.size()+1);
		custTemp.put("templateMessage", messageTemplate);
		
		jsonArrayTempObject.add(custTemp);
		
		try (FileWriter file = new FileWriter(templatefilePath)) {
			
			file.write("");
			file.flush();
			
			file.write(jsonArrayTempObject.toJSONString());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run()
	{
		try {
			
			FileReader fr = new FileReader(guestfilePath);
			FileReader cfr = new FileReader(companyfilePath);
			FileReader tfr = new FileReader(templatefilePath);
			
			JSONParser jsonParser = new JSONParser();
			
			JSONArray jsonArrayGuestObject = (JSONArray) jsonParser.parse(fr) ;
			JSONArray jsonArrayCompObject = (JSONArray) jsonParser.parse(cfr);
			JSONArray jsonArrayTempObject = (JSONArray) jsonParser.parse(tfr);
			
			
			
			ArrayList<Guests> guestList = getGuests(jsonArrayGuestObject);
			ArrayList<Reservation> reservationList = getReservationDetails(jsonArrayGuestObject);
			ArrayList<Company> companyList = getCompany(jsonArrayCompObject);
			ArrayList<MessageTemplate> templateList = getTemplate(jsonArrayTempObject);
			
		
			System.out.println("List of users to select from: ");
			System.out.println("ID  " + "firstName   " + "lastName");
			displayGuestDetails(guestList);
			
			System.out.println("List of companies to select from:");
			System.out.println("ID  " + "companyName ");
			displayCompanyDetails(companyList);
			

			int searchID;
			
			userInput = new Scanner(System.in);
			System.out.println("Enter the userID:");
			String guestInput = userInput.nextLine();
			searchID = Integer.parseInt(guestInput);
			//System.out.println(checkGuestID(searchID, guestList));
/*			while(!(checkGuestID(searchID,guestList)))
					{
 						if(searchID <= 0 || searchID > guestList.size()) // not completed to validate the userID
						{
							System.out.println("Id does not exist. Please try again.");
							searchID = userInput.nextInt();
							
						}
					}*/
			
			System.out.println("Enter the companyID:");
			String compInput = userInput.nextLine();
			
			int compID = Integer.parseInt(compInput);
			
			System.out.println("Do you want to use existing template or do you want to create a custom template? \n Press 1 for existing template. \n Press 2 for creating custom template.");
			int tempInput = userInput.nextInt();
			if(tempInput == 1)
			{
				System.out.println("List of templates to choose from:");
				displayTemplateDetails(templateList);
				System.out.println("Enter the templateID:");
				int tempID = userInput.nextInt();
				
				
				
				Guests g = new Guests(guestList.get(Integer.parseInt(guestInput)-1).getGuestID(),
										guestList.get(Integer.parseInt(guestInput)-1).getFirstName(),
										guestList.get(Integer.parseInt(guestInput)-1).getLastName());
				
				Reservation resDetails = new Reservation(reservationList.get(Integer.parseInt(guestInput)-1).getGuestID(),
															reservationList.get(Integer.parseInt(guestInput)-1).getRoomNo(),
															reservationList.get(Integer.parseInt(guestInput)-1).getStartTimestamp(),
															reservationList.get(Integer.parseInt(guestInput)-1).getEndTimestamp());
				
				Company company = new Company(companyList.get(Integer.parseInt(compInput)-1).getCompanyID(),
												companyList.get(Integer.parseInt(compInput)-1).getCompanyName(),
												companyList.get(Integer.parseInt(compInput)-1).getCity(),
												companyList.get(Integer.parseInt(compInput)-1).getTimeZone());
				
				MessageTemplate mt= new MessageTemplate(templateList.get(tempID-1).getTemplateID(), templateList.get(tempID-1).getTemplateMessage());
				mt.setGreeting(resDetails, company);
				mt.setFirstName(g);
				mt.setCompanyName(company);
				mt.setRoomNo(resDetails);
				
				System.out.println("Custom Generated Message: \n" +mt.setTemplateMessage());
			}
			
			else if(tempInput == 2)
			{
				System.out.println("Please use the following metrics as input while creating custom template: ");
				System.out.println(" Use %GREETING% for getting the time of the day \n Use %NAME% for getting first name of the guest. \n Use %COMPANY_NAME% for getting hotel name. \n Use %ROOM_NO% for getting room number.");
				System.out.println("Enter custom message template:");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				String messTemp = br.readLine();
				
				writeNewTemplate(jsonArrayTempObject, templateList, messTemp);
				
				jsonArrayTempObject = (JSONArray) jsonParser.parse(new InputStreamReader(new FileInputStream(templatefilePath)));
				templateList = getTemplate(jsonArrayTempObject);
				
				Guests g = new Guests(guestList.get(Integer.parseInt(guestInput)-1).getGuestID(),
										guestList.get(Integer.parseInt(guestInput)-1).getFirstName(),
										guestList.get(Integer.parseInt(guestInput)-1).getLastName());

				Reservation resDetails = new Reservation(reservationList.get(Integer.parseInt(guestInput)-1).getGuestID(),
															reservationList.get(Integer.parseInt(guestInput)-1).getRoomNo(),
															reservationList.get(Integer.parseInt(guestInput)-1).getStartTimestamp(),
															reservationList.get(Integer.parseInt(guestInput)-1).getEndTimestamp());

				Company company = new Company(companyList.get(Integer.parseInt(compInput)-1).getCompanyID(),
												companyList.get(Integer.parseInt(compInput)-1).getCompanyName(),
												companyList.get(Integer.parseInt(compInput)-1).getCity(),
												companyList.get(Integer.parseInt(compInput)-1).getTimeZone());

				
				MessageTemplate mt= new MessageTemplate(templateList.get(templateList.size()-1).getTemplateID(), templateList.get(templateList.size()-1).getTemplateMessage());
				mt.setGreeting(resDetails, company);
				mt.setFirstName(g);
				mt.setCompanyName(company);
				mt.setRoomNo(resDetails);
				System.out.println("Custom Generated Message: \n" + mt.setTemplateMessage());
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String [] args)
	{
		
	TestOutput testOutput = new TestOutput();
	testOutput.run();
	

		
	}
}
