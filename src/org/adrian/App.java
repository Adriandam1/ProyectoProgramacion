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

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    DatabaseConnection.createTableIfNotExists(); // Crear la tabla si no existe

                    GestorUsuarios gestorUsuarios = new GestorUsuarios();
                    cargarUsuariosDesdeArchivo("usuarios.txt", gestorUsuarios);

                    AppFrame frame = new AppFrame();
                    frame.setContentPane(new LoginPanel(frame, gestorUsuarios));
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static void cargarUsuariosDesdeArchivo(String nombreArchivo, GestorUsuarios gestorUsuarios) {
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    String nombreUsuario = partes[0];
                    String contraseña = partes[1];
                    String tipoUsuario = partes[2];
                    gestorUsuarios.agregarUsuario(new Usuario(nombreUsuario, contraseña, tipoUsuario));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
