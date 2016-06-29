package org.nextprot.commons.statements;

import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class RawStatement {

	private static final long serialVersionUID = -157703153930536610L;

	private Map<String, String> keyValues = null;

	// Keep it package protected, so it enforeces to use the builder
	RawStatement(Map<String, String> map) {
		keyValues = new TreeMap<String, String>(map);
	}

	public String getValue(StatementField field) {
		return keyValues.get(field.name());
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

	/**
	 * Used for groupBy function. This should be delegated to an external class
	 * 
	 * @return
	 */
	@Deprecated
	public String getBiological_subject_annot_hash() {
		return this.getValue(StatementField.BIOLOGICAL_OBJECT_ANNOT_HASH);
	}

}
