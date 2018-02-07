package com.kipsu.msggen.parser;
/*import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.*;


public class readJsonData {
	public static final String guestfilePath = "G:/Placement/Interviews - Companies/Kipsu/KipsuCodingProject/Guests.json";
	public static final String companyfilePath = "G:/Placement/Interviews - Companies/Kipsu/KipsuCodingProject/Companies.json";
	
	public static void displayGuestsData(Guests guestObj)
	{
		System.out.println(" " + guestObj.getGuestID() + ". " + guestObj.getFirstName() + " " + guestObj.getLastName());
	}
	public static void checkGuestID(int id, Guests guest)
	{
		System.out.println("Enter Guest ID to display their details:");
		Scanner kbd = new Scanner(System.in);
		id = kbd.nextInt();
		if(guest.getGuestID() == id)
		{
			System.out.println(guest.getFirstName(id));
		}
		else System.out.println("Guest ID does not exist");
	}
	public static void main(String [] args)
	{
		
	
	try {
		FileReader fr = new FileReader(guestfilePath);
		JSONParser jsonParser = new JSONParser();
		Object obj = jsonParser.parse(fr);
		//JSONObject inputObject = (JSONObject) obj;
		JSONArray jsonArrayObject = (JSONArray) obj ;
		
		//System.out.println("json object value :" + jsonObject);
		jsonArrayObject.add(obj);
		
		System.out.println(((JSONObject) jsonArrayObject.get(0)).get("reservation"));
		
		System.out.println(jsonArrayObject.get(0));
		System.out.println("List of Users:");
		for(int i=0; i<jsonArrayObject.size()-1;i++)
		{
			//Guests guest = new Guests((JSONObject) jsonArrayObject.get(i));
			Reservation resDetails = new Reservation(guest,(JSONObject) ((JSONObject) jsonArrayObject.get(i)).get("reservation"));
			//System.out.println(guest.toString());
			
			displayGuestsData(guest);
			System.out.println(resDetails.toString());
			
			
		}
		
		
		
		

		
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}

		
	}
}
*/