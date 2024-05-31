package org.adrian.view;

import org.adrian.controller.GestorUsuarios;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.net.URL;

/**
 * Panel de inicio de sesión que permite a los usuarios autenticarse en la aplicación.
 */
public class LoginPanel extends JPanel {
    private JTextField campoUsuario;
    private JPasswordField campoContrasenia;
    private GestorUsuarios gestorUsuarios;
    private AppFrame frame;

    public LoginPanel(AppFrame frame, GestorUsuarios gestorUsuarios) {
        this.frame = frame;
        this.gestorUsuarios = gestorUsuarios;
        initComponents();
    }
    /**
     * Inicializa y configura los componentes del panel de inicio de sesión.
     */
    private void initComponents() {
        setLayout(null);  // Establece un diseño nulo para posicionar los componentes manualmente

        // Etiqueta para titulo Inicio sesion
        JLabel jLabelInicioSesion = new JLabel("Inicio de Sesion");
        jLabelInicioSesion.setFont(new Font("Times New Roman", Font.BOLD, 36));
        jLabelInicioSesion.setForeground(new Color(102, 102, 255));
        jLabelInicioSesion.setBounds(100, 50, 356, 50);
        add(jLabelInicioSesion);

        //-----------------
        //--------- Imagen hackeando a la derecha
        JLabel imageLabel = new JLabel();
        URL imageURL = getClass().getResource("/org/adrian/imagenes/hack2.jpg");
        if (imageURL != null) {
            ImageIcon imageIcon = new ImageIcon(imageURL);
            imageLabel.setIcon(imageIcon);
            imageLabel.setBounds(600, 60, imageIcon.getIconWidth(), imageIcon.getIconHeight());
            add(imageLabel);
        }
        //------------------ Imagen esquina inferior derecha

        URL imageURLDaniCast = getClass().getResource("/org/adrian/imagenes/DaniCast.png");
        ImageIcon iconoDaniCast = new ImageIcon(imageURLDaniCast);
        JLabel imageLabelDaniCast = new JLabel(iconoDaniCast);


            //ImageIcon imagenDaniCast = new ImageIcon(imageURLDaniCast);
            //imageLabelDaniCast.setIcon(imagenDaniCast);
            // Calcular las coordenadas para la esquina inferior derecha
            int x = frame.getWidth() - iconoDaniCast.getIconWidth() -17;
            int y = frame.getHeight() - iconoDaniCast.getIconHeight() -40;
            imageLabelDaniCast.setBounds(x, y, iconoDaniCast.getIconWidth(), iconoDaniCast.getIconHeight());

            // Crear un borde alrededor de la imagen
            Border borde = BorderFactory.createLineBorder(Color.BLACK, 2); // Puedes ajustar el color y el grosor del borde según tus preferencias
            imageLabelDaniCast.setBorder(borde);

            //mouse listener para cuando se haga click en la imagen
            imageLabelDaniCast.addMouseListener(new MouseAdapter() {
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
                    imageLabelDaniCast.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // Cambia el cursor del ratón cuando sale de la imagen
                    imageLabelDaniCast.setCursor(Cursor.getDefaultCursor());
                }
            });
            add(imageLabelDaniCast);

        //----------------------------

        // Etiqueta para el campo de usuario
        JLabel jLabelUsuario = new JLabel("Usuario:");
        jLabelUsuario.setFont(new Font("Times New Roman", Font.BOLD, 24));
        jLabelUsuario.setBounds(50, 150, 100, 25);
        add(jLabelUsuario); // Agrega la etiqueta al panel

        // Campo de texto para el usuario
        campoUsuario = new JTextField();
        campoUsuario.setFont(new Font("Times New Roman", Font.BOLD, 24));
        campoUsuario.setBounds(jLabelUsuario.getX() + jLabelUsuario.getWidth() + 10, 145, 300, 30);
        add(campoUsuario); // Agregar el campo de texto al panel

        // Etiqueta para el campo de contraseña
        JLabel jLabelcontrasena = new JLabel("Contraseña:");
        jLabelcontrasena.setFont(new Font("Times New Roman", Font.BOLD, 24));
        jLabelcontrasena.setBounds(50, 250, 150, 25);
        add(jLabelcontrasena); // Agrega la etiqueta al panel

        // Campo de contraseña
        campoContrasenia = new JPasswordField();
        campoContrasenia.setFont(new Font("Times New Roman", Font.BOLD, 24));
        campoContrasenia.setBounds(180, 250, 300, 30);
        add(campoContrasenia); // Agrego el campo de contraseña al panel

        // Botón para iniciar sesión
        JButton jButtonLogin = new JButton("Iniciar Sesion");
        jButtonLogin.setFont(new Font("Times New Roman", Font.BOLD, 20));
        jButtonLogin.setBounds(220, 350, 150, 40);
        jButtonLogin.addActionListener(e -> {
            // Comprueba si el campo usuario esta vacio
            if (campoUsuario.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(LoginPanel.this, "El campo usuario está vacío!");
                campoUsuario.grabFocus(); // Establece el foco en el campo de usuario
                return;
            }
            // comprueba si el campo contraseña esta vacio
            if (new String(campoContrasenia.getPassword()).trim().isEmpty()) {
                JOptionPane.showMessageDialog(LoginPanel.this, "El campo contraseña está vacío!");
                campoContrasenia.grabFocus();
                return;
            }
            // En el caso de que el usuario exista, comprobamos contraseña e inicia sesion o no
            boolean usuarioExiste = gestorUsuarios.autenticarUsuario(campoUsuario.getText(), new String(campoContrasenia.getPassword())) != null;
            if (usuarioExiste) {
                JOptionPane.showMessageDialog(LoginPanel.this, "Iniciado usuario: " + campoUsuario.getText());
                frame.setContentPane(new EnlacesPanel(campoUsuario.getText(), frame, gestorUsuarios));
                frame.revalidate();
            } else {
                JOptionPane.showMessageDialog(LoginPanel.this, "Usuario incorrecto!");
                campoUsuario.grabFocus();
            }
        });
        add(jButtonLogin); //Agregar el botón al panel
    }
    /**
     * Reinicio los campos de usuario y contraseña del panel de inicio de sesión.
     */
    public void resetFields() {
        campoUsuario.setText("");
        campoContrasenia.setText("");
    }
}
