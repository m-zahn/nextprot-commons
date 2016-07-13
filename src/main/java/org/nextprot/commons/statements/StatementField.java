package org.nextprot.commons.statements;

public enum StatementField {

	// According with https://calipho.isb-sib.ch/wiki/display/cal/Raw+statements+specifications

	NEXTPROT_ACCESSION, //Used for raw statements
	
	ENTRY_ACCESSION(true), //Used for mapped statement
	GENE_NAME,//TODO should be a list of gene names
	
	ISOFORM_ACCESSION(true),

	BIOLOGICAL_SUBJECT_ANNOT_HASH(true),
	BIOLOGICAL_SUBJECT_ANNOT_NAME,

	ANNOTATION_CATEGORY(true),
	ANNOT_DESCRIPTION(true),
	ANNOT_NAME, 
	ANNOT_HASH, 

	STATEMENT_QUALITY, 

	ANNOT_CV_TERM_TERMINOLOGY(true),
	ANNOT_CV_TERM_ACCESSION(true),
	ANNOT_CV_TERM_NAME,
	
	ANNOT_LOC_BEGIN_CANONICAL_REF (true),
	ANNOT_LOC_END_CANONICAL_REF(true),
	ANNOT_LOC_BEGIN_GENOMIC_REF,
	ANNOT_LOC_END_GENOMIC_REF,

	VARIANT_ORIGINAL_AMINO_ACID(true),
	VARIANT_VARIATION_AMINO_ACID(true),

	VARIANT_ORIGINAL_GENOMIC(true),
	VARIANT_VARIATION_GENOMIC(true),
	

	BIOLOGICAL_OBJECT_TYPE(true),
	BIOLOGICAL_OBJECT_ACCESSION(true),
	BIOLOGICAL_OBJECT_DATABASE(true),
	BIOLOGICAL_OBJECT_ANNOT_HASH(true),


	ANNOT_SOURCE_DATABASE,
	ANNOT_SOURCE_ACCESSION,
	
	//Publications
	REFERENCE_PUBMED,

	//Experimental context fields
	EXP_CONTEXT_ECO_DETECT_METHOD,
	EXP_CONTEXT_PROPERTY_INTENSITY,
	EXP_CTX_PRPTY_PROTEIN_ORIGIN, //CHECK THIS	

	

	DEBUG_NOTE //Used for errors
	;
	

	private boolean unicity = false;
	StatementField(boolean unicity){
		this.unicity = unicity;
	}
	
	StatementField(){
		this.unicity = false;
	}

	public boolean isUnicity() {
		return unicity;
	}

	public void setUnicity(boolean unicity) {
		this.unicity = unicity;
	}
}
