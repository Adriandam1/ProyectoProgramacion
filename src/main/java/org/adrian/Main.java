package src.main.java.org.adrian;

import src.main.java.org.adrian.controller.GestorUsuarios;
import src.main.java.org.adrian.model.Usuario;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try {
            // Ejemplo de uso
            GestorUsuarios gestor = new GestorUsuarios();

            Usuario usuario1 = new Usuario("Pepe", "contraseña123", "admin");
            Usuario usuario2 = new Usuario("Pepa", "secreto456", "usuario");

            gestor.agregarUsuario(usuario1);
            gestor.agregarUsuario(usuario2);

            System.out.println("Usuarios registrados:");
            for (Usuario usuario : gestor.obtenerTodosLosUsuarios()) {
                System.out.println(usuario);
            }

            Usuario usuarioEncontrado = gestor.buscarUsuarioPorNombre("Alice");
            if (usuarioEncontrado != null) {
                System.out.println("Usuario encontrado: " + usuarioEncontrado);
            } else {
                System.out.println("Usuario no encontrado.");
            }
        } catch (Exception e) {
            System.err.println("Error al ejecutar el programa: " + e.getMessage());
        }
    }
}