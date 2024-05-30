package org.adrian;

import org.adrian.controller.GestorUsuarios;
import org.adrian.DatabaseConnection;
import org.adrian.model.Usuario;
import org.adrian.view.AppFrame;
import org.adrian.view.LoginPanel;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    DatabaseConnection.createTableIfNotExists(); // Crear la tabla si no existe

                    GestorUsuarios gestorUsuarios = new GestorUsuarios();
                    gestorUsuarios.agregarUsuario(new Usuario("lulu", "123", "admin"));
                    gestorUsuarios.agregarUsuario(new Usuario("admin", "password", "admin"));
                    gestorUsuarios.agregarUsuario(new Usuario("user1", "pass1", "user"));
                    gestorUsuarios.agregarUsuario(new Usuario("user2", "pass2", "user"));

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
