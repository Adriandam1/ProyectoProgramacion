package src.main.java.org.adrian.controller;

import src.main.java.org.adrian.model.Usuario;
import java.util.HashMap;
import java.util.Map;

public class GestorUsuarios {
    private Map<String, Usuario> usuarios = new HashMap<>();

    public void agregarUsuario(Usuario usuario) {
        usuarios.put(usuario.getNombre(), usuario);
    }

    public void eliminarUsuario(Usuario usuario) {
        usuarios.remove(usuario);
    }

    /*public Usuario buscarUsuarioPorNombre(String nombre) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombre)) {
                return usuario;
            }
        }
        return null;
    }*/

    //Busca usuario si concuerda y luego comprueba que sea su contraseña
    public boolean iniciarSesion(String nombre, String password) {
        Usuario usuario = usuarios.get(nombre);
        return usuario != null && usuario.getPassword().equals(password);
    }

    public Map<String, Usuario> obtenerTodosLosUsuarios() {
        return usuarios;
    }
}
