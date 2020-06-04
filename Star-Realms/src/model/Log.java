package model;

import java.util.ArrayList;
import java.util.Stack;

import model.comp.Graphic;

public class Log implements Graphic{
	private final ArrayList<String> logs;
	private int update;
	
	public Log() {
		logs = new ArrayList<String>();
		update = 0;
	}
	
	public String peek() {
		return logs.get(logs.size()-1);
	}
	
	public void add(String logMessage) {
		logs.add(logMessage);
		update ++;
	}

	@Override
	public void paint() {
		while(update!=0) {
			System.out.println(logs.get(logs.size()-update));
			update --;
		}
		
	}
}
