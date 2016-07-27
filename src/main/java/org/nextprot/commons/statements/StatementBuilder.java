package org.nextprot.commons.statements;

import static org.nextprot.commons.statements.StatementField.*;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.nextprot.commons.statements.constants.AnnotationType;
import org.nextprot.commons.utils.StringUtils;

public class StatementBuilder {

	private Map<String, String> keyValues = new TreeMap<>();

	public static StatementBuilder createNew() {
		StatementBuilder sb = new StatementBuilder();
		return sb;
	}

	public StatementBuilder addField(StatementField statementField, String statementValue) {
		this.keyValues.put(statementField.name(), statementValue);
		return this;
	}

	public StatementBuilder addSubjects(Collection<Statement> statements) {

		Set<String> sortedStatementIds = new TreeSet<String>();

		Iterator<Statement> statementsIt = statements.iterator();

		while (statementsIt.hasNext()) {
			Statement s = statementsIt.next();
			sortedStatementIds.add(s.getStatementId());
		}
		
		String subjectStatemendIds = StringUtils.mkString(sortedStatementIds, ",");

		addField(SUBJECT_STATEMENT_IDS, subjectStatemendIds);

		return this;
	}

	public StatementBuilder addObject(Statement statement) {
		addField(OBJECT_STATEMENT_IDS, statement.getStatementId());
		return this;
	}

	public StatementBuilder addDebugNote(String note) {
		if (note != null && note.length() > 0) {

			if (!this.keyValues.containsKey(StatementField.DEBUG_NOTE.name())) {
				addField(DEBUG_NOTE, "");
			}

			String debugNote = this.keyValues.get(StatementField.DEBUG_NOTE.name());
			debugNote += note + "\n";

			addField(DEBUG_NOTE, debugNote);
		}
		return this;
	}

	public Statement build() {
		Statement rs = new Statement(keyValues);
		rs.putValue(StatementField.STATEMENT_ID, StatementUtil.computeAndGetAnnotationId(rs, AnnotationType.STATEMENT));
		return rs;
	}

	public StatementBuilder addMap(Map<String, String> map) {
		keyValues.putAll(map);
		return this;
	}

	public StatementBuilder addCompulsoryFields(String entryAccession, String isoformAccession, String annotationCategory) {
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
		addField(SOURCE, sourceDatabase);
		return this;
	}

	public StatementBuilder addVariantInfo(String annotationCategory, String firstPosition, String lastPosition, String variationOrigin, String variationVariation) {

		if(annotationCategory == null || !(annotationCategory.equals("variant") || annotationCategory.equals("mutagenesis"))) {
			throw new RuntimeException("annotation category " + annotationCategory + " is not allowed for variant");
		}
		addField(ANNOTATION_CATEGORY, annotationCategory);
		
		addField(LOCATION_BEGIN, firstPosition);
		addField(LOCATION_END, lastPosition);

		addField(VARIANT_ORIGINAL_AMINO_ACID, variationOrigin);
		addField(VARIANT_VARIATION_AMINO_ACID, variationVariation);

		return this;

	}

	public StatementBuilder addLocationFields(String locationBegin, String locationEnd) {
		addField(LOCATION_BEGIN, locationBegin);
		addField(LOCATION_END, locationEnd);
		return this;
	}

}
