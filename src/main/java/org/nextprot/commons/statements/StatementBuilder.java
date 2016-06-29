package org.nextprot.commons.statements;

import java.util.HashMap;
import java.util.Map;

public class StatementBuilder {

	private Map<String, String> keyValues = new HashMap<String, String>();

	public static StatementBuilder createNew() {
		return new StatementBuilder();
	}

	public StatementBuilder addStatementField(StatementField statementField, String statementValue) {
		this.keyValues.put(statementField.name(), statementValue);
		return this;
	}

	public RawStatement build() {
		return new RawStatement(keyValues);
	}
}
