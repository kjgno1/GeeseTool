package com.ptn.geesetool.config;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SqliteUtil {

    private static Connection connection;

    public synchronized static Connection getConnection() throws SQLException {
        if (connection == null) {
            try {
                String driverClass = "org.sqlite.JDBC";
                Class.forName(driverClass);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            String url = "jdbc:sqlite:src/main/resources/db/app.db";
            return connection = DriverManager.getConnection(url);
        } else {
            return connection;
        }
    }
    public static void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            throw new NullPointerException("Connection not open! ");
        }
    }

    public static final String DB_USER = "create table TBL_IMAGE_INFO (" +
            "  ID integer(11) not null," +
            "  NAME text(200)," +
            "  DESCRIPTIONS text(400)," +
            "  TAGS text(400)," +
            "  IS_UPLOADED text(400)," +
            "  primary key (ID)" +
            ");";

    public static void createDatabases() throws SQLException, IOException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        statement.execute(DB_USER);
        close();
    }
}