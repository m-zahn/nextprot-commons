package org.nextprot.commons.statements;

import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class RawStatement extends TreeMap<String, String>{

	private static final long serialVersionUID = 1128974177596821182L;

	// Keep the constructor package protected, so it enforces the use of the Builder
	RawStatement(Map<String, String> map) {
		super(map);
	}

	public String getValue(StatementField field) {
		return get(field.name());
	}

	String putValue(StatementField field, String value) {
		return put(field.name(), value);
	}
	/**
	 * Not sure it should be part of the statement
	 * 
	 * @return
	 */
	@Deprecated
	public String getAnnot_hash() {
		return StatementUtil.getAnnotationHash(this);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}


}
