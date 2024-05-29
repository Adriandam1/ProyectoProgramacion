package org.adrian.view;

import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame {
    public AppFrame() {

        //Utilizando Toolkit mido la pantalla y crea el frame del tamaño buscado
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit mipantalla = Toolkit.getDefaultToolkit();
        Dimension tamanoPantalla = mipantalla.getScreenSize();
        int alturaPantalla = tamanoPantalla.height;
        int anchoPantalla = tamanoPantalla.width;

        //Tamaño ventana = /2 pantalla, localizacion centro de la pantalla
        setSize(anchoPantalla / 2, alturaPantalla / 2);
        setLocation(anchoPantalla / 4, alturaPantalla / 4);
        setResizable(true);
        setTitle("Trabajo de Programacion");
        setLayout(new BorderLayout());
        // Añadimos icono esquina superior izquierda;
        Image miIcono = mipantalla.getImage("src/org/adrian/Imagenes/DaniCast.png");
        setIconImage(miIcono);
    }
}
