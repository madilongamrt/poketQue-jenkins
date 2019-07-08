package com.project.PoketQue.Controller;

import javax.validation.Valid;

import com.project.PoketQue.service.statistic.statisticservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/statistic")
@CrossOrigin("*")
public class statisticController {
	@Autowired
    statisticservice ss;


	@PostMapping("/popularTimes/")
    private final Object getBranchStat(@Valid @RequestParam(value="branchid") Integer branchid) {
        return ss.getStatsById(branchid);
    }
}