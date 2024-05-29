package org.adrian.view;

import org.adrian.controller.GestorEnlaces;
import org.adrian.controller.GestorUsuarios;
import org.adrian.model.Enlace;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.net.URL;


/**
 * El panel de enlaces es responsable de mostrar los enlaces disponibles para un usuario y permitir la gestión de estos enlaces.
 */
public class EnlacesPanel extends JPanel {
    private GestorEnlaces gestorEnlaces;
    private JTextPane textPane;
    private JLabel userLabel;
    private AppFrame frame;
    private GestorUsuarios gestorUsuarios;


    /**
     * Constructor para el panel de enlaces que inicializa el panel con los enlaces del usuario.
     *
     * @param usuario        El nombre de usuario.
     * @param frame          El marco de la aplicación.
     */
    public EnlacesPanel(String usuario, AppFrame frame, GestorUsuarios gestorUsuarios) {
        this.frame = frame;
        this.gestorUsuarios = gestorUsuarios; // Guarda el GestorUsuarios recibido
        gestorEnlaces = new GestorEnlaces();
        initialize(usuario);
    }
    /**
     * Inicializa los componentes del panel.
     *
     * @param usuario El nombre de usuario.
     */
    private void initialize(String usuario) {
        setLayout(null);

        // Label para mostrar el usuario que ha iniciado sesión
        userLabel = new JLabel("Usuario: " + usuario);
        userLabel.setBounds(10, 10, 200, 25);
        userLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
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

        //--------- Imagen hackeando a la derecha
        JLabel imageLabel = new JLabel();
        URL imageURL = getClass().getResource("/org/adrian/imagenes/hack1.jpg");
        if (imageURL != null) {
            ImageIcon imageIcon = new ImageIcon(imageURL);
            imageLabel.setIcon(imageIcon);
            imageLabel.setBounds(550, 60, imageIcon.getIconWidth(), imageIcon.getIconHeight());
            add(imageLabel);
        }
        //------------
        //------------ Imagen esquina inferior derecha

        URL imageURL2 = getClass().getResource("/org/adrian/imagenes/DaniCast.png");
        ImageIcon icon2 = new ImageIcon(imageURL2);
        JLabel imageLabel2 = new JLabel(icon2);



            ImageIcon imageIcon2 = new ImageIcon(imageURL2);
            imageLabel2.setIcon(imageIcon2);
            // Calcular las coordenadas para la esquina inferior derecha
            int x = frame.getWidth() - icon2.getIconWidth() -17;
            int y = frame.getHeight() - icon2.getIconHeight() -40;
            imageLabel2.setBounds(x, y, icon2.getIconWidth(), icon2.getIconHeight());

            // Crear un borde alrededor de la imagen
            Border border = BorderFactory.createLineBorder(Color.BLACK, 2); // Puedes ajustar el color y el grosor del borde según tus preferencias
            imageLabel2.setBorder(border);

            //mouse listener para cuando se haga click en la imagen
            imageLabel2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        // Abre la página web en el navegador predeterminado
                        Desktop.getDesktop().browse(new URI("https://www.danielcastelao.org/"));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    // Cambia el cursor del ratón cuando pasa sobre la imagen
                    imageLabel2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // Cambia el cursor del ratón cuando sale de la imagen
                    imageLabel2.setCursor(Cursor.getDefaultCursor());
                }
            });
            add(imageLabel2);
        //------------

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
            /**
             * Maneja el evento de clic en el botón de cierre de sesión.
             *
             * @param e El evento de acción.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mensaje para confirmar el cierre de sesión
                int option = JOptionPane.showConfirmDialog(EnlacesPanel.this, "¿Está seguro que desea cerrar sesión?", "Cerrar Sesión", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    // Cambia el panel de contenido a LoginPanel
                    frame.setContentPane(new LoginPanel(frame, gestorUsuarios)); //
                    frame.revalidate();
                    // Restablecer los campos de texto del panel de inicio de sesión
                    ((LoginPanel)frame.getContentPane()).resetFields();
                }
            }
        });

        updateNoteList();
    }

    /**
     * Actualiza la lista de enlaces mostrados en el panel.
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
