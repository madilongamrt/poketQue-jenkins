package com.project.PoketQue.Repository;
import com.project.PoketQue.Models.User;
import org.springframework.data.repository.CrudRepository;

public interface userRepo extends CrudRepository<User,Integer> {

	/**
	 *  Read user from the database by id
	 */
	public User getByUserid(Integer id);

	/**
	 * Read user from the database by email
	 */
	public User getByUseremail(String useremail);
}
