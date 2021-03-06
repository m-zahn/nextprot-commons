package org.nextprot.commons.statements;

import java.util.Map;
import java.util.TreeMap;

/**
 * DO NOT ADD public setters on this class.
 * A StatementID is computed based on the fields when build() is invoked
 * 
 * @author Daniel Teixeira http://github.com/ddtxra
 *
 */
public class Statement extends TreeMap<String, String>{

	private static final long serialVersionUID = -4723168061980820149L;
	private boolean isProcessed = false;
	
	//Needed for serialization in Play?
	public Statement() {
		super();
	}

	// Keep the constructor package protected, so it enforces the use of the Builder
	Statement(Map<String, String> map) {
		super(new TreeMap<String, String>(map));
	}

	public String getValue(StatementField field) {
		return get(field.name());
	}

	public String getDebugInfo() {
		return get(StatementField.DEBUG_INFO.name());
	}

	String putValue(StatementField field, String value) {
		return put(field.name(), value);
	}
	
/*	void computeAndSetAnnotationIds(AnnotationType annotationType){
		putValue(StatementField.ANNOTATION_ID, StatementUtil.computeAndGetAnnotationId(this,annotationType));
	}*/

	public String getSubjectStatementIds() {
		return getValue(StatementField.SUBJECT_STATEMENT_IDS);
	}
	
	public String[] getSubjectStatementIdsArray() {
		String subjects = getValue(StatementField.SUBJECT_STATEMENT_IDS);
		if(subjects == null) return null;
		else return subjects.split(",");
	}
	
	public String getStatementId() {
		return this.getValue(StatementField.STATEMENT_ID);
	}
	
	public String getAnnotationId() {
		return this.getValue(StatementField.ANNOTATION_ID);
	}
	
	public String getObjectStatementId() {
		return getValue(StatementField.OBJECT_STATEMENT_IDS);
	}
	
	public boolean hasModifiedSubject() {
		return (getValue(StatementField.SUBJECT_STATEMENT_IDS) != null);
	}

	public boolean isProcessed() {
		return isProcessed;
	}

	public void processed() {
		this.isProcessed = true;
	}

	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for (String s : this.keySet()) {
			sb.append("\t\"" + s + "\": \"" + this.get(s).replace("\"", "''") + "\",\n");
		}
		sb.append("}");
		return sb.toString();
	}

}
