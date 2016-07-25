package org.nextprot.commons.statements.constants;

public enum NextProtSources {

	BioEditor ("neXtProt", "http://kant.isb-sib.ch:9000/bioeditor");
	

	private String sourceName;
	private String statementsUrl;
	
	NextProtSources(String sourceName, String statementsUrl){
		this.sourceName = sourceName;
		this.statementsUrl = statementsUrl;
	}
	
	
	
	public String getSourceName() {
		return sourceName;
	}

	public String getStatementsUrl() {
		return statementsUrl;
	}

}
