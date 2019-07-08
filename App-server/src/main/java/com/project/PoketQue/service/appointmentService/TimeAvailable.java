package com.project.PoketQue.service.appointmentService;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.project.PoketQue.Models.Appointment;
import com.project.PoketQue.interfaces.TimeAvailableInterface;

@Component
public final class TimeAvailable implements TimeAvailableInterface{
		private boolean after30(LocalDateTime timeCheck, ArrayList<LocalDateTime> timearray){
	       /* the idea is to have a maximun amount of 5 appointment per x amount of minutes 
	        * 
	        * this method is used to check how many appointments are there already in the array
	        * that have been booked in the first 30minutes of the hour 
	        *
	        */
	        
	        int countTime = 1;
	        for(LocalDateTime i : timearray){
	            if((timeCheck.getHour() == i.getHour() && timeCheck.getMinute() >= 31) &&
	             (i.getMinute() >= 31 && i.getMinute() <= 59)){
	                countTime +=1;//counts the total amount of bookings in the 2nd 30min of a hour time frame
	            }
	        }
	        if(countTime <= 5){//max amount of people
	            return true;
	        }
	        return false;
	    }
	    private boolean before30(LocalDateTime timeCheck, ArrayList<LocalDateTime> timearray){
	        int countTime = 1;
	        for(LocalDateTime i : timearray){
	            if((timeCheck.getHour() == i.getHour() && timeCheck.getMinute() <= 30) &&
	             (i.getMinute() >= 0 && i.getMinute() <= 30)){
	                countTime +=1;
	            }
	        }
	        if(countTime <= 5){
	            return true;
	        }
	        return false;
	    }
	    private ArrayList<LocalDateTime> getAppointmentTimes(ArrayList<Appointment> appoint,int branchid){
	        /*
	            this method puts the the times of the Appointment array in a array of apointment TIMES
	            this array of minutes is required in order to find the total amount of appoinment bookings
	            made already in the time the user specifed
	        */  
	        ArrayList<LocalDateTime> timearray = new ArrayList<LocalDateTime>();
	        for(Appointment a: appoint){
	        		if(branchid == a.getBranch().getId()) {//checks  to find the appointment is being set in the same branch
	        			timearray.add(a.getAppointmentTime());
	        		}
	        }
	        return timearray;
	    }
	    @Override
	    public boolean checkAppointmentTime(LocalDateTime timecheck, ArrayList<Appointment> appoint,int branchid){
	        //LocalDateTime timecheck = LocalDate.now().atTime(11, 31);
	    	boolean addOrNot = false;
	    	if((timecheck.getHour() < 9 && timecheck.getHour() >= 17) 
	        && (timecheck.getMinute()>=59 && timecheck.getMinute()<=0)){
	    		//prevent booking of appointments before 9am and after 15:00
	            //prevent bookings with minutes that are under zero and about 59
	    		return addOrNot;
	        }
	    	if(appoint.isEmpty()) {
	    		//if the are no appointments in the array then go ahead and create an apoointment
	    		return true;
	    	}
	        ArrayList<LocalDateTime> timearray = getAppointmentTimes(appoint,branchid);
	        //takes the list of appointments
	        //and only takes all the times already in the appointment bookings
	        if(timecheck.getMinute()<=30){
	            addOrNot = before30(timecheck,timearray);
	        }
	        if(timecheck.getMinute()>=31){
	            addOrNot = after30(timecheck,timearray);
	        }
	        /*
	            the idea is that should the the total amount of times in the specifecd range
	            that the client wants to book be less than 5 then true will be returned and vice 
	            versa  
	        */
	        return addOrNot;
	    }

}
