package src.main.java.org.adrian.controller;

import src.main.java.org.adrian.model.Usuario;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GestorUsuarios {
    private List<Usuario> usuarios;
    public Usuario usuarioActual;

    public GestorUsuarios() {
        usuarios = new ArrayList<>();
    }


    public boolean iniciarSesion(JTextField nombre, JPasswordField password) {

        // Verificar las credenciales de inicio de sesión
        //si el usuario coincide devuelve true, sino devuelve false
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombre) && usuario.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void eliminarUsuario(Usuario usuario) {
        usuarios.remove(usuario);
    }

    public Usuario buscarUsuarioPorNombre(String nombre) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombre)) {
                return usuario;
            }
        }
        return null; // Usuario no encontrado
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarios;
    }
}