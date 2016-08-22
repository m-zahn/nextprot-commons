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

public class JDBCStatementLoaderServiceImpl implements StatementLoaderService {

	private String entryTable =  StatementTableNames.ENTRY_TABLE;
	private String isoTable =  StatementTableNames.ISO_TABLE;
	private String rawTable = StatementTableNames.RAW_TABLE;
	
	public JDBCStatementLoaderServiceImpl() {
	}

	public JDBCStatementLoaderServiceImpl(String tableSuffix) {
		entryTable += tableSuffix;
		isoTable += tableSuffix;
		rawTable += tableSuffix;
	}
	
	@Override
	public void loadRawStatementsForSource(Set<Statement> statements, NextProtSource source) throws SQLException {
		StatementUtil.computeAndSetAnnotationIdsForRawStatements(statements, AnnotationType.STATEMENT);
		load(statements, rawTable, source);
	}

	@Override
	public void loadStatementsMappedToEntrySpecAnnotationsForSource(Set<Statement> statements, NextProtSource source) throws SQLException {
		StatementUtil.computeAndSetAnnotationIdsForRawStatements(statements, AnnotationType.ENTRY);
		load(statements, entryTable, source);
	}
	
	@Override
	public void loadStatementsMappedToIsoSpecAnnotationsForSource(Set<Statement> statements, NextProtSource source) throws SQLException {
		StatementUtil.computeAndSetAnnotationIdsForRawStatements(statements, AnnotationType.ISOFORM);
		load(statements, isoTable, source);
	}
	
	private void load(Set<Statement> statements, String tableName, NextProtSource source) throws SQLException {
		
		Connection conn = null;
		java.sql.Statement deleteStatement = null;
		PreparedStatement pstmt = null;
		
		try {

			conn = StatementConnectionPool.getConnection();
			
			deleteStatement = conn.createStatement();
			deleteStatement.addBatch("DELETE FROM nxflat." + tableName + " WHERE SOURCE = '" + source.getSourceName() + "'");

			
			String columnNames = StringUtils.mkString(StatementField.values(), "", ",", "");
			List<String> bindVariablesList = new ArrayList<>();
			for (int i=0 ; i<StatementField.values().length; i++) {
				bindVariablesList.add("?");
			}
			String bindVariables = StringUtils.mkString(bindVariablesList, "",",", "");

			pstmt = conn.prepareStatement(
					"INSERT INTO nxflat." + tableName + " (" + columnNames + ") VALUES ( " + bindVariables + ")"
			);

			for (Statement s : statements) {
				for (int i = 0; i < StatementField.values().length; i++) {
					StatementField sf = StatementField.values()[i];
					String value = null;
					if(sf.equals(StatementField.SOURCE)){
						value = source.getSourceName();
					}else value = s.getValue(sf); 
					if (value != null) {
						pstmt.setString(i + 1, value.replace("'", "''"));
					} else {
						pstmt.setNull(i + 1, java.sql.Types.VARCHAR);
					}
				}
				pstmt.addBatch();
			}

			deleteStatement.executeBatch();
			pstmt.executeBatch();

		} finally {

			if(deleteStatement != null){
				deleteStatement.close();
			}
			
			if(pstmt  != null){
				pstmt.close();
			}
		
			if(conn != null){
				conn.close();
			}

		}
		
	}

}