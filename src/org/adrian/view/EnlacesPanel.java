package org.adrian.view;

import org.adrian.controller.GestorEnlaces;
import org.adrian.controller.GestorUsuarios;
import org.adrian.model.Enlace;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnlacesPanel extends JPanel {
    private GestorEnlaces gestorEnlaces;
    private JTextPane textPane;
    private JLabel userLabel;
    private AppFrame frame;
    private GestorUsuarios gestorUsuarios;

    public EnlacesPanel(String usuario, AppFrame frame, GestorUsuarios gestorUsuarios) {
        this.frame = frame;
        this.gestorUsuarios = gestorUsuarios; // Guarda el GestorUsuarios recibido
        gestorEnlaces = new GestorEnlaces();
        initialize(usuario);
    }

    private void initialize(String usuario) {
        setLayout(null);

        // Label para mostrar el usuario que ha iniciado sesión
        userLabel = new JLabel("Usuario: " + usuario);
        userLabel.setBounds(10, 10, 200, 25);
        add(userLabel);

        JButton logoutButton = new JButton("Cerrar Sesión");
        logoutButton.setBounds(400, 10, 130, 25);
        add(logoutButton);

        textPane = new JTextPane();
        textPane.setContentType("text/html");
        textPane.setEditable(false);
        textPane.setBounds(10, 40, 560, 250);
        textPane.addHyperlinkListener(new HyperlinkListener() {
            @Override
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                    try {
                        java.awt.Desktop.getDesktop().browse(e.getURL().toURI());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setBounds(10, 40, 560, 250);
        add(scrollPane);

        JButton btnAddNote = new JButton("Añadir enlace");
        btnAddNote.setBounds(10, 300, 130, 25);
        add(btnAddNote);

        JButton btnDeleteNote = new JButton("Borrar enlace");
        btnDeleteNote.setBounds(150, 300, 120, 25);
        add(btnDeleteNote);

        btnAddNote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = JOptionPane.showInputDialog("Pon nombre al enlace:");
                String url = JOptionPane.showInputDialog("Escribe la URL:");
                String descripcion = JOptionPane.showInputDialog("Escribe algún comentario:");
                gestorEnlaces.agregarEnlace(new Enlace(nombre, url, descripcion));
                updateNoteList();
            }
        });

        btnDeleteNote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = JOptionPane.showInputDialog("Ingrese el nombre del enlace a eliminar:");
                Enlace enlaceAEliminar = gestorEnlaces.obtenerTodosLosEnlaces().stream()
                        .filter(enlace -> enlace.getTitulo().equals(nombre))
                        .findFirst()
                        .orElse(null);
                if (enlaceAEliminar != null) {
                    gestorEnlaces.eliminarEnlace(enlaceAEliminar);
                    updateNoteList();
                } else {
                    JOptionPane.showMessageDialog(EnlacesPanel.this, "Enlace no encontrado!");
                }
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(EnlacesPanel.this, "¿Está seguro que desea cerrar sesión?", "Cerrar Sesión", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    frame.setContentPane(new LoginPanel(frame, gestorUsuarios)); // Utiliza el mismo GestorUsuarios
                    frame.revalidate();
                    // Restablecer los campos de texto del panel de inicio de sesión
                    ((LoginPanel)frame.getContentPane()).resetFields();
                }
            }
        });

        updateNoteList();
    }

    private void updateNoteList() {
        StringBuilder html = new StringBuilder("<html><body>");
        for (Enlace enlace : gestorEnlaces.obtenerTodosLosEnlaces()) {
            html.append("<p>")
                    .append(enlace.getTitulo()).append(" - ")
                    .append("<a href='").append(enlace.getUrl()).append("'>").append(enlace.getUrl()).append("</a>")
                    .append(" - ").append(enlace.getComentario())
                    .append("</p>");
        }
        html.append("</body></html>");
        textPane.setText(html.toString());
    }
}
