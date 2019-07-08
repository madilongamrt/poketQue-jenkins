package com.project.PoketQue.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Value;

/**
 * Branch table with getters and setters methods
 */

@Entity
@Table(name = "branch", uniqueConstraints={@UniqueConstraint(columnNames={"branchAddress"})})
public class Branch {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name="branchName", nullable=false, length=50)
	@NotEmpty
	private String branchName;
	@Column(name="branchAddress",nullable=false, length=90)
	@NotEmpty
	private String branchAddress;

	@Column(name="openingTime", nullable=false, length=5)
	private String openingTime;

	@Column(name="closingTime",nullable=false, length=5)
	private String closingTime;
	
	@Value("000")
	@Column(name="phone_number", length=20)
	private String phoneNumber;
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * 	Default constructor
	 */
	Branch() {}
	
	/**
	 * 	Getters
	 */
	
	public String getBranchName() { return branchName; }

	public String getBranchAddress() { return branchAddress; }

	public String getOpeningTime() { return openingTime; }

	public String getClosingTime() { return closingTime; }

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 	Setters
	 */
	public void setBranchName(String branchName) {

		if(branchName.isEmpty()){ this.branchName = "default"; }
		this.branchName = branchName;
	}

	public void setBranchAddress(String branchAddress) {
		if(branchAddress.isEmpty()){ this.branchAddress = "default"; }
		this.branchAddress = branchAddress;
	}

	public void setOpeningTime(String openingTime) { this.openingTime = openingTime; }

	public void setClosingTime(String closingTime) { this.closingTime = closingTime; }

	public Integer getId() {
		return id;
	}


}