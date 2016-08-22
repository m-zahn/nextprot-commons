package org.nextprot.commons.statements;

public class TargetIsoformStatementPosition implements Comparable<TargetIsoformStatementPosition>{

	private String isoformName;
	private String specificity;
	private Integer begin;
	private Integer end;
	private String name;
	
	public TargetIsoformStatementPosition() {
		
	}

	public TargetIsoformStatementPosition(String isoformName, String specificity) {
		this.isoformName = isoformName;
		this.specificity = specificity;
	}

	public TargetIsoformStatementPosition(String isoformName, Integer begin, Integer end, String specificity, String name) {
		this.isoformName = isoformName;
		this.specificity = specificity;
		this.begin = begin;
		this.end = end;
		this.name = name;
	}
	
	public String getIsoformName() {
		return isoformName;
	}

	public String getSpecificity() {
		return specificity;
	}
	
	public Integer getBegin() {
		return begin;
	}
	public Integer getEnd() {
		return end;
	}

	@Override
	public int compareTo(TargetIsoformStatementPosition o) {
		return isoformName.compareTo(o.isoformName);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
