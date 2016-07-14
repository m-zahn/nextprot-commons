package org.nextprot.commons.statements.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.nextprot.commons.statements.RawStatement;
import org.nextprot.commons.statements.StatementField;
import org.nextprot.commons.statements.service.StatementLoaderService;
import org.nextprot.commons.utils.StringUtils;

public class OracleStatementLoaderServiceImpl implements StatementLoaderService {

	private String TABLE = "MAPPED_STATEMENTS_NEXT";

	@Override
	public void load(List<RawStatement> statements) {

		Connection conn;
		try {

			conn = OracleConnectionPool.getConnection();
			Statement statement = conn.createStatement();

			String columnNames = StringUtils.mkString(StatementField.values(), "", ",", "");
			
			for(RawStatement s : statements){
				
				List<String> values = new ArrayList<>();
				for(StatementField v : StatementField.values()) {
					String value = s.getValue(v);
					values.add((value != null) ? value.replace("'", "''") : null);
				}
				String fieldValues = StringUtils.mkString(values, "'", ",", "'");
				
				
				String sqlStatement = "INSERT INTO " + TABLE + " (" + columnNames + ") VALUES ( " + fieldValues + ")";
				statement.addBatch(sqlStatement);
				
			}

			statement.executeBatch();
			statement.close();
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
			statement.executeQuery("DELETE FROM " + TABLE);
			statement.close();
			statement.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}