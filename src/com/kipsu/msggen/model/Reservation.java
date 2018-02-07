package com.kipsu.msggen.model;
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
	

	public Reservation (int index, ArrayList<JSONObject> guestObj)
	{
		this.guestID = (long) guestObj.get(index).get("id");
		JSONObject reservation = (JSONObject) guestObj.get(index).get("reservation");
		
		this.roomNo = (long) reservation.get("roomNumber");
		this.startTimestamp = (long) reservation.get("startTimestamp");
		this.endTimestamp = (long) reservation.get("endTimestamp");
 		
		
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
