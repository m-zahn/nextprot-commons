package org.nextprot.commons.statements;

import org.junit.Test;

public class GenerateMappedStatementDDL {

	String tableName = "MAPPED_STATEMENTS_NEXT";

	@Test
	public void generateDDL() {
		StringBuilder sb = new StringBuilder();
		sb.append("DROP TABLE " + tableName + ";\n");
		sb.append("CREATE TABLE " + tableName + " (\n");
		for (int i = 0; i < StatementField.values().length; i++) {
			sb.append("\t" + StatementField.values()[i].name() + " VARCHAR2(4000)");
			if(i+1 < StatementField.values().length){
				sb.append(",");
			}
			sb.append("\n");
		}
		sb.append(");\n");

		sb.append("CREATE INDEX MS_" + StatementField.ENTRY_ACCESSION.name() + "_IDX ON " + tableName + " ( " + StatementField.ENTRY_ACCESSION.name() + " );\n");
		sb.append("CREATE INDEX MS_" + StatementField.ANNOT_ISO_ID.name() + "_IDX ON " + tableName + " ( " + StatementField.ANNOT_ISO_ID.name() + " );\n");
		sb.append("CREATE INDEX MS_" + StatementField.ANNOT_ENTRY_ID.name() + "_IDX ON " + tableName + " ( " + StatementField.ANNOT_ENTRY_ID.name() + " );\n");

		sb.append("GRANT SELECT ON MAPPED_STATEMENTS_NEXT TO nxbed_read;\n");

		System.out.println(sb.toString());
	}

}
