package org.adrian;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:gestor.db"; // Ruta de la base de datos SQLite

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void createTableIfNotExists() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS enlaces ("
                + "nombre TEXT PRIMARY KEY, "
                + "url TEXT NOT NULL, "
                + "comentario TEXT"
                + ");";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
