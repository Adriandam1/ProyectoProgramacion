import org.adrian.controller.GestorEnlaces;
import org.adrian.DatabaseConnection;
import org.adrian.model.Enlace;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.*;

public class Tests {

    private Connection connection;

    @Before
    public void setUp() throws SQLException {
        // Establecer la conexión a la base de datos
        connection = DatabaseConnection.getConnection();
        // Creamos la tabla si no existe
        DatabaseConnection.createTableIfNotExists();
    }

    @After
    public void tearDown() throws SQLException {
        // Cerrar la conexión después de cada prueba
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    public void testObtenerTodosLosEnlaces() throws SQLException {
        // Arrange
        GestorEnlaces gestorEnlaces = new GestorEnlaces();

        // Act
        List<Enlace> enlaces = gestorEnlaces.obtenerTodosLosEnlaces();

        // Assert
        assertEquals(0, enlaces.size());
    }

    @Test
    public void testAgregarYEliminarEnlace() throws SQLException {
        // Arrange
        GestorEnlaces gestorEnlaces = new GestorEnlaces();
        Enlace enlace = new Enlace("TestNombre", "http://test.com", "Test comentario");

        // Act
        gestorEnlaces.agregarEnlace(enlace);

        // Assert
        List<Enlace> enlaces = gestorEnlaces.obtenerTodosLosEnlaces();
        assertEquals(1, enlaces.size());

        // Act (Eliminar)
        gestorEnlaces.eliminarEnlace("TestNombre");

        // Assert
        enlaces = gestorEnlaces.obtenerTodosLosEnlaces();
        assertEquals(0, enlaces.size());
    }

    @Test
    public void testExisteEnlaceConNombre() throws SQLException {
        // Arrange
        GestorEnlaces gestorEnlaces = new GestorEnlaces();
        Enlace enlace = new Enlace("TestNombre", "http://test.com", "Test comentario");
        gestorEnlaces.agregarEnlace(enlace);

        // Act & Assert
        assertTrue(gestorEnlaces.existeEnlaceConNombre("TestNombre"));
        assertFalse(gestorEnlaces.existeEnlaceConNombre("OtroNombre"));
    }
}
