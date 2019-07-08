package com.project.PoketQue.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * User table with getters and setters
 */

@Entity
@Table(name = "user" , uniqueConstraints={@UniqueConstraint(columnNames={"useremail"})})
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userid;

	@Column(name="name", nullable=false, length=150)
	@NotEmpty
	private String name;
 

	@Column(name="useremail",nullable=false, length=50)
	@NotEmpty
	private String useremail;

	/**
	 * Default constructor
	 */
	public User() {}

	/**
	 * Getters
	 */
    public String getName() {return name;}

    public long getId() {return userid;}
	
    public String getEmail() {return useremail;}

	/**
	 * Setters
	 */
	public void setName(String name) {
		if(name.isEmpty()) { this.name = "default"; }
		this.name = name;

	}

	public void setId(Integer id) { this.userid = id; }

	public String getUseremail() {return useremail;}

	/* 
		need to catch duplicates before adding to database
	*/
	public void setUseremail(String useremail) {
		if(useremail.isEmpty()) { this.useremail = "default"; }
		this.useremail = useremail;
	
	}
}