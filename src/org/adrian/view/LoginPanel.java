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
    private JTextField userField;
    private JPasswordField passField;
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
        setLayout(null);

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

        //-----------------

        JLabel jLabelUsuario = new JLabel("Usuario:");
        jLabelUsuario.setFont(new Font("Times New Roman", Font.BOLD, 24));
        jLabelUsuario.setBounds(50, 150, 100, 25);
        add(jLabelUsuario);

        userField = new JTextField();
        userField.setFont(new Font("Times New Roman", Font.BOLD, 24));
        userField.setBounds(jLabelUsuario.getX() + jLabelUsuario.getWidth() + 10, 145, 300, 30);
        add(userField);

        JLabel jLabel1 = new JLabel("Contraseña:");
        jLabel1.setFont(new Font("Times New Roman", Font.BOLD, 24));
        jLabel1.setBounds(50, 250, 150, 25);
        add(jLabel1);

        passField = new JPasswordField();
        passField.setFont(new Font("Times New Roman", Font.BOLD, 24));
        passField.setBounds(180, 250, 300, 30);
        add(passField);

        JButton jButtonLogin = new JButton("Iniciar Sesion");
        jButtonLogin.setFont(new Font("Times New Roman", Font.BOLD, 20));
        jButtonLogin.setBounds(220, 350, 150, 40);
        jButtonLogin.addActionListener(e -> {
            // Comprueba si el campo usuario esta vacio
            if (userField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(LoginPanel.this, "El campo usuario está vacío!");
                userField.grabFocus();
                return;
            }
            // comprueba si el campo contraseña esta vacio
            if (new String(passField.getPassword()).trim().isEmpty()) {
                JOptionPane.showMessageDialog(LoginPanel.this, "El campo contraseña está vacío!");
                passField.grabFocus();
                return;
            }
            // En el caso de que el usuario exista, comprobamos contraseña e inicia sesion o no
            boolean usuarioExiste = gestorUsuarios.autenticarUsuario(userField.getText(), new String(passField.getPassword())) != null;
            if (usuarioExiste) {
                JOptionPane.showMessageDialog(LoginPanel.this, "Iniciado usuario: " + userField.getText());
                frame.setContentPane(new EnlacesPanel(userField.getText(), frame, gestorUsuarios));
                frame.revalidate();
            } else {
                JOptionPane.showMessageDialog(LoginPanel.this, "Usuario incorrecto!");
                userField.grabFocus();
            }
        });
        add(jButtonLogin);
    }
    /**
     * Reinicio los campos de usuario y contraseña del panel de inicio de sesión.
     */
    public void resetFields() {
        userField.setText("");
        passField.setText("");
    }
}
