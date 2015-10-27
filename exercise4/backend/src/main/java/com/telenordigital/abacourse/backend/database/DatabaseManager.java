package com.telenordigital.abacourse.backend.database;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    private static final String DB_NAME = "sms";

    private final String url;
    private final String username;
    private final String password;

    public DatabaseManager(final String url, final String username, final String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public boolean insert(final String message) {
        final String sql = "INSERT INTO messages (message, created) VALUES (?,NOW())";
        try (
                final Connection conn = createConnection(DB_NAME);
                final PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, message);
            statement.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    private Connection createConnection(final String database) throws SQLException {
        return DriverManager.getConnection(url+database, username, password);
    }
}
