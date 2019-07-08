package com.project.PoketQue.service.branchService;

import com.project.PoketQue.Repository.branchRepo;
import com.project.PoketQue.Models.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class branchServices {

    @Autowired
    private branchRepo branchrepo;

    /**
     * Save branch and return if it already exists
     */
    public Object save(Branch branch) {
        Optional<Branch> br = Optional.ofNullable(branchrepo.getByBranchAddress(branch.getBranchAddress()));
        if(br.isPresent()){
            return br.get();
        }
        return branchrepo.save(branch);
    }
    public Object getById(Integer brancid){
    	Optional<Branch> br = branchrepo.findById(brancid);
    	if(br.isPresent()) {
    		return br.get();
    	}
        return "branch not found";
    }


    public ArrayList getBranch() {
        ArrayList op = branchrepo.getBranches();
        if(op.size() != 0) {
            return op;
        }else{
            return null;
        }

    }
}