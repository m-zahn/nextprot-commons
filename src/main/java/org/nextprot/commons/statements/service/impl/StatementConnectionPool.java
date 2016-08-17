package org.nextprot.commons.statements.service.impl;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class StatementConnectionPool {

	static ComboPooledDataSource pds;

	static {

		pds = new ComboPooledDataSource();

		try {
			pds.setDriverClass("org.postgresql.Driver");
		} catch (PropertyVetoException e) {
			throw new RuntimeException(e.getMessage());
		}

		pds.setJdbcUrl("jdbc:postgresql://crick:5432/nxflat");
		pds.setUser("postgres");
		pds.setPassword("postgres");
		pds.setMaxPoolSize(10);
		pds.setMinPoolSize(1);
		pds.setInitialPoolSize(1);
		pds.setAutoCommitOnClose(true);

	}

	public static Connection getConnection() throws SQLException {
		return pds.getConnection();
	}

}