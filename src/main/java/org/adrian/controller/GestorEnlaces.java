package src.main.java.org.adrian.controller;

import src.main.java.org.adrian.model.Enlace;
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

    // Implementar metodos...
}
