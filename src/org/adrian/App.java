package org.adrian;

import org.adrian.controller.GestorUsuarios;
import org.adrian.DatabaseConnection;
import org.adrian.model.Usuario;
import org.adrian.view.AppFrame;
import org.adrian.view.LoginPanel;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.adrian.controller.GestorUsuarios.cargarUsuariosDesdeArchivo;

/**
 * Clase principal de la aplicación que inicia la interfaz gráfica y gestiona la conexión a la base de datos.
 */
public class App {
    /**
     * Método principal de la aplicación. Inicia la interfaz gráfica y carga los usuarios desde un archivo.
     *
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    DatabaseConnection.createTableIfNotExists(); // Crear la tabla si no existe

                    GestorUsuarios gestorUsuarios = new GestorUsuarios();
                    cargarUsuariosDesdeArchivo("usuarios.txt", gestorUsuarios);

                    // Crear y mostrar el marco de la aplicacion
                    AppFrame frame = new AppFrame();
                    frame.setContentPane(new LoginPanel(frame, gestorUsuarios));
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
