package com.kipsu.msggen.tests;
import java.io.BufferedReader;
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
	
	public static ArrayList<JSONObject> addGuestDataFromJSON(ArrayList<JSONObject> responseListGuest) throws IOException, ParseException
	{
		try {
			FileReader fr = new FileReader(guestfilePath);
			JSONParser jsonParser = new JSONParser();
			Object guest_obj = jsonParser.parse(fr);
			JSONArray jsonArrayGuestObject = (JSONArray) guest_obj ;
			//responseListGuest = new ArrayList<JSONObject>();
			for(int i = 0; i < jsonArrayGuestObject.size(); i++){
				responseListGuest.add((JSONObject) jsonArrayGuestObject.get(i));
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responseListGuest;
		
	}
	
	public static ArrayList<JSONObject> addCompanyDataFromJSON(ArrayList<JSONObject> responseListCompany) throws IOException, ParseException
	{
		try {
			FileReader fr = new FileReader(companyfilePath);
			JSONParser jsonParser = new JSONParser();
			Object comp_obj = jsonParser.parse(fr);
			JSONArray jsonArrayCompObject = (JSONArray) comp_obj ;
			//responseListCompany = new ArrayList<JSONObject>();
			for(int i = 0; i < jsonArrayCompObject.size(); i++){
				responseListCompany.add((JSONObject) jsonArrayCompObject.get(i));
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responseListCompany;
		
	}
	
	public static ArrayList<JSONObject> addTemplateDataFromJSON(ArrayList<JSONObject> responseListTemplate) throws IOException, ParseException
	{
		try {
			FileReader fr = new FileReader(templatefilePath);
			JSONParser jsonParser = new JSONParser();
			Object temp_obj = jsonParser.parse(fr);
			JSONArray jsonArrayTempObject = (JSONArray) temp_obj ;
			//responseListTemplate = new ArrayList<JSONObject>();
			for(int i = 0; i < jsonArrayTempObject.size(); i++){
				responseListTemplate.add((JSONObject) jsonArrayTempObject.get(i));
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responseListTemplate;
		
	}
	public static void main(String [] args)
	{
		
	
	try {
		
		ArrayList<JSONObject> responseListGuest = new ArrayList<JSONObject>();
		ArrayList<JSONObject> responseListCompany = new ArrayList<JSONObject>();
		ArrayList<JSONObject> responseListTemplate = new ArrayList<JSONObject>();
		
		/*addGuestDataFromJSON(responseListGuest);
		addCompanyDataFromJSON(responseListCompany);
		addTemplateDataFromJSON(responseListTemplate);*/
		
		FileReader fr = new FileReader(guestfilePath);
		FileReader cfr = new FileReader(companyfilePath);
		FileReader tfr = new FileReader(templatefilePath);
		
		JSONParser jsonParser = new JSONParser();
		
		Object guest_obj = jsonParser.parse(fr);
		Object comp_obj = jsonParser.parse(cfr);
		Object temp_obj = jsonParser.parse(tfr);
		
		
		JSONArray jsonArrayGuestObject = (JSONArray) guest_obj ;
		JSONArray jsonArrayCompObject = (JSONArray) comp_obj;
		JSONArray jsonArrayTempObject = (JSONArray) temp_obj;
		
		
		
		//ArrayList<JSONObject> responseListGuest = new ArrayList<JSONObject>();
		//ArrayList<JSONObject> responseListCompany = new ArrayList<JSONObject>();
		//ArrayList<JSONObject> responseListTemplate = new ArrayList<JSONObject>();
		
		for(int i = 0; i < jsonArrayGuestObject.size(); i++){
			responseListGuest.add((JSONObject) jsonArrayGuestObject.get(i));
		}
		
		for(int i = 0; i < jsonArrayCompObject.size(); i++){
			responseListCompany.add((JSONObject) jsonArrayCompObject.get(i));
		}
		
		for(int i = 0; i < jsonArrayTempObject.size(); i++){
			responseListTemplate.add((JSONObject) jsonArrayTempObject.get(i));
		}
		
		System.out.println("List of users to select from:");
		for(int i = 0; i< jsonArrayGuestObject.size();i++)
		{
			System.out.println(""+responseListGuest.get(i).get("id")+" "+responseListGuest.get(i).get("firstName")+ " "+ responseListGuest.get(i).get("lastName"));
		}
		
		System.out.println("List of companies to select from:");
		for(int i = 0; i< jsonArrayCompObject.size();i++)
		{
			System.out.println(""+responseListCompany.get(i).get("id")+" "+responseListCompany.get(i).get("company")+ " ");
		}
		

		
		
		Scanner userInput = new Scanner(System.in);
		System.out.println("Enter the userID:");
		int id_to_Search = userInput.nextInt();
		
		if(id_to_Search <= 0 || id_to_Search > responseListGuest.size())
		{
			System.out.println("Id does not exist. Please try again.");
			id_to_Search = userInput.nextInt();
			
		}
		System.out.println("Enter the companyID:");
		int compID = userInput.nextInt();
		
		System.out.println("Do you want to use existing template or do you want to create a custom template? \n Press 1 for existing template. \n Press 2 for creating custom template.");
		int tempInput = userInput.nextInt();
		if(tempInput == 1)
		{
			System.out.println("List of templates to choose from:");
			for(int i = 0; i< jsonArrayTempObject.size();i++)
			{
				System.out.println(""+responseListTemplate.get(i).get("tID")+" "+responseListTemplate.get(i).get("templateName")+ " ");
			}
			System.out.println("Enter the templateID:");
			int tempID = userInput.nextInt();
			
			Guests guest = new Guests(id_to_Search - 1,responseListGuest);
			
			
			Reservation resDetails = new Reservation(id_to_Search - 1, responseListGuest);
			Company company = new Company(compID-1, responseListCompany);
			
			MessageTemplate mt= new MessageTemplate(tempID-1, responseListTemplate);
			mt.setGreeting(resDetails, company);
			mt.setFirstName(guest);
			mt.setCompanyName(company);
			mt.setRoomNo(resDetails);
			
			System.out.println("Custom Generated Message: \n" +mt.setTemplateMessage());
		}
		
		else if(tempInput == 2)
		{
			System.out.println("Please use the following metrics as input while creating custom template: ");
			System.out.println(" Use %Greeting% for getting the time of the day \n Use * for getting first name of the guest. \n Use - for getting hotel name. \n Use $ for getting room number.");
			System.out.println("Enter custom message template:");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String messTemp = br.readLine();
			
			
			JSONObject custTemp = new JSONObject();
			custTemp.put("tID", responseListTemplate.size()+1);
			custTemp.put("templateName", messTemp);
			
			responseListTemplate.add(custTemp);
			
			jsonArrayTempObject.addAll(responseListTemplate.size()-1, responseListTemplate);
			jsonArrayTempObject.toJSONString();
			
			JSONArray respTemp = new JSONArray();
			respTemp.addAll(responseListTemplate);
			
			System.out.println(respTemp + "respTemp");
			try (FileWriter file = new FileWriter(templatefilePath)) {
				
				file.write("");
				file.flush();
				
				file.write(respTemp.toJSONString());
				
				
				
			}
			
			Guests guest = new Guests(id_to_Search - 1,responseListGuest);
			
			
			Reservation resDetails = new Reservation(id_to_Search - 1, responseListGuest);
			Company company = new Company(compID-1, responseListCompany);
			
			MessageTemplate mt= new MessageTemplate((long) responseListTemplate.size()-1, responseListTemplate);
			mt.setGreeting(resDetails, company);
			mt.setFirstName(guest);
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
}
