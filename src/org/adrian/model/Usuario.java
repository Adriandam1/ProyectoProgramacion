package org.adrian.model;

/**
 * La clase Usuario representa un usuario del sistema con un nombre de usuario, una contraseña y un rol asociado.
 */
public class Usuario {
    private String nombre;
    private String password;
    private String rol;

    /**
     * Constructor de la clase Usuario que inicializa los atributos del usuario.
     *
     * @param nombre   El nombre de usuario.
     * @param password La contraseña del usuario.
     * @param rol      El rol asociado al usuario.
     */
    public Usuario(String nombre, String password, String rol) {
        this.nombre = nombre;
        this.password = password;
        this.rol = rol;
    }

    /**
     * Obtiene el nombre de usuario.
     *
     * @return El nombre de usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de usuario.
     *
     * @param nombre El nuevo nombre de usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return La contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param password La nueva contraseña del usuario.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtiene el rol asociado al usuario.
     *
     * @return El rol del usuario.
     */
    public String getRol() {
        return rol;
    }

    /**
     * Establece el rol asociado al usuario.
     *
     * @param rol El nuevo rol del usuario.
     */
    public void setRol(String rol) {
        this.rol = rol;
    }
}
