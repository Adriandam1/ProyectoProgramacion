package org.adrian.controller;

import org.adrian.model.Enlace;

import java.util.ArrayList;
import java.util.List;

public class GestorEnlaces {
    private List<Enlace> enlaces;

    public GestorEnlaces() {
        enlaces = new ArrayList<>();
    }

    public void agregarEnlace(Enlace enlace) {
        enlaces.add(enlace);
    }

    public void eliminarEnlace(Enlace enlace) {
        enlaces.remove(enlace);
    }

    public List<Enlace> obtenerTodosLosEnlaces() {
        return enlaces;
    }
}
