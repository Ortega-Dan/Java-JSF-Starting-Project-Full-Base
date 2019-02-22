package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLControl {

	// Connection details
	private static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String DB_URL = "jdbc:sqlserver://192.168.0.7:1433;databaseName=Synergetics_Report;user=Synergetics;password=Password##1;";
	public static final int INSERT = 0;
	public static final int SELECT = 1;
	public static final int UPDATE = 2;
	public static final int DELETE = 3;

	public static Object runSQL(int kind, String sqlquery) {
		try {

			Connection conn = null;
			Statement stmt = null;
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL);
			stmt = conn.createStatement();
			long milsec = System.currentTimeMillis();

			// INSERT
			if (kind == 0) {
				Integer intero = stmt.executeUpdate(sqlquery);
				System.out.println("Insert execution at: " + milsec);
				return intero;
			}

			// SELECT
			if (kind == 1) {
				ResultSet rs = stmt.executeQuery(sqlquery);
				System.out.println("Select execution at: " + milsec);
				return rs;
			}
			
			// UPDATE
			if (kind == 2) {
				Integer intero = stmt.executeUpdate(sqlquery);
				System.out.println("Update execution at: " + milsec);
				return intero;
			}

			return null;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void closeResultSetStuff(ResultSet rs) {
		Statement stmt = null;
		Connection conn = null;
		try {
			stmt = rs.getStatement();
			conn = stmt.getConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {

				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
				se2.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

}
