package org.adrian.model;

/**
 * La clase Enlace representa un enlace web con un título, una URL y un comentario asociado.
 */
public class Enlace {
    private String nombre;
    private String url;
    private String comentario;

    /**
     * Constructor de la clase Enlace que inicializa los atributos del enlace.
     *
     * @param nombre     El título del enlace.
     * @param url        La URL del enlace.
     * @param comentario El comentario asociado al enlace.
     */
    public Enlace(String nombre, String url, String comentario) {
        this.nombre = nombre;
        this.url = url;
        this.comentario = comentario;
    }

    /**
     * Obtiene el título del enlace.
     *
     * @return El título del enlace.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el título del enlace.
     *
     * @param nombre El nuevo título del enlace.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la URL del enlace.
     *
     * @return La URL del enlace.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Establece la URL del enlace.
     *
     * @param url La nueva URL del enlace.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Obtiene el comentario asociado al enlace.
     *
     * @return El comentario del enlace.
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * Establece el comentario asociado al enlace.
     *
     * @param comentario El nuevo comentario del enlace.
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
