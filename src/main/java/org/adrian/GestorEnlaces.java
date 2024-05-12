package org.adrian;

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
