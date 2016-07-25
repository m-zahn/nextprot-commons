package org.nextprot.commons.statements.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.nextprot.commons.statements.Statement;
import org.nextprot.commons.statements.StatementField;
import org.nextprot.commons.statements.StatementUtil;
import org.nextprot.commons.statements.constants.AnnotationType;
import org.nextprot.commons.statements.constants.NextProtSource;
import org.nextprot.commons.statements.constants.StatementTableNames;
import org.nextprot.commons.statements.service.StatementLoaderService;
import org.nextprot.commons.utils.StringUtils;

public class OracleStatementLoaderServiceImpl implements StatementLoaderService {

	private String entryTable =  StatementTableNames.ENTRY_TABLE;
	private String isoTable =  StatementTableNames.ISO_TABLE;
	private String rawTable = StatementTableNames.RAW_TABLE;
	
	public OracleStatementLoaderServiceImpl() {
	}

	public OracleStatementLoaderServiceImpl(String tableSuffix) {
		entryTable += tableSuffix;
		isoTable += tableSuffix;
		rawTable += tableSuffix;
	}
	
	@Override
	public void loadRawStatementsForSource(Set<Statement> statements, NextProtSource source) {
		load(statements, rawTable);
	}

	@Override
	public void loadStatementsMappedToEntrySpecAnnotationsForSource(Set<Statement> statements, NextProtSource source) {
		StatementUtil.computeAndSetAnnotationIdsForRawStatements(statements, AnnotationType.ENTRY);
		load(statements, entryTable);
	}
	
	@Override
	public void loadStatementsMappedToIsoSpecAnnotationsForSource(Set<Statement> statements, NextProtSource source) {
		StatementUtil.computeAndSetAnnotationIdsForRawStatements(statements, AnnotationType.ISOFORM);
		load(statements, isoTable);
	}
	
	private void load(Set<Statement> statements, String tableName) {
		
		Connection conn;
		try {

			conn = OracleConnectionPool.getConnection();
			String columnNames = StringUtils.mkString(StatementField.values(), "", ",", "");
			List<String> bindVariablesList = new ArrayList<>();
			for (int i=0 ; i<StatementField.values().length; i++) {
				bindVariablesList.add("?");
			}
			String bindVariables = StringUtils.mkString(bindVariablesList, "",",", "");

			PreparedStatement pstmt = conn.prepareStatement(
					"INSERT INTO " + tableName + " (" + columnNames + ") VALUES ( " + bindVariables + ")"
			);

			for (Statement s : statements) {
				for (int i = 0; i < StatementField.values().length; i++) {
					String value = s.getValue(StatementField.values()[i]);
					if (value != null) {
						pstmt.setString(i + 1, value.replace("'", "''"));
					} else {
						pstmt.setNull(i + 1, java.sql.Types.NVARCHAR);
					}
				}
				pstmt.addBatch();
			}

			pstmt.executeBatch();
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public void deleteStatementsForSource(NextProtSource source) {

		try {

			Connection conn = OracleConnectionPool.getConnection();
			java.sql.Statement statement = conn.createStatement();
			statement.addBatch("DELETE FROM " + entryTable + " WHERE source = " + source.getSourceName());
			statement.addBatch("DELETE FROM " + isoTable + " WHERE source = " + source.getSourceName());
			statement.addBatch("DELETE FROM " + rawTable + " WHERE source = " + source.getSourceName());

			statement.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}