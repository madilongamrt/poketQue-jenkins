package com.project.PoketQue.interfaces;

import java.util.ArrayList;

public interface QueInterface {
	public int addToQue();
	public int removeFromQue(Integer lookfor);
	public ArrayList<Integer> getQue();
	public ArrayList<Integer> addTofrontOfque(Integer param);
}
