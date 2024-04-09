package com.itp258capstonekiosk.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

public class KioskDbUtil {
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private DataSource dataSource;

	public KioskDbUtil() {

	}

	public KioskDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}

    public Connection getConnection() throws SQLException {
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String jdbcUrl = "jdbc:mysql://localhost/DatabaseNameGoesHere?useUnicode=true&characterEncoding=UTF-8";
        String user = "UserName";
        String password = "Password";

        try {
            connection = DriverManager.getConnection(jdbcUrl, user, password);
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            // Handle or log the exception appropriately
            throw e;
        }

        return connection;
    }

    public void closeConnection(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close(); // Returns the connection to the pool if using a connection pool
            }
        } catch (SQLException e) {
            // Handle or log the exception appropriately
            e.printStackTrace();
        }
    }
}
