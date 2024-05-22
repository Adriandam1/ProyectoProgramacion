package src.main.java.org.adrian.view;

import src.main.java.org.adrian.controller.GestorUsuarios;
import src.main.java.org.adrian.model.Usuario;

import javax.swing.JOptionPane;

/**
 *
 * @author Adrian
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jButtonLogin = new javax.swing.JButton();
        jLabelUsuario = new javax.swing.JLabel();
        txtUserInput = new javax.swing.JTextField();
        jLabelInicioSesion = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtPasswordInput = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N

        jButtonLogin.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jButtonLogin.setText("BotonLogin");
        jButtonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoginActionPerformed(evt);
            }
        });

        jLabelUsuario.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabelUsuario.setText("Usuario:");

        txtUserInput.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        txtUserInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserInputActionPerformed(evt);
            }
        });

        jLabelInicioSesion.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabelInicioSesion.setForeground(new java.awt.Color(102, 102, 255));
        jLabelInicioSesion.setText("Inicio de Sesion");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("Contraseña:");

        txtPasswordInput.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jLabelInicioSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGap(156, 156, 156)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jLabel1)
                                                        .addComponent(jLabelUsuario))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jButtonLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtUserInput)
                                                        .addComponent(txtPasswordInput, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE))))
                                .addContainerGap(304, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(jLabelInicioSesion)
                                .addGap(68, 68, 68)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtUserInput, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtPasswordInput, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE))
                                .addGap(32, 32, 32)
                                .addComponent(jButtonLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(127, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>

    private void jButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:

        GestorUsuarios Gusuarios = new GestorUsuarios();



        //Evitar que el usuario quede vacio
        if (txtUserInput.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "El campo usuario esta vacio!");
            txtUserInput.grabFocus();
            return;
        }
        //Evitar que la contraseña este vacia
        if (txtPasswordInput.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "El campo contraseña esta vacio!");
            txtPasswordInput.grabFocus();
            return;
        }

        //Si el inicio de sesion es correcto comprobamos si el usuario existe:
        boolean usuarioExiste = Gusuarios.iniciarSesion(txtUserInput, txtPasswordInput);
        if (usuarioExiste == true){
        //Envio de mensaje de login
        JOptionPane.showMessageDialog(rootPane, "Iniciado usuario: "+txtUserInput.getText());
        }
        //Si el usuario es incorrecto nos avisa
        else {
            JOptionPane.showMessageDialog(rootPane, "Usuario incorrecto!");
            txtUserInput.grabFocus();
        }

        /*
        //Envio de mensaje de login copy
        JOptionPane.showMessageDialog(rootPane, "Iniciado usuario: "+txtUserInput.getText());*/
    }

    private void txtUserInputActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //---------------------
        // Ejemplo de uso
        GestorUsuarios Gestor = new GestorUsuarios();

        Usuario usuario1 = new Usuario("Pepe", "123", "admin");
        Usuario usuario2 = new Usuario("Pepa", "456", "usuario");

        Gestor.agregarUsuario(usuario1);
        Gestor.agregarUsuario(usuario2);

        System.out.println(Gestor.obtenerTodosLosUsuarios());

        //----------------------

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton jButtonLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelInicioSesion;
    private javax.swing.JLabel jLabelUsuario;
    private javax.swing.JPasswordField txtPasswordInput;
    private javax.swing.JTextField txtUserInput;
    // End of variables declaration
}