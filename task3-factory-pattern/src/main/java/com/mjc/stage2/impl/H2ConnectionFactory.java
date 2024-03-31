package com.mjc.stage2.impl;

import com.mjc.stage2.ConnectionFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class H2ConnectionFactory implements ConnectionFactory {
    private final String jdbcDriver;
    private final String dbUrl;
    private final String user;
    private final String password;

    public H2ConnectionFactory() {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("h2database.properties")) {
            props.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        user = props.getProperty("user");
        password = props.getProperty("password");
        dbUrl = props.getProperty("db_url");
        jdbcDriver = props.getProperty("jdbc_driver");
    }

    @Override
    public Connection createConnection() {
        try {
            Class.forName(jdbcDriver);
            return DriverManager.getConnection(dbUrl, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

