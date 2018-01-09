package com.test.karaf;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author e3ckuo
 */
public class DataSourceConnector {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceConnector.class);


    private final DataSource dataSource;

    public DataSourceConnector(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void init() {
        try (Connection connection = dataSource.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                LOGGER.info("Hello world!");
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
