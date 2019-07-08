package com.project.PoketQue.service.appointmentService;

import java.util.Random;

import com.project.PoketQue.interfaces.RandomCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class randomCreator implements RandomCreator {
	
	@Autowired
	RandomCreator randomcreator;
	//private appointmentRepo ar;
	//private String sk;
	private final String randomStr(int i) {
		//this method creates a random number for ref
		String randomCar = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890mnbvcxzasdfghjklpoiuytrewq";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < i) { 
            int index = (int) (rnd.nextFloat() * randomCar.length());
            salt.append(randomCar.charAt(index));
        }
        return salt.toString(); 
	}
	@Override
	public String random(int i) {
		return randomStr(i);
	}
	
	/*
	 * Check if the random ref is unique
	 * 
	private boolean IsCodeUnqiue(String ref) {
		Optional<Appointment> oa = ar.findByAppointmentref(ref);
		if(oa.isPresent()) {
			oa = null;
			return false;
		}
		oa = null;
		return true;
	}*/
	
	/*public String random(int i) {
		sk = randomStr(i);
		if(IsCodeUnqiue(sk)) {
			//sk = null;
			return sk;
		}
		sk = null;
		return random(i);
	}*/
}