package com.project.PoketQue.service.appointmentService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.project.PoketQue.Repository.appointmentRepo;
import com.project.PoketQue.Repository.branchRepo;
import com.project.PoketQue.Repository.userRepo;
import com.project.PoketQue.interfaces.QueInterface;
import com.project.PoketQue.interfaces.RandomCreator;
import com.project.PoketQue.interfaces.TimeAvailableInterface;
import com.project.PoketQue.service.queServic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.PoketQue.Models.Appointment;
import com.project.PoketQue.Models.Branch;
import com.project.PoketQue.Models.User;

@Service
public class AppointmentService {

	private ArrayList<Appointment> appointlist = new ArrayList<Appointment>();
	
	@Autowired
	private QueInterface qs;
	@Autowired
	private appointmentRepo apprepo;
	@Autowired
	private branchRepo br;
	@Autowired
	private userRepo ur;
	@Autowired 
	private RandomCreator rc;
	@Autowired
	TimeAvailableInterface timeA;

	public AppointmentService() {}

	public AppointmentService (queServic qsParam) {
		this.qs = qsParam;
	}

	public ArrayList<Appointment> getAllAppointment(){
		return appointlist;
	}
	public final Object deleteAppointment(Integer AppointmentId) {
		Appointment ap ;//= appointlist.stream().filter(t -> t.getAppointmentid().equals(AppointmentId)).findFirst().get();
		
		/*use a try catch block cause the ,get() will throw an exception should it not find the appointment*/
		try{
		 ap = appointlist.stream().filter(t -> t.getAppointmentid().equals(AppointmentId)).findFirst().get();
		 
		 ap.setAppointmentActiveState(false);
		 apprepo.save(appointlist.remove(appointlist.indexOf(ap)));
		 /* we are not quite sure if we want to remove the appointment from the database so for now we are just 
			 * setting the activestate to false instead of deleting the appointment row from the database*/
		 //apprepo.delete(appointlist.remove(appointlist.indexOf(ap))); 
		 return true;
			
		}catch(NoSuchElementException e) {
			Optional<Appointment> ap1 = apprepo.findById(AppointmentId);
			if(ap1.isPresent()) {
				ap1.get().setAppointmentActiveState(false);
				apprepo.save((ap1.get()));	
				return true;
			}
			return "Appointment not found";
		}	
	}
	public final Object getByRefNumber(String refParam) {
		/*
		 * this method finds the Appointment object by AppointmentRef 
		 * */
		//Appointment ap ;//= appointlist.stream().filter(t -> t.getAppointmentRef().equals(refParam)).findFirst().get(); 
		try {
			/* */

			return appointlist.stream().filter(t -> t.getAppointmentRef().equals(refParam)).findFirst().get(); 
		}
		catch(NoSuchElementException e){
			return "Appointment not found";
			//return ap;
		}


		//return "Appointment not found Please check that you entered your ref properly";
	}

	public final Object getById(Integer id) {
		//Optional<Appointment> ap = apprepo.findById(id);
		 //if(ap == null) { return ResponseEntity.notFound().build(); }
		return apprepo.findById(id);
	}

	public final Object addApointment1(String timm, Integer userID, Integer BranchID) {

		/* 
		 * this method adds the appointment booking from the client to an array
		 * and uses the timeAvailable to check if the time slot is free to be booked
		 * we need the number in the que so we can send it o the user at redem time in branch
		 */

		Branch bran = br.getById(BranchID);
		User usr = ur.getByUserid(userID);
		if(usr == null) {
			/*
			 * checks if the user is in the database 
			 * cause only registered users can make an appointment
			 */
			bran = null;
			return "User not found";
		}
		if(bran == null) {
			/*
			 * checks if the Branch is in the database 
			 * cause you need a branch to be in the database to make an appointment
			 */
			usr = null;
			return "branch not found";
		}
		addAppointment2 aa2 = new addAppointment2();
		int[] arr = aa2.MYtime(timm);
		if(arr[0] == 0) {
			return "Please enter a time that is between 09h00 and 15h05";
		}
		LocalDateTime dateTim = LocalDate.now().atTime(arr[0],arr[1]);//ParamHours, ParamMinutes 
		LocalDateTime currentTim = LocalDateTime.now();
		if (dateTim.isBefore(currentTim) || (dateTim.getDayOfYear()!=currentTim.getDayOfYear())) {
		   /* 
		    * checks if the appointment is before the current time
			* that way you can't book for a time that is in the past
			* and can only make an appointment for today
			*/
			return "Please enter a time that is not in the past";
		}
		// = new TimeAvailable();//class to check if the time is available
		if(timeA.checkAppointmentTime(dateTim, getAllAppointment(),bran.getId()))
		{
			Integer positionHolder = qs.addToQue();//adds the user to the que in branch
			Appointment pop = new Appointment(rc.random(5),dateTim,qs.removeFromQue(positionHolder));
			pop.setBranch(bran);
			pop.setUser(usr);
			pop.setAppointmentActiveState(true);
			Appointment pop1 = this.createAppointment(pop);
			appointlist.add(pop1);
			pop = null;
			return pop1;
		}else {
			return "The appointment slots in this 30 minute period are fully booked";
		}
	}
	
	private final Appointment createAppointment(Appointment a) {
		/*saves appointment to branch*/
        return apprepo.save(a);
    }
	
	public final void addInBranchwithOutAppointment() {
		qs.addToQue();		
		//adds walk ins to branch que 
	}
	
	public final ArrayList<Integer> getQue(){
		return qs.getQue();//gets the que
	}

	public final ArrayList<Integer> toFont(String param){
		Object p = getByRefNumber(param);
		if(p instanceof java.lang.String) {
			return qs.getQue();
		}else {
			Appointment ap = (Appointment)p;//down cast from object to appointment 
			return qs.addTofrontOfque(ap.getQuePosition());
		}
	}
}