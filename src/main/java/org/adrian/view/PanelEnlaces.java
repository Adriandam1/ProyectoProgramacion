package src.main.java.org.adrian.view;

import src.main.java.org.adrian.controller.GestorEnlaces;
import src.main.java.org.adrian.model.Enlace;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelEnlaces {
    class NoteManagerPanel extends JPanel {
        private GestorEnlaces gestorEnlaces;
        private JTextPane textPane;

        /**
         * Constructor de la clase NoteManagerPanel.
         * Inicializa el gestor de enlaces y configura la GUI.
         */
        public NoteManagerPanel() {
            gestorEnlaces = new GestorEnlaces();
            initialize2();
        }

        private void initialize2() {
            setLayout(null);

            // Área de texto para mostrar las notas
            textPane = new JTextPane();
            textPane.setContentType("text/html");
            textPane.setEditable(false);
            textPane.setBounds(10, 10, 560, 250);
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
            scrollPane.setBounds(10, 10, 560, 250);
            add(scrollPane);

            // Botón para añadir una nota
            JButton btnAddNote = new JButton("Añadir enlace:");
            btnAddNote.setBounds(10, 270, 100, 25);
            add(btnAddNote);

            // Botón para eliminar una nota
            JButton btnDeleteNote = new JButton("Borrar Enlace");
            btnDeleteNote.setBounds(120, 270, 120, 25);
            add(btnDeleteNote);

            // Listener para el botón de añadir nota
            btnAddNote.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String nombre = JOptionPane.showInputDialog("Pon nombre al enlace:");
                    String url = JOptionPane.showInputDialog("Escribe la URL:");
                    String descripcion = JOptionPane.showInputDialog("Algun comentario:");
                    gestorEnlaces.agregarEnlace(new Enlace(nombre, url, descripcion));
                    updateNoteList();
                }
            });

            // Listener para el botón de eliminar nota
            btnDeleteNote.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String nombre = JOptionPane.showInputDialog("Que enlace quieres eliminar(nombre):");
                    Enlace enlaceAEliminar = gestorEnlaces.obtenerTodosLosEnlaces().stream()
                            .filter(enlace -> enlace.getTitulo().equals(nombre))
                            .findFirst()
                            .orElse(null);
                    if (enlaceAEliminar != null) {
                        gestorEnlaces.eliminarEnlace(enlaceAEliminar);
                        updateNoteList();
                    } else {
                        JOptionPane.showMessageDialog(NoteManagerPanel.this, "Enlace no encontrado!");
                    }
                }
            });

            updateNoteList(); // Muestra las notas al iniciar
        }

        /**
         * Actualiza la lista de notas mostradas en el área de texto.
         */
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
}
