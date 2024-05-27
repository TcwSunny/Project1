package utils;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3p0Util {
	public static ComboPooledDataSource openDataSource() {
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		try {
			cpds.setDriverClass( "com.microsoft.sqlserver.jdbc.SQLServerDriver" );
			cpds.setJdbcUrl( "jdbc:sqlserver://localhost:1433;DatabaseName=project1;encrypt=false" );
			cpds.setUser("SunnyTeng");
			cpds.setPassword("h225401129");
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		return cpds;
	}
	
	private static ComboPooledDataSource dataSource = C3p0Util.openDataSource();
	
    public static Connection getConnection() throws SQLException {
    	return dataSource.getConnection();
    }
 
	public static void release(Connection connection) {
		try {
			if(connection!=null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void release(Connection connection,Statement statement) {
		try {
			if(connection!=null) {
				connection.close();
			}
			if(statement!=null) {
				statement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void release(Connection connection,Statement statement,ResultSet resultSet) {
		try {
			if(connection!=null) {
				connection.close();
			}
			if(statement!=null) {
				statement.close();
			}
			if(resultSet!=null) {
				resultSet.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
