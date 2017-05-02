package org.nextprot.commons.statements;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TargetIsoformSet extends TreeSet<TargetIsoformStatementPosition> {

	private static final long serialVersionUID = -8387267990628898963L;

	public TargetIsoformSet() {
		super();
	}
	
	public TargetIsoformSet(Set<TargetIsoformStatementPosition> targets) {
		super(targets);
	}
	
	
	public static TargetIsoformSet deSerializeFromJsonString(String tistatementAsJson) {

		ObjectMapper mapper = new ObjectMapper();
		try {
			return new TargetIsoformSet(mapper.readValue(tistatementAsJson, TargetIsoformSet.class));
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}

	}

	public String serializeToJsonString() {

		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Failed to convert" + e.getLocalizedMessage());
		}
	}


}
