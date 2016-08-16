package org.nextprot.commons.statements.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;

public class OracleConnectionPool {

	static OracleConnectionPoolDataSource ocpds;

	static {

		try {

			ocpds = new OracleConnectionPoolDataSource();
			ocpds.setDriverType("thin");
			ocpds.setServerName("fou");
			ocpds.setNetworkProtocol("tcp");
			ocpds.setDatabaseName("SIBTEST3");
			ocpds.setPortNumber(1526);
			ocpds.setUser("nxbed");
			ocpds.setPassword("juventus");

			ocpds.setImplicitCachingEnabled(true);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static Connection getConnection() throws SQLException {
		return ocpds.getPooledConnection().getConnection();
	}

}