package com.project.PoketQue.Models;

import java.time.LocalDateTime;
import javax.persistence.*;

/**
 * Appointment table class with getters and setters method.
 *  Has two foreign keys from the 'User' and 'Branch' table
 */

@Entity
@Table(name = "appointment", uniqueConstraints={@UniqueConstraint(columnNames={"appointmentRef"})})
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name="currenttime")
	private LocalDateTime currentTime;

	@Column(name="appointmenttime")
	private LocalDateTime appointmentTime;

	@Column(name="appointmentexpirytime")
	private LocalDateTime appointmentExpiryTime;

	@Column(name="appointmentactivetime")
	private LocalDateTime appointmentActiveTime;
	
	@Column(name="appointmentactivestate")
	private boolean AppointmentActiveState;

	@Column(name="appointmentref", nullable=false)
	private String appointmentref;

	@Column(name="Queposition", nullable=false , length = 4)
	private int QuePosition;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinColumn( name = "fk_branch_id",foreignKey=@ForeignKey(name="branchid"))
	private Branch branch;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinColumn( name = "fk_User_id", foreignKey=@ForeignKey(name="userid"))
	private User user;



	/**
	 * Default constructor
	 */
	public Appointment() {}

	/**
	 * Parameterized constructor
	 */
	public Appointment(String AppointmentRefParam, LocalDateTime AppointmentTime, int QuePosition) {
		this.id = 0;
		this.appointmentTime = AppointmentTime;
		this.appointmentActiveTime = this.appointmentTime.minusMinutes(6);
		this.appointmentExpiryTime = this.appointmentTime.plusMinutes(15);
		this.QuePosition = QuePosition;
		this.currentTime = LocalDateTime.now();
		this.appointmentref = AppointmentRefParam;
	}

	/**
	 * Getters
	 */
	public Integer getAppointmentid() {
		return id;
	}

	public LocalDateTime getCurrentTime() {
		return currentTime;
	}

	public LocalDateTime getAppointmentTime() {
		return appointmentTime;
	}

	public LocalDateTime getAppointmentExpiryTime() {
		return appointmentExpiryTime;
	}

	public LocalDateTime getAppointmentActiveTime() {
		return appointmentActiveTime;
	}

	public String getAppointmentRef() {
		return appointmentref;
	}

	public boolean isAppointmentActiveState(){
		return AppointmentActiveState;
	}

	public int getQuePosition() {
		return QuePosition;
	}

	public User getUser() {
		return user;
	}

	public Branch getBranch() {
		return branch;
	}

	/**
	 * Setters
	 */
	public void setAppointmentid(Integer appointmentid) {
		id = appointmentid;
	}

	public void setCurrentTime(LocalDateTime currentTime) {
		this.currentTime = currentTime;
	}

	public void setAppointmentActiveState(boolean AppointmentActiveState){
		this.AppointmentActiveState = AppointmentActiveState;
	}

	public void setAppointmentTime(LocalDateTime appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public void setAppointmentExpiryTime(int time) {
		this.appointmentExpiryTime = this.appointmentTime.plusMinutes(time);//25minutes
	}

	public void setAppointmentActiveTime(int time) {
		this.appointmentActiveTime = this.appointmentTime.minusMinutes(time);//6minutes before
	}

	public void setAppointmentRef(String appointmentRef) {
		this.appointmentref = appointmentRef;
	}

	public void setQuePosition(int quePosition) {
		QuePosition = quePosition;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public void setUser(User user) {
		this.user = user;
	}

}