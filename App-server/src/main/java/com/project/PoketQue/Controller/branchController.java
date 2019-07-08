package com.project.PoketQue.Controller;

import com.project.PoketQue.service.branchService.branchServices;
import com.project.PoketQue.service.statistic.statisticservice;
import com.project.PoketQue.Models.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/branch")
public class BranchController {

    @Autowired
    public branchServices branchserv;

    @Autowired
    public statisticservice stats;
    // Add/save a branch
    @PostMapping("/addBranch")
    public Object createBranch(@Valid @RequestBody Branch br) {
        return branchserv.save(br);
    }

    // Get a branch by Id
    @GetMapping("/getBranch/")
    public ResponseEntity<Object> getBranchById(@PathVariable(value="branchid") Integer branchid) {
        Object branch = branchserv.getById(branchid);

        if(branch == null) {
            return ResponseEntity.notFound().build(); }
        else {
            return ResponseEntity.ok().body(branch); }
    }

    @PostMapping("/retrieveBranch/")
    private final ArrayList getBranchStat(@Valid @RequestParam(value="branchid") Integer branchid) {
        return branchserv.getBranch();
    }
    @PostMapping("/retrieveBranchAddresses/")
    private final List<Object> getBranchbyAdresses() {
        return stats.getBranches();
    }

}