package org.nextprot.commons.statements;

import java.util.Map;
import java.util.TreeMap;

public class RawStatement extends TreeMap<String, String>{
	
	private static final long serialVersionUID = -4723168061980820149L;
	
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

	public String getIsoformAnnotationId() {
		return StatementUtil.getIsoAnnotationId(this);
	}

	public String getEntryAnnotationId() {
		return StatementUtil.getEntryAnnotationId(this);
	}

}
