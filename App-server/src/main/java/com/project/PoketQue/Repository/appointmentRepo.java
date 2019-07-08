package com.project.PoketQue.Repository;

import com.project.PoketQue.Models.Appointment;

import java.util.Optional;

//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface appointmentRepo  extends CrudRepository<Appointment, Integer> {
	Optional<Appointment> findByAppointmentref(String ref);
}