package org.nextprot.commons.statements;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.nextprot.commons.algo.MD5Algo;

public class RawStatement extends TreeMap<String, String>{
	
	private static final long serialVersionUID = -4723168061980820149L;
	
	//Needed for serialization in Play?
	public RawStatement() {
		super();
	}

	// Keep the constructor package protected, so it enforces the use of the Builder
	RawStatement(Map<String, String> map) {
		super(new TreeMap<String, String>(map));
	}

	public String getValue(StatementField field) {
		return get(field.name());
	}

	String putValue(StatementField field, String value) {
		return put(field.name(), value);
	}
	
	void computeAndSetAnnotationIds(){
		putValue(StatementField.ANNOT_ISO_ID, computeAndGetAnnotationId(this,AnnotationType.ISOFORM));
		putValue(StatementField.ANNOT_ENTRY_ID, computeAndGetAnnotationId(this,AnnotationType.ENTRY));
	}

	public String getSubjectStatementIds() {
		return getValue(StatementField.SUBJECT_STATEMENT_IDS);
	}
	
	public String getStatementId() {
		return getValue(StatementField.STATEMENT_ID);
	}
	
	public String getObjectStatementId() {
		return getValue(StatementField.OBJECT_STATEMENT_IDS);
	}
	
	public boolean hasModifiedSubject() {
		return (getValue(StatementField.SUBJECT_STATEMENT_IDS) != null);
	}
	
	
	private static String computeAndGetAnnotationId(RawStatement statement, AnnotationType type) {

		// Filter fields which are used to compute unicity
		Set<StatementField> unicityFields = new TreeSet<StatementField>();
		StatementField[] fields = StatementField.values();
		for (StatementField field : fields) {
			// According with
			// https://calipho.isb-sib.ch/wiki/display/cal/Raw+statements+specifications

			if (type.equals(AnnotationType.ISOFORM)) {
				if (field.isIsoUnicity()) {
					unicityFields.add(field);
				}
			} else if (type.equals(AnnotationType.ENTRY)) {
				if (field.isEntryUnicity()) {
					unicityFields.add(field);
				}
			}
		}

		TreeSet<String> contentItems = new TreeSet<String>();
		for (StatementField unicityField : unicityFields) {
			String value = statement.getValue(unicityField);
			if (value != null) {
				contentItems.add(value);
			}
		}

		StringBuffer payload = new StringBuffer();
		Iterator<String> icItemsIterator = contentItems.iterator();
		while (icItemsIterator.hasNext()) {
			payload.append(icItemsIterator.next());
		}

		return MD5Algo.computeMD5(payload.toString());

	}

}
