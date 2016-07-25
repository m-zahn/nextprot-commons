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

	private String tableSuffix = "";
	
	public OracleStatementLoaderServiceImpl() {
	}

	public OracleStatementLoaderServiceImpl(String tableSuffix) {
		this.tableSuffix = tableSuffix;
	}
	
	@Override
	public void loadRawStatementsForSource(Set<Statement> statements, NextProtSource source) {
		load(statements, StatementTableNames.RAW_TABLE);
	}

	@Override
	public void loadStatementsMappedToEntrySpecAnnotationsForSource(Set<Statement> statements, NextProtSource source) {
		StatementUtil.computeAndSetAnnotationIdsForRawStatements(statements, AnnotationType.ENTRY);
		load(statements, StatementTableNames.ENTRY_TABLE);
	}
	
	@Override
	public void loadStatementsMappedToIsoSpecAnnotationsForSource(Set<Statement> statements, NextProtSource source) {
		StatementUtil.computeAndSetAnnotationIdsForRawStatements(statements, AnnotationType.ISOFORM);
		load(statements, StatementTableNames.ISO_TABLE);
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
			statement.addBatch("DELETE FROM " + StatementTableNames.ENTRY_TABLE + " WHERE source = " + source.getSourceName());
			statement.addBatch("DELETE FROM " + StatementTableNames.ISO_TABLE + " WHERE source = " + source.getSourceName());
			statement.addBatch("DELETE FROM " + StatementTableNames.RAW_TABLE + " WHERE source = " + source.getSourceName());

			statement.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}