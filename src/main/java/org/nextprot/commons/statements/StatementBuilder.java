package org.nextprot.commons.statements;

import static org.nextprot.commons.statements.StatementField.ANNOTATION_CATEGORY;
import static org.nextprot.commons.statements.StatementField.ANNOT_CV_TERM_ACCESSION;
import static org.nextprot.commons.statements.StatementField.ANNOT_CV_TERM_NAME;
import static org.nextprot.commons.statements.StatementField.ANNOT_CV_TERM_TERMINOLOGY;
import static org.nextprot.commons.statements.StatementField.ANNOT_LOC_BEGIN_CANONICAL_REF;
import static org.nextprot.commons.statements.StatementField.ANNOT_LOC_END_CANONICAL_REF;
import static org.nextprot.commons.statements.StatementField.ANNOT_SOURCE_ACCESSION;
import static org.nextprot.commons.statements.StatementField.ANNOT_SOURCE_DATABASE;
import static org.nextprot.commons.statements.StatementField.DEBUG_NOTE;
import static org.nextprot.commons.statements.StatementField.ENTRY_ACCESSION;
import static org.nextprot.commons.statements.StatementField.ISOFORM_ACCESSION;
import static org.nextprot.commons.statements.StatementField.OBJECT_ANNOT_ENTRY_IDS;
import static org.nextprot.commons.statements.StatementField.OBJECT_ANNOT_ISO_IDS;
import static org.nextprot.commons.statements.StatementField.SUBJECT_ANNOT_ISO_IDS;
import static org.nextprot.commons.statements.StatementField.SUBJECT_ANNOT_ISO_UNAMES;
import static org.nextprot.commons.statements.StatementField.VARIANT_ORIGINAL_AMINO_ACID;
import static org.nextprot.commons.statements.StatementField.VARIANT_VARIATION_AMINO_ACID;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class StatementBuilder {

	private Map<String, String> keyValues = new HashMap<String, String>();

	public static StatementBuilder createNew() {
		return new StatementBuilder();
	}

	public StatementBuilder addField(StatementField statementField, String statementValue) {
		this.keyValues.put(statementField.name(), statementValue);
		return this;
	}

	public StatementBuilder addAnnotationSubject(Set<RawStatement> statements) {

		StringBuilder sbIsoIds = new StringBuilder();
		StringBuilder sbIsoUnames = new StringBuilder();

		
		Iterator<RawStatement> statementsIt = statements.iterator();
		
		while(statementsIt.hasNext()){
			RawStatement s = statementsIt.next();
			sbIsoIds.append(s.getIsoformAnnotationId());
			sbIsoUnames.append(s.getValue(StatementField.ANNOT_ISO_UNAME));
			if(statementsIt.hasNext()){
				sbIsoIds.append(",");
				sbIsoUnames.append(",");
			}
		}
		
		addField(SUBJECT_ANNOT_ISO_IDS, sbIsoIds.toString());
		addField(SUBJECT_ANNOT_ISO_UNAMES, sbIsoUnames.toString());

		/*addField(SUBJECT_ANNOT_ENTRY_IDS, statement.getEntryAnnotationId());
	    addField(SUBJECT_ANNOT_ENTRY_UNAMES, null);
		 */

		return this;
	}

	
	public StatementBuilder addAnnotationObject(RawStatement statement) {

		addField(OBJECT_ANNOT_ISO_IDS, statement.getIsoformAnnotationId());
	    addField(OBJECT_ANNOT_ENTRY_IDS, statement.getEntryAnnotationId());

		addField(OBJECT_ANNOT_ENTRY_IDS, null);
	    addField(OBJECT_ANNOT_ENTRY_IDS, null);


		return this;
	}


	public StatementBuilder addDebugNote(String note) {
		if(!this.keyValues.containsKey(StatementField.DEBUG_NOTE.name())){
			addField(DEBUG_NOTE, "");
		}
		
		String debugNote = this.keyValues.get(StatementField.DEBUG_NOTE.name());
		debugNote += note + "\n";
		
		addField(DEBUG_NOTE, debugNote);
		return this;
	}
	

	public RawStatement build() {
		RawStatement rs = new RawStatement(keyValues);

		rs.putValue(StatementField.ANNOT_ISO_ID, StatementUtil.getIsoAnnotationId(rs));
		rs.putValue(StatementField.ANNOT_ENTRY_ID, StatementUtil.getEntryAnnotationId(rs));

		return rs;
	}
	
	public StatementBuilder addMap(Map<String, String> map) {
		keyValues.putAll(map);
		return this;
	}
	
	
	public StatementBuilder addCompulsaryFields(String entryAccession, String isoformAccession, String annotationCategory) {
		addField(ENTRY_ACCESSION, entryAccession);
		addField(ISOFORM_ACCESSION, isoformAccession);
		addField(ANNOTATION_CATEGORY, annotationCategory);
		return this;
	}
	
	public StatementBuilder addCvTerm(String cvTermAccession, String cvTermName, String cvTerminology) {
		addField(ANNOT_CV_TERM_ACCESSION, cvTermAccession);
		addField(ANNOT_CV_TERM_NAME, cvTermName);
		addField(ANNOT_CV_TERM_TERMINOLOGY, cvTerminology);
		return this;
	}

	public StatementBuilder addSourceInfo(String sourceAccession, String sourceDatabase) {
		addField(ANNOT_SOURCE_ACCESSION, sourceAccession);
		addField(ANNOT_SOURCE_DATABASE, sourceDatabase);
		return this;
	}

	public StatementBuilder addVariantInfo(String firstPosition, String lastPosition, String variationOrigin, String variationVariation) {

		addField(ANNOTATION_CATEGORY, "variant");//What about mutagenesis
		
	    addField(ANNOT_LOC_BEGIN_CANONICAL_REF, firstPosition);
	    addField(ANNOT_LOC_END_CANONICAL_REF, lastPosition);

	    addField(VARIANT_ORIGINAL_AMINO_ACID, variationOrigin);
	    addField(VARIANT_VARIATION_AMINO_ACID, variationVariation);
	    
	    return this;
	    
	}

	public StatementBuilder addLocationFields(String locationBegin, String locationEnd) {
		addField(ANNOT_LOC_BEGIN_CANONICAL_REF, locationBegin);
		addField(ANNOT_LOC_END_CANONICAL_REF, locationEnd);
		return this;
	}

}
