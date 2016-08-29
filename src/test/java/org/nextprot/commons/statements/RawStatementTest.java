package org.nextprot.commons.statements;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.nextprot.commons.constants.QualityQualifier;
import org.nextprot.commons.statements.constants.AnnotationType;

public class RawStatementTest {
	
	QualityQualifier defaultQuality = QualityQualifier.GOLD;
	
	@Test
	public void testRawStatementEquals() {
		Statement rs1 = StatementBuilder.createNew().addCompulsaryFields("AAA", "BBB", "CCC", defaultQuality).build();
		Statement rs2 = StatementBuilder.createNew().addCompulsaryFields("AAA", "BBB", "CCC", defaultQuality).build();
		assertEquals(rs1, rs2);
	}
	
	
	@Test
	public void testRawStatementInsertionInSets() {
		Set<Statement> set1 = new HashSet<>();
		set1.add(StatementBuilder.createNew().addCompulsaryFields("AAA", "BBB", "CCC", defaultQuality).build());
		set1.add(StatementBuilder.createNew().addCompulsaryFields("AAA", "BBB", "CCC", defaultQuality ).build());
		
		assertEquals(set1.size(), 1);
		
		set1.add(StatementBuilder.createNew().addCompulsaryFields("DDD", "BBB", "CCC", defaultQuality).build());
		assertEquals(set1.size(), 2);

	}
	
	
	@Test
	public void testAnnotHashUnicity() {

		Statement rs1 = StatementBuilder.createNew()
				.addField(StatementField.NEXTPROT_ACCESSION, "NX_P25054")
				.addField(StatementField.GENE_NAME, "apc")
				.addField(StatementField.ISOFORM_ACCESSION, "NX_P25054-1")
				.addCompulsaryFields("AAA", "BBB", "CCC", defaultQuality)
				.addSourceInfo("CAVA-VP90999", "BED").buildWithAnnotationHash(AnnotationType.ISOFORM);
		Statement rs2 = StatementBuilder.createNew()
				.addField(StatementField.NEXTPROT_ACCESSION, "NX_P25054")
				.addField(StatementField.GENE_NAME, "apc")
				.addField(StatementField.ISOFORM_ACCESSION, "NX_P25054-1")
				.addCompulsaryFields("AAA", "BBB", "CCC", defaultQuality)
				.addSourceInfo("XPTO", "Caviar").buildWithAnnotationHash(AnnotationType.ISOFORM);

		assertNotEquals(rs1, rs2); 
		assertEquals(rs1.getValue(StatementField.ANNOTATION_ID), rs2.getValue(StatementField.ANNOTATION_ID));
	}

	@Test
	public void testRawStatement2() {

		Statement rs1 = StatementBuilder.createNew().addCompulsaryFields("AAA", "BBB", "CCC", defaultQuality).build();
		Statement rs2 = StatementBuilder.createNew().addMap(rs1).build();

		assertEquals(rs1, rs2);
	}
}
