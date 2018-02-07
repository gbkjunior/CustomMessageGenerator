package com.msggen.model;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;
import java.sql.Date;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class Reservation {
	long guestID;
	long roomNo;
	long startTimestamp;
	long endTimestamp;
	
	public Reservation(long guestID, long roomNo, long startTimestamp, long endTimestamp)
	{
		this.guestID = guestID;
		this.roomNo = roomNo;
		this.startTimestamp = startTimestamp;
		this.endTimestamp = endTimestamp;
	}

	public long getGuestID()
	{
		return guestID;
	}
	
	public long getRoomNo()
	{
		return roomNo;
	}
	
	public long getStartTimestamp()
	{
		return startTimestamp;
		
	}
	
	public long getEndTimestamp()
	{
		return endTimestamp;
	}
	
	public String getStartTime(long startTimestamp, Company c)
	{
		
		SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss z");
		simpleDate.setTimeZone(TimeZone.getTimeZone(c.getTimeZoneCode(c.getTimeZone())));
		String formatted = simpleDate.format(new Date(startTimestamp*1000));
		return formatted;

	}
	
	
	
	public void getEndTime(long endTimestamp)
	{
		
		Timestamp t = new Timestamp(endTimestamp);
		SimpleDateFormat simpleDate = new SimpleDateFormat(" HH:mm:ss:S");
		
		System.out.println("End Time: "  + simpleDate.format(t) );
	}
	
	public String toString()
	{
		return("Reservation Details: "+ "GuestID: "+ this.guestID + ", \n"+"roomNo:" + this.getRoomNo() + ", \n" + "startTimestamp: " + this.getStartTimestamp() + ", \n" +
				"endTimestamp:" +this.getEndTimestamp() + "}");
	}
}
