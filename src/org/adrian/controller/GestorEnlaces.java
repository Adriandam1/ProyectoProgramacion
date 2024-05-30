package org.adrian.controller;

import org.adrian.DatabaseConnection;
import org.adrian.model.Enlace;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestorEnlaces {

    public List<Enlace> obtenerTodosLosEnlaces() {
        List<Enlace> enlaces = new ArrayList<>();
        String sql = "SELECT nombre, url, comentario FROM enlaces";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                String url = resultSet.getString("url");
                String comentario = resultSet.getString("comentario");
                enlaces.add(new Enlace(nombre, url, comentario));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return enlaces;
    }

    public void agregarEnlace(Enlace enlace) {
        String sql = "INSERT INTO enlaces (nombre, url, comentario) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, enlace.getNombre());
            statement.setString(2, enlace.getUrl());
            statement.setString(3, enlace.getComentario());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarEnlace(String nombre) {
        String sql = "DELETE FROM enlaces WHERE nombre = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nombre);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean existeEnlaceConNombre(String nombre) {
        String sql = "SELECT COUNT(*) FROM enlaces WHERE nombre = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nombre);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
