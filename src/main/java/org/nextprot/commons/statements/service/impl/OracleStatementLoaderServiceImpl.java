package org.nextprot.commons.statements.service.impl;

import org.nextprot.commons.statements.RawStatement;
import org.nextprot.commons.statements.StatementField;
import org.nextprot.commons.statements.StatementUtil;
import org.nextprot.commons.statements.service.StatementLoaderService;
import org.nextprot.commons.utils.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OracleStatementLoaderServiceImpl implements StatementLoaderService {

	private static final String TABLE = "MAPPED_STATEMENTS_NEXT";

	private final String table;

	public OracleStatementLoaderServiceImpl() {
		this(TABLE);
	}

	public OracleStatementLoaderServiceImpl(String table) {
		this.table = table;
	}

	@Override
	public void load(List<RawStatement> statements) {
		
		
		StatementUtil.computeAndSetAnnotationIdsForRawStatements(statements);

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
					"INSERT INTO " + table + " (" + columnNames + ") VALUES ( " + bindVariables + ")"
			);

			for (RawStatement s : statements) {
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
	public void deleteAll() {

		try {

			Connection conn = OracleConnectionPool.getConnection();
			Statement statement = conn.createStatement();
			statement.executeQuery("DELETE FROM " + table);
			statement.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}