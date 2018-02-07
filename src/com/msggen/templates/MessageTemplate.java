package com.msggen.templates;

import java.util.*;

import org.json.simple.JSONObject;

import com.msggen.model.Company;
import com.msggen.model.Guests;
import com.msggen.model.Reservation;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;

public class MessageTemplate {
	int greetingID;
	String greeting;
	String firstName;
	long templateID;
	String templateMessage;
	String companyName;
	long roomNo;
	
	public MessageTemplate(long templateID, String templateMessage)
	{
		this.templateID = templateID;
		this.templateMessage = templateMessage;
	}
	
	
	public String setTemplateMessage()
	{
		String greet = this.templateMessage.replaceAll("%GREETING%", this.getGreeting());
		String greet1 = greet.replaceAll("%NAME%", this.getFirstName());
		String greet2 = greet1.replaceAll("%COMPANY_NAME%", this.getCompanyName());
		String greet3 = greet2.replaceAll("%ROOM_NO%", String.valueOf(this.getRoomNo()));
		return greet3;
	}
	
	public String getCompanyName()
	{
		return companyName;
	}
	public void setCompanyName(Company c)
	{
		this.companyName = c.getCompanyName();
	}
	public String getFirstName()
	{
		return firstName;
	}
	
	public void setFirstName(Guests g)
	{
		this.firstName = g.getFirstName();
	}
	public long getRoomNo()
	{
		return roomNo;
	}
	
	public void setRoomNo(Reservation r)
	{
		this.roomNo = r.getRoomNo();
	}
	
	public void setGreeting(Reservation r, Company c)
	{
		
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		
		
		df.setTimeZone(TimeZone.getTimeZone(c.getTimeZoneCode(c.getTimeZone())));
		String formatted = df.format(new Date(r.getStartTimestamp()*1000));
		
		
		int hh =  Integer.parseInt(formatted.substring(0,2));
		
		
		if(hh>=0 && hh<11)
		{
			this.greeting = "Good Morning";
			this.greetingID = 1;
		}
		else if(hh>=11 && hh<18)
		{
			this.greeting = "Good Afternoon";
			this.greetingID = 2;
		}
		else if(hh>=18 && hh<=23)
		{
			this.greeting = "Good Evening";
			this.greetingID = 3;
		}
		
	}
	
	public int getGreetingID()
	{
		return greetingID;
	}
	
	public long getTemplateID()
	{
		return templateID;
	}
	public String getTemplateMessage()
	{
		return templateMessage;
	}
	public String getGreeting()
	{
		return greeting;
	}
	

}
