package org.nextprot.commons.statements;

import java.util.HashMap;
import java.util.Map;
import org.nextprot.commons.statements.StatementField;
import static org.nextprot.commons.statements.StatementField.*;

public class StatementBuilder {

	private Map<String, String> keyValues = new HashMap<String, String>();

	public static StatementBuilder createNew() {
		return new StatementBuilder();
	}

	public StatementBuilder addField(StatementField statementField, String statementValue) {
		this.keyValues.put(statementField.name(), statementValue);
		return this;
	}

	public RawStatement build() {
		RawStatement rs = new RawStatement(keyValues);
		String hash = StatementUtil.getAnnotationHash(rs);
		rs.putValue(StatementField.ANNOT_HASH, hash);
		return rs;
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

	public StatementBuilder addLocationFields(String locationBegin, String locationEnd) {
		addField(ANNOT_LOC_BEGIN_CANONICAL_REF, locationBegin);
		addField(ANNOT_LOC_END_CANONICAL_REF, locationEnd);
		return this;
	}

}
