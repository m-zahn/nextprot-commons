package org.nextprot.commons.statements;

public enum StatementField {

	STATEMENT_ID, 
	
	// According with https://calipho.isb-sib.ch/wiki/display/cal/Raw+statements+specifications
	NEXTPROT_ACCESSION, //Used for raw statements
	
	ENTRY_ACCESSION(true, true), //Used for mapped statement
	GENE_NAME,//TODO should be a list of gene names

	LOCATION_BEGIN_MASTER (false, true),
	LOCATION_END_MASTER (false, true),
	
	LOCATION_BEGIN (true, false),
	LOCATION_END (true, false),

	
	ISOFORM_ACCESSION(true, false),

	
	// SUBJECTS ///////////////////////////////////////////////////////
	SUBJECT_STATEMENT_IDS(true, true),
	
	SUBJECT_ANNOTATION_IDS,

	ANNOTATION_SUBJECT_SPECIES,
	ANNOTATION_OBJECT_SPECIES,
	////////////////////////////////////////////////////////////////////

	ANNOTATION_CATEGORY(true, true),
	ANNOT_DESCRIPTION(true, true),

	ISOFORM_CANONICAL,

	TARGET_ISOFORMS,

	ANNOTATION_ID, 
	ANNOTATION_NAME, 

	IS_NEGATIVE(true, true), 

	EVIDENCE_QUALITY, 
	EVIDENCE_INTENSITY,
	EVIDENCE_NOTE,
	EVIDENCE_STATEMENT_REF,
	EVIDENCE_CODE,
	EVIDENCE_PROPERTIES,

	ANNOT_CV_TERM_TERMINOLOGY(true, true),
	ANNOT_CV_TERM_ACCESSION(true, true),
	ANNOT_CV_TERM_NAME,
	

	VARIANT_ORIGINAL_AMINO_ACID(true, true),
	VARIANT_VARIATION_AMINO_ACID(true, true),

	BIOLOGICAL_OBJECT_TYPE(true, true),
	BIOLOGICAL_OBJECT_ACCESSION(true, true),
	BIOLOGICAL_OBJECT_DATABASE(true, true),

	// OBJECT ANNOTATION ///////////////////////////////////////////////////////////
	OBJECT_STATEMENT_IDS(true, true),
	OBJECT_ANNOTATION_IDS,

	OBJECT_ANNOT_ISO_UNAMES,
	OBJECT_ANNOT_ENTRY_UNAMES,
	///////////////////////////////////////////////////////////////////////////////

	SOURCE,
	ANNOT_SOURCE_ACCESSION,
	
	//Experimental context fields
	EXP_CONTEXT_ECO_DETECT_METHOD,
	EXP_CONTEXT_ECO_MUTATION,
	EXP_CONTEXT_ECO_ISS,
	
	//Publications
	REFERENCE_PUBMED,
	REFERENCE_CROSS_REF,
	REFERENCE_CROSS_DOI,

	
	//EVIDENCE
	ASSIGNED_BY,
	ASSIGMENT_METHOD,
	RESOURCE_TYPE,
	EVIDENCE_RESOURCE_ID, //This is the ID of the resource in the database database only in the mapped statements
	
	DEBUG_NOTE, //Used for errors, warnings, debug information
	
	
	RAW_STATEMENT_ID //Keep a reference to the Raw statement
	;
	

	private boolean isoUnicity = false;
	private boolean entryUnicity = false;

	StatementField(boolean isoUnicity, boolean entryUnicity){
		this.isoUnicity = isoUnicity;
		this.entryUnicity = entryUnicity;
	}
	
	StatementField(){
		this.isoUnicity = false;
		this.entryUnicity = false;

	}

	public boolean isIsoUnicity() {
		return isoUnicity;
	}

	public void setIsoUnicity(boolean isoUnicity) {
		this.isoUnicity = isoUnicity;
	}
	
	public boolean isEntryUnicity() {
		return entryUnicity;
	}

	public void setEntryUnicity(boolean entryUnicity) {
		this.entryUnicity = entryUnicity;
	}
}
