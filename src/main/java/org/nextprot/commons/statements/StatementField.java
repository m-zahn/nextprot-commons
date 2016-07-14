package org.nextprot.commons.statements;

public enum StatementField {

	// According with https://calipho.isb-sib.ch/wiki/display/cal/Raw+statements+specifications

	NEXTPROT_ACCESSION, //Used for raw statements
	
	ENTRY_ACCESSION(true, true), //Used for mapped statement
	GENE_NAME,//TODO should be a list of gene names

	LOCATION_BEGIN_MASTER (false, true),
	LOCATION_END_MASTER (false, true),
	
	ISOFORM_ACCESSION(true, false),

	
	// SUBJECTS ///////////////////////////////////////////////////////
	
	SUBJECT_ANNOT_ISO_IDS(true, false),
	SUBJECT_ANNOT_ENTRY_IDS(false, true),

	SUBJECT_ANNOT_ISO_UNAMES,
	SUBJECT_ANNOT_ENTRY_UNAMES,
	////////////////////////////////////////////////////////////////////

	ANNOTATION_CATEGORY(true, true),
	ANNOT_DESCRIPTION(true, true),

	ANNOT_ISO_ID, 
	ANNOT_ISO_UNAME, 

	ANNOT_ENTRY_ID, 
	ANNOT_ENTRY_UNAME, 

	STATEMENT_QUALITY, 

	ANNOT_CV_TERM_TERMINOLOGY(true, true),
	ANNOT_CV_TERM_ACCESSION(true, true),
	ANNOT_CV_TERM_NAME,
	
	ANNOT_LOC_BEGIN_CANONICAL_REF (true, false),
	ANNOT_LOC_END_CANONICAL_REF(true, false),
	ANNOT_LOC_BEGIN_GENOMIC_REF,
	ANNOT_LOC_END_GENOMIC_REF,

	VARIANT_ORIGINAL_AMINO_ACID(true, true),
	VARIANT_VARIATION_AMINO_ACID(true, true),

	VARIANT_ORIGINAL_GENOMIC,
	VARIANT_VARIATION_GENOMIC,
	
	
	BIOLOGICAL_OBJECT_TYPE(true, true),
	BIOLOGICAL_OBJECT_ACCESSION(true, true),
	BIOLOGICAL_OBJECT_DATABASE(true, true),

	// OBJECT ANNOTATION ///////////////////////////////////////////////////////////
	OBJECT_ANNOT_ISO_IDS(true, false),
	OBJECT_ANNOT_ENTRY_IDS(false, true),

	OBJECT_ANNOT_ISO_UNAMES,
	OBJECT_ANNOT_ENTRY_UNAMES,
	///////////////////////////////////////////////////////////////////////////////

	ANNOT_SOURCE_DATABASE,
	ANNOT_SOURCE_ACCESSION,
	
	//Publications
	REFERENCE_PUBMED,

	//Experimental context fields
	EXP_CONTEXT_ECO_DETECT_METHOD,
	EXP_CONTEXT_PROPERTY_INTENSITY,
	EXP_CTX_PRPTY_PROTEIN_ORIGIN, //CHECK THIS	

	

	DEBUG_NOTE //Used for errors, warnings, debug information
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
