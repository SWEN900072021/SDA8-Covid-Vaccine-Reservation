package org.unimelb.cis.swen90007sda8.DBConnector;

import java.sql.*;

public class postgresqlConnector {
    private Connection conn = null;
    public postgresqlConnector() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            String url = "jdbc:postgresql://ec2-44-198-223-154.compute-1.amazonaws.com/de41193liacu11";
            String user = "afltyojyhsunzv";
            String password = "f353ad528068ff503fe032630811b830cc29a0ac26cca731ae1e43a8ef5575a9";
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Connect to the PostgreSQL database
     * @return a Connection object
     */
    public ResultSet connect(String stmt) {
        PreparedStatement findStatement;
        ResultSet rs = null;
        try {
            findStatement = conn.prepareStatement(stmt);
            findStatement.execute();
            rs = findStatement.getResultSet();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return rs;
    }

    public Boolean connectBoolean(String stmt) {
        PreparedStatement findStatement;
        Boolean result = null;
        try {
            findStatement = conn.prepareStatement(stmt);
            result = findStatement.execute();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return result;
    }

}