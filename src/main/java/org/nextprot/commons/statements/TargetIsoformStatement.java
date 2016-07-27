package org.nextprot.commons.statements;

import java.util.Map;
import java.util.TreeMap;


public class TargetIsoformStatement extends TreeMap<String, TargetIsoformStatementPosition>{
	
	private static final long serialVersionUID = 1L;
	
	public TargetIsoformStatement(){
		super();
	}
	
	public TargetIsoformStatement(Map<String, TargetIsoformStatementPosition> map){
		super(map);
	}

	public void putIsoformPosition(String isoformName, Integer begin, Integer end){
		this.put(isoformName, new TargetIsoformStatementPosition(begin, end));
	}

	public void resetPositions(){
		for(TargetIsoformStatementPosition tisp : this.values()){
			tisp.setBegin(null);
			tisp.setEnd(null);
		}
	}

}
