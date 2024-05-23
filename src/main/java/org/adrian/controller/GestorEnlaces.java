package src.main.java.org.adrian.controller;

import src.main.java.org.adrian.model.Enlace;
import java.util.ArrayList;
import java.util.List;

public class GestorEnlaces {
    private List<Enlace> enlaces = new ArrayList<>();

    /**
     *
     * Adds a new note to the list.
     *
     * @param enlace Note to be added.
     */
    public void agregarEnlace(Enlace enlace) {
        enlaces.add(enlace);
    }

    /**
     * Removes a note from the list.
     *
     * @param enlace Note to be removed.
     */
    public void eliminarEnlace(Enlace enlace) {
        enlaces.remove(enlace);
    }

    /**
     * Gets the list of all notes.
     *
     * @return List of notes.
     */
    public List<Enlace> obtenerTodosLosEnlaces() {
        return enlaces;
    }
}