package com.project.PoketQue.service.statistic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.PoketQue.Models.Statistics;
import com.project.PoketQue.Repository.statisticsRepo;
import com.project.PoketQue.Repository.branchRepo;

import javax.transaction.Transactional;

@Transactional
@Service("StatsService")
public class statisticservice {

	@Autowired
	private statisticsRepo sr;

	@Autowired
	private branchRepo br;
	
	/* *
	 * Method to time stamp clients that have and that don't have an appointment
	 * when they walk into the branch   
	 * */
	public boolean addStat(Integer branchId) {
		Statistics st = new Statistics(br.getById(branchId));
		if(st.getBranch()!= null) {
			sr.save(st);
			st = null;
			return true;
		}
		st = null;
		return false;}

	@Autowired
	public  statisticservice(statisticsRepo stats){
		this.sr = stats;
	}


	public List<Object> getStatsById(Integer BranchId) {
		List<Object> op = sr.getStatsById(BranchId);
		if(op.size() != 0 ) {
			return op;
		}else{
			return null;


		}}
/*
   Populating branch address for the dummy queuing system.
 */
public List<Object> getBranches(){
	List<Object> branchAdd = (List<Object>) br.getByBranchAddress();
	if (!branchAdd.isEmpty()){
		return branchAdd;
	}else {

		return  null;
	}

}



}