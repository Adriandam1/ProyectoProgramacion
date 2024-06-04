package org.adrian.controller;

import org.adrian.model.Usuario;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

    /**
     * Carga los usuarios desde un archivo de texto y los agrega al gestor de usuarios.
     * El archivo debe tener el siguiente formato:
     * Cada línea del archivo representa un usuario.
     * @param nombreArchivo     El nombre del archivo que contiene los usuarios.
     * @param gestorUsuarios    El gestor de usuarios al cual se agregarán los usuarios leídos.
     */
    public static void cargarUsuariosDesdeArchivo(String nombreArchivo, GestorUsuarios gestorUsuarios) {
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
