package org.nextprot.commons.statements;

public class TargetIsoformStatementPosition {

	private Integer begin;
	private Integer end;
	
	public TargetIsoformStatementPosition(){
	}
	
	public TargetIsoformStatementPosition(Integer begin, Integer end){
		this.begin = begin;
		this.end = end;
	}

	public Integer getBegin() {
		return begin;
	}
	public void setBegin(Integer begin) {
		this.begin = begin;
	}
	public Integer getEnd() {
		return end;
	}
	public void setEnd(Integer end) {
		this.end = end;
	}

}
