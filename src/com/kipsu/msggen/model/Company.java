package com.kipsu.msggen.model;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class Company {

	long companyID;
	String companyName;
	String city;
	String timeZone;
	

	
	public Company(int index, ArrayList<JSONObject> companyObject)
	{
		this.companyID = (long) companyObject.get(index).get("id");
		this.companyName = (String) companyObject.get(index).get("company");
		this.city = (String) companyObject.get(index).get("city");
		this.timeZone = (String) companyObject.get(index).get("timezone");
	}
	
	public long getCompanyID()
	{
		return companyID;
	}
	
	public String getCompanyName()
	{
		return companyName;
	
	}
	
	public String getCity()
	{
		return city;
	}
	
	public String getTimeZone()
	{
		return timeZone;
	}
	
	public String toString()
	{
		return("{ \n"+ "id: " + this.getCompanyID() + ", \n" + 
						"company: " +this.getCompanyName() + ", \n" +
						"city: " + this.getCity() + ", \n" +
						"timezone: "+ this.getTimeZone() +
						"\n}");
	}
	
	public String getTimeZoneCode(String timeZone)
	{
		String code = "";
		if(timeZone.equals("US/Central"))
		{
			code="GMT-6";
		}
		else if(timeZone.equals("US/Eastern"))
		{
			code="GMT-5";
		}
		else if(timeZone.equals("US/Pacific"))
		{
			code = "GMT-8";
		}
		return code;
	}
	
}
