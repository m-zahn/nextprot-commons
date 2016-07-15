package org.nextprot.commons.statements;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class RawStatementTest {
	
	@Test
	public void testRawStatementEquals() {
		RawStatement rs1 = StatementBuilder.createNew().addCompulsoryFields("AAA", "BBB", "CCC").build();
		RawStatement rs2 = StatementBuilder.createNew().addCompulsoryFields("AAA", "BBB", "CCC").build();
		assertEquals(rs1, rs2);
	}
	
	
	@Test
	public void testRawStatementInsertionInSets() {
		Set<RawStatement> set1 = new HashSet<>();
		set1.add(StatementBuilder.createNew().addCompulsoryFields("AAA", "BBB", "CCC").build());
		set1.add(StatementBuilder.createNew().addCompulsoryFields("AAA", "BBB", "CCC").build());
		
		assertEquals(set1.size(), 1);
		
		set1.add(StatementBuilder.createNew().addCompulsoryFields("DDD", "BBB", "CCC").build());
		assertEquals(set1.size(), 2);

	}
	
	
	@Test
	public void testAnnotHashUnicity() {

		RawStatement rs1 = StatementBuilder.createNew()
				.addCompulsoryFields("AAA", "BBB", "CCC")
				.addSourceInfo("CAVA-VP90999", "BED").build();
		RawStatement rs2 = StatementBuilder.createNew()
				.addCompulsoryFields("AAA", "BBB", "CCC")
				.addSourceInfo("XPTO", "Caviar").build();

		assertNotEquals(rs1, rs2); 
		assertEquals(rs1.getValue(StatementField.ANNOT_ISO_ID), rs2.getValue(StatementField.ANNOT_ISO_ID));
	}

	@Test
	public void testRawStatement2() {

		RawStatement rs1 = StatementBuilder.createNew().addCompulsoryFields("AAA", "BBB", "CCC").build();
		RawStatement rs2 = StatementBuilder.createNew().addMap(rs1).build();

		assertEquals(rs1, rs2);
	}
}
