package org.adrian.controller;

import org.adrian.model.Enlace;

import java.util.ArrayList;
import java.util.List;

/**
 * GestorEnlaces es una clase que se encarga de gestionar la lista de enlaces en la aplicación.
 */
public class GestorEnlaces {
    private List<Enlace> enlaces;


    /**
     * Constructor de la clase GestorEnlaces que inicializa la lista de enlaces.
     */
    public GestorEnlaces() {

        enlaces = new ArrayList<>();
    }

    /**
     * Agrega un nuevo enlace a la lista de enlaces.
     *
     * @param enlace El enlace a agregar.
     */
    public void agregarEnlace(Enlace enlace) {
        enlaces.add(enlace);
    }

    /**
     * Elimina un enlace de la lista de enlaces.
     *
     * @param enlace El enlace a eliminar.
     */
    public void eliminarEnlace(Enlace enlace)
    {
        enlaces.remove(enlace);
    }

    /**
     * Obtiene todos los enlaces almacenados en la lista.
     *
     * @return La lista de todos los enlaces.
     */
    public List<Enlace> obtenerTodosLosEnlaces() {

        return enlaces;
    }

    // Método para verificar si ya existe un enlace con un nombre dado
    public boolean existeEnlaceConNombre(String nombre) {
        for (Enlace enlace : enlaces) {
            if (enlace.getTitulo().equals(nombre)) {
                return true;
            }
        }
        return false;
    }


}
