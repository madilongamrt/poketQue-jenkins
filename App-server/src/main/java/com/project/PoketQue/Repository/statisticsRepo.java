package com.project.PoketQue.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.project.PoketQue.Models.Statistics;

public interface statisticsRepo extends CrudRepository<Statistics, Integer>{
	@Query(value="call stats_Proc(?1)",nativeQuery=true)
	public List<Object> getStatsById(Integer BranchId);
}
