package com.project.PoketQue.service.appointmentService;

import org.springframework.stereotype.Component;
import com.project.PoketQue.interfaces.AppointmentParamCheckerInterface;

@Component
public final class AppointmentParamChecker implements AppointmentParamCheckerInterface{
	@Override
	public boolean isdigit(String num){
		/*
		 * We are checking if the argument can be converted into a number*/
        try {
            Integer.parseInt(num);
        } catch (NumberFormatException e) {
            return false;//can not be converted to a number
        }
        return true; 
    }
	@Override
	public boolean isRightLenght(String num, int numLenght) {
		/*
		 * We are checking if the num argument 
		 * is the lenght specified lenght numLenght*/
		if(num.length() != numLenght ) {
			return false;//the number is not the right lenght
		}
		return true;
	}
}