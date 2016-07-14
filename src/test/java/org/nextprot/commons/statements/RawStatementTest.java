package org.nextprot.commons.statements;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class RawStatementTest {
	
	@Test
	public void testRawStatementEquals() {
		RawStatement rs1 = StatementBuilder.createNew().addCompulsaryFields("AAA", "BBB", "CCC").build();
		RawStatement rs2 = StatementBuilder.createNew().addCompulsaryFields("AAA", "BBB", "CCC").build();
		assertEquals(rs1, rs2);
	}
	
	
	@Test
	public void testRawStatementInsertionInSets() {
		Set<RawStatement> set1 = new HashSet<RawStatement>();
		set1.add(StatementBuilder.createNew().addCompulsaryFields("AAA", "BBB", "CCC").build());
		set1.add(StatementBuilder.createNew().addCompulsaryFields("AAA", "BBB", "CCC").build());
		
		assertEquals(set1.size(), 1);
		
		set1.add(StatementBuilder.createNew().addCompulsaryFields("DDD", "BBB", "CCC").build());
		assertEquals(set1.size(), 2);

	}
	
	
	@Test
	public void testAnnotHashUnicity() {

		RawStatement rs1 = StatementBuilder.createNew()
				.addCompulsaryFields("AAA", "BBB", "CCC")
				.addSourceInfo("CAVA-VP90999", "BED").build();
		RawStatement rs2 = StatementBuilder.createNew()
				.addCompulsaryFields("AAA", "BBB", "CCC")
				.addSourceInfo("XPTO", "Caviar").build();

		assertNotEquals(rs1, rs2); 
		assertEquals(rs1.getIsoformAnnotationId(), rs2.getIsoformAnnotationId());
	}


}
