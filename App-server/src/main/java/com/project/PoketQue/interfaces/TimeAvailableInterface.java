package com.project.PoketQue.interfaces;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.project.PoketQue.Models.Appointment;

public interface TimeAvailableInterface {
	public boolean checkAppointmentTime(LocalDateTime timecheck, ArrayList<Appointment> obj, int branchid);
}