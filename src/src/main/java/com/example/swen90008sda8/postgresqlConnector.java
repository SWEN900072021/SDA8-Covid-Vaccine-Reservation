package com.example.swen90008sda8;

import org.postgresql.util.PSQLException;

import java.sql.*;

//public class JDBCtest {
//    private final String url = "jdbc:postgresql://localhost:5432/myDB";
//    private final String user = "postgres";
//    private final String password = "123456";
//
//    public Connection connect() throws SQLException {
//        Connection conn = DriverManager.getConnection(url, user, password);
//        return conn;
//    }
//
//    public static void main(String[] args) throws SQLException {
//        JDBCtest app = new JDBCtest();
//        app.connect();
//    }
//}

public class postgresqlConnector {
    private final String url = "jdbc:postgresql://localhost:5432/myDB";
    private final String user = "postgres";
    private final String password = "123456";
    /**
     * Connect to the PostgreSQL database
     * @return a Connection object
     */
    public ResultSet connect(String stmt) {
//        String sql = "SELECT * FROM users;";
        PreparedStatement findStatement;
        ResultSet rs = null;
        Connection conn;
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            conn = DriverManager.getConnection(url, user, password);
            findStatement = conn.prepareStatement(stmt);
            findStatement.execute();
            rs = findStatement.getResultSet();
        } catch (PSQLException e1) {
            e1.printStackTrace();
        } catch (SQLException e2) {
            e2.printStackTrace();
        }
        return rs;
    }

}