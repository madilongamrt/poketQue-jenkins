package com.project.PoketQue.service.appointmentService;

public class addAppointment2 {
    public final int[] MYtime(String timm){
    	int[] timmarr = new int[2];
    	
        String paraHours = timm.substring(0,2);
        String paraMin = timm.substring(2,4);
        int hours = Integer.parseInt(paraHours);
        int mins = Integer.parseInt(paraMin);
        
        if (hours > 23 || hours < 9 || mins < 0 || mins > 59 || (hours==23 && mins >= 05)) {
            timmarr[0] = 0;
            timmarr[1] = 0;
            return timmarr;
        }
        
        timmarr[0] = hours;
        timmarr[1] = mins;
        return timmarr;
    }
}
//remane the operating hours