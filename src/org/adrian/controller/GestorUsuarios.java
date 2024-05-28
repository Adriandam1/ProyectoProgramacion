package org.adrian.controller;

import org.adrian.model.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 * GestorUsuarios es una clase que se encarga de gestionar la lista de usuarios y la autenticación en la aplicación.
 */
public class GestorUsuarios {
    private List<Usuario> usuarios;

    /**
     * Constructor de la clase GestorUsuarios que inicializa la lista de usuarios.
     */
    public GestorUsuarios() {
        usuarios = new ArrayList<>();
    }

    /**
     * Agrega un nuevo usuario a la lista de usuarios.
     *
     * @param usuario El usuario a agregar.
     */
    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    /**
     * Elimina un usuario de la lista de usuarios.
     *
     * @param usuario El usuario a eliminar.
     */
    public void eliminarUsuario(Usuario usuario) {
        usuarios.remove(usuario);
    }

    /**
     * Obtiene todos los usuarios almacenados en la lista.
     *
     * @return La lista de todos los usuarios.
     */
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarios;
    }

    /**
     * Autentica un usuario en la aplicación.
     *
     * @param nombre   El nombre de usuario.
     * @param password La contraseña del usuario.
     * @return El usuario autenticado, o null si la autenticación falla.
     */
    public Usuario autenticarUsuario(String nombre, String password) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombre) && usuario.getPassword().equals(password)) {
                return usuario;
            }
        }
        return null;
    }
}
