package org.adrian;

import javax.swing.JOptionPane;
import java.sql.*;

public class CConexion {

    Connection conectar = null;
    String usuario = "postgres";
    String contrasenia = "debian";
    String bd = "gestor";
    String ip = "localhost";
    String puerto = "5432";
    String cadena = "jdbc:postgresql://" + ip + ":" + puerto + "/" + bd;

    public Connection establecerConexion() {
        try {
            Class.forName("org.postgresql.Driver");
            conectar = DriverManager.getConnection(cadena, usuario, contrasenia);
            JOptionPane.showMessageDialog(null, "Se conectó a la BD");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectarse: " + e.toString());
        }
        return conectar;
    }
    /* Este metodo sirve para crear una base de datos
     * introduces el nombre de la base de datos
     * return mensaje informativo
     */

    public void crearBBDD() {
        try (Statement stmt = conectar.createStatement()) {
            String sql = "CREATE database gestor";
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "La base de datos 'gestor' fue creada con exito");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al crear la base de datos 'gestor': " + e.getMessage());
        }
    }

    public void crearTabla() {
        try (Statement stmt = conectar.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS enlaces (" +
                    "ID SERIAL PRIMARY KEY, " +
                    "Nombre VARCHAR(20), " +
                    "url VARCHAR(60), " +
                    "comentario VARCHAR(50)";
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Tabla enlaces creada con éxito");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al crear la tabla: " + e.getMessage());
        }
    }

    public void insertarEnlace(String nombre, String url, String comentario) {
        String sql = "INSERT INTO Enlaces (Nombre, url, comentario) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conectar.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, url);
            pstmt.setString(3, comentario);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Enlace insertado con éxito");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar enlace: " + e.getMessage());
        }
    }

    public void borrarEnlace(String nombre) {
        String sql = "DELETE FROM enlaces WHERE " + nombre + " = ?";
        try (PreparedStatement pstmt = conectar.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(null, "Enlace eliminado con éxito");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el enlace: " + nombre);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar enlace: " + e.getMessage());
        }
    }

    public void actualizarEnlace(String nombre, String url, String comentario) {
        String sql = "UPDATE enlaces SET Nombre = ? WHERE nombre = ?";
        try (PreparedStatement pstmt = conectar.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, url);
            pstmt.setString(3, comentario);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(null, "Enlace actualizado con éxito");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el enlace: " +nombre);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar enlace: " + e.getMessage());
        }
    }
}
