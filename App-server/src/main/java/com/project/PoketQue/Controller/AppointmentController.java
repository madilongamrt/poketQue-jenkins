package com.project.PoketQue.Controller;

import java.util.ArrayList;
import javax.validation.Valid;
import com.project.PoketQue.service.appointmentService.AppointmentService;
import com.project.PoketQue.service.statistic.statisticservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.PoketQue.interfaces.AppointmentParamCheckerInterface;


@CrossOrigin("*")
@RestController
@RequestMapping("api/appointment")
public class AppointmentController {
	@Autowired
	private AppointmentParamCheckerInterface apc;
	@Autowired
	private AppointmentService apps;
	@Autowired
	private statisticservice statsService;
	/*
	 *
	 * the following methods should be in the que controller
	 * but we couldn't configure how to keep the que position with
	 * probably cause we are not passing the right instance of the que class
	 *
	 **/
	@PostMapping("/add")
	private final ResponseEntity<ArrayList<Integer>> addToQue() {
		//@Valid @RequestParam("BranchId") Integer BranchId
		apps.addInBranchwithOutAppointment();
		statsService.addStat(1);//BranchId);
		return ResponseEntity.ok().body(apps.getQue());
	}

	@GetMapping("/que")
	private final ArrayList<Integer> getQue(){
		return apps.getQue();
	}

	@PostMapping("/appointment/move/")
	private final ArrayList<Integer> addToFrontOfQue(@Valid @RequestParam("ref") String ref
			,@Valid @RequestParam("BranchId") Integer BranchId) {
				statsService.addStat(BranchId);
		return apps.toFont(ref) ;
	}
	@DeleteMapping("/RemoveAppointments/")
	private final Object RemoveAppointment(@Valid @RequestParam("ParamAppointmentId") Integer Id) {
		return apps.deleteAppointment(Id);
	}
	@PostMapping("/addAppointments/")
	private final ResponseEntity<Object> CreateAppointment( @Valid @RequestParam("ParamBookingTime") String ParamBookingTime
															,@Valid @RequestParam("UserId") Integer UserId
															,@Valid @RequestParam("BranchId") Integer BranchId) {
		if(!apc.isRightLenght(ParamBookingTime, 4)|| !apc.isdigit(ParamBookingTime)||ParamBookingTime.isEmpty()) {
			return ResponseEntity.ok().body("please ensure that the time entered is four digits");
		}
		else {
			return ResponseEntity.ok().body(apps.addApointment1(ParamBookingTime,UserId,BranchId));
		}
	}
	@GetMapping("/FindAppointments/")
	private final Object getQueposition(@RequestParam String refParam) {
		if(refParam.length() != 5) {//check lenght of ref number and remove hard coded values from code
			return "Please ensure that the code you entered is "+ "5" +" characters in length";
		}else {
			return apps.getByRefNumber(refParam);	
		}
	}
	@GetMapping("/FindAllAppointments/")
	private final Object getAppointments() {
		return apps.getAllAppointment();
	}
}