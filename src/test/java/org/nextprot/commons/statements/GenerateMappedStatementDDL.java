package org.nextprot.commons.statements;

import org.junit.Test;
import org.nextprot.commons.statements.constants.StatementTableNames;

public class GenerateMappedStatementDDL {

	@Test
	public void generateDDL() {
		generateOneTable(StatementTableNames.ENTRY_TABLE);
		generateOneTable(StatementTableNames.ISO_TABLE);
		generateOneTable(StatementTableNames.RAW_TABLE);

	}
	
	private void generateOneTable(String tableName){
		
		StringBuilder sb = new StringBuilder();
		sb.append("DROP TABLE nxflat." + tableName + ";\n");
		sb.append("CREATE TABLE nxflat." + tableName + " (\n");
		for (int i = 0; i < StatementField.values().length; i++) {
			sb.append("\t" + StatementField.values()[i].name() + " VARCHAR(4000)");
			if(i+1 < StatementField.values().length){
				sb.append(",");
			}
			sb.append("\n");
		}
		sb.append(");\n");

		sb.append("CREATE INDEX " + tableName.substring(0, 10) + "_ENTRY_AC_IDX ON nxflat." + tableName + " ( " + StatementField.ENTRY_ACCESSION.name() + " );\n");
		sb.append("CREATE INDEX " + tableName.substring(0, 10) + "_ANNOT_ID_IDX ON nxflat." + tableName + " ( " + StatementField.ANNOTATION_ID.name() + " );\n");

		//sb.append("GRANT SELECT ON " + tableName + " TO nxbed_read;\n");

		System.out.println(sb.toString());
		System.out.println();
		System.out.println();

		
	}

}
