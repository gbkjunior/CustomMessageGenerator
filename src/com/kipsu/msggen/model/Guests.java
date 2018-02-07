package com.kipsu.msggen.model;
import java.util.ArrayList;
import org.json.simple.JSONObject;

public class Guests {
	long guestID;
	String firstName;
	String lastName;
	
	ArrayList<Guests> guestArray = new ArrayList();
	public Guests(long guestID, String firstName, String lastName)
	{
		this.guestID = guestID;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Guests(int index, ArrayList<JSONObject> guestObj)
	{
		
		this.guestID = Long.parseLong(guestObj.get(index).get("id").toString());
		
		this.firstName = (String) guestObj.get(index).get("firstName");
		this.lastName = (String) guestObj.get(index).get("lastName");
	}
	public long getGuestID()
	{
		return guestID;
	}
	

	public String getFirstName()
	{
		return firstName;
	}
	public String getLastName()
	{
		return lastName;
	}
	
	public String toString()
	{
		return("id: "+ this.getGuestID() + ", \n" + "firstName: " + this.getFirstName() +", \n" + "lastName: " +this.getLastName()+ " " );
	}
}
