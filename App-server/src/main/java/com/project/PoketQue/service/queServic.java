package com.project.PoketQue.service;

import java.util.ArrayList;

import com.project.PoketQue.interfaces.QueInterface;
import org.springframework.stereotype.Component;

@Component
public final class queServic implements QueInterface {
	int incrementor = 1;
	private ArrayList<Integer> queSystem = new ArrayList<Integer>();
	
	@Override
	public int addToQue() {
		if(queSystem.add(incrementor)) {
			return incrementor++;
		}
		return -1;
	}
	@Override
	public int removeFromQue(Integer lookfor) {
		if(queSystem.contains(lookfor)){
			queSystem.remove(lookfor);
			  return lookfor;
        }
	    return -1;
	}
	@Override
	public ArrayList<Integer> getQue(){
		return queSystem;
	}
	@Override
	public ArrayList<Integer> addTofrontOfque(Integer param){
		queSystem.add(0, param);
		return queSystem;
	}
}