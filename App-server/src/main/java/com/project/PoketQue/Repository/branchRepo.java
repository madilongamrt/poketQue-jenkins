package com.project.PoketQue.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.project.PoketQue.Models.Branch;

import java.util.ArrayList;
import java.util.List;

public interface branchRepo extends CrudRepository<Branch, Integer> {

	/**
	 * get a branch from the database id
	 */
	public Branch getById(Integer id);

	/**
	 * get a branch from the database by address
	 */
	public Branch getByBranchAddress(String branchAddress);

	@Query(value="select branch_address from branch",nativeQuery=true)
	public List<Object> getByBranchAddress();

	@Query(value="select * from branch",nativeQuery=true)
	public ArrayList<Branch> getBranches();

}
