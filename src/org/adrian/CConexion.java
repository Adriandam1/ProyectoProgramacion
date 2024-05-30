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

    /**
     * Establece la conexión a la base de datos.
     * @return La conexión establecida.
     */
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

   /* public Connection2() {


        private static String url = "jdbc:sqlite:.\gestor";
        private static String usuario = "root";
        private static String password = "";
        Connection con = DriverManager.getConnection(url, usuario, password);
    }*/

    /**
     * Crea la base de datos.
     */
    public void crearBBDD() {
        try (Statement stmt = conectar.createStatement()) {
            String sql = "CREATE DATABASE IF NOT EXISTS gestor";
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "La base de datos 'gestor' fue creada con éxito");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al crear la base de datos 'gestor': " + e.getMessage());
        }
    }

    /**
     * Crea la tabla 'enlaces' en la base de datos.
     */
    public void crearTabla() {
        try (Statement stmt = conectar.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS enlaces (" +
                    "ID SERIAL PRIMARY KEY, " +
                    "Nombre VARCHAR(20), " +
                    "url VARCHAR(60), " +
                    "comentario VARCHAR(50))";
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Tabla 'enlaces' creada con éxito");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al crear la tabla 'enlaces': " + e.getMessage());
        }
    }

    /**
     * Inserta un nuevo enlace en la tabla 'enlaces'.
     * @param nombre El nombre del enlace.
     * @param url La URL del enlace.
     * @param comentario El comentario asociado al enlace.
     */
    public void insertarEnlace(String nombre, String url, String comentario) {
        String sql = "INSERT INTO enlaces (Nombre, url, comentario) VALUES (?, ?, ?)";
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

    /**
     * Elimina un enlace de la tabla 'enlaces' según su nombre.
     * @param nombre El nombre del enlace a eliminar.
     */
    public void borrarEnlace(String nombre) {
        String sql = "DELETE FROM enlaces WHERE nombre = ?";
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

    /**
     * Actualiza un enlace de la tabla 'enlaces'.
     * @param nombre El nombre del enlace a actualizar.
     * @param url La nueva URL del enlace.
     * @param comentario El nuevo comentario asociado al enlace.
     */
    public void actualizarEnlace(String nombre, String url, String comentario) {
        String sql = "UPDATE enlaces SET url = ?, comentario = ? WHERE nombre = ?";
        try (PreparedStatement pstmt = conectar.prepareStatement(sql)) {
            pstmt.setString(1, url);
            pstmt.setString(2, comentario);
            pstmt.setString(3, nombre);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(null, "Enlace actualizado con éxito");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el enlace: " + nombre);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar enlace: " + e.getMessage());
        }
    }
}
