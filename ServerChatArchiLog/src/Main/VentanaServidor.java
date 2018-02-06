/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.DataOutputStream;
import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Eros
 */
public class VentanaServidor extends javax.swing.JFrame {

    public static int NUM_PUERTO = 44444;
    public static EstructuraFicheros EstFich;
    private ServerSocket servidor;
    private Socket s;
    private HiloServidorReceptor hiloRecep;
    private Usuario user;
    private DataOutputStream dos;

    private Date fecha;

    public VentanaServidor() {
        initComponents();
        try {
            servidor = new ServerSocket(NUM_PUERTO);
            MensajesConsola("Servidor conectado.");
        } catch (Exception ex) {
            MensajesConsola("Error al iniciar el Servidor.");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTAChat = new javax.swing.JTextArea();
        jBCerrar = new javax.swing.JButton();
        jBDesconectarCliente = new javax.swing.JButton();
        jTexto = new javax.swing.JTextField();
        jBEnviar = new javax.swing.JButton();
        jLCarpCompartida = new javax.swing.JLabel();
        jTADetalles2 = new javax.swing.JTextArea();
        jTADetalles1 = new javax.swing.JTextArea();
        jBEsperarCliente = new javax.swing.JButton();
        jBCompartCarp = new javax.swing.JButton();
        jLEstado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        jTAChat.setColumns(20);
        jTAChat.setRows(5);
        jScrollPane1.setViewportView(jTAChat);

        jBCerrar.setText("Cerrar Aplicación");
        jBCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCerrarActionPerformed(evt);
            }
        });

        jBDesconectarCliente.setText("Desconectar Cliente");
        jBDesconectarCliente.setEnabled(false);
        jBDesconectarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBDesconectarClienteActionPerformed(evt);
            }
        });

        jBEnviar.setText("Enviar");
        jBEnviar.setEnabled(false);
        jBEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEnviarActionPerformed(evt);
            }
        });

        jLCarpCompartida.setText("Ninguna carpeta compartida");
        jLCarpCompartida.setEnabled(false);

        jTADetalles2.setEditable(false);
        jTADetalles2.setColumns(20);
        jTADetalles2.setRows(5);
        jTADetalles2.setPreferredSize(new java.awt.Dimension(160, 90));

        jTADetalles1.setEditable(false);
        jTADetalles1.setColumns(20);
        jTADetalles1.setRows(5);
        jTADetalles1.setPreferredSize(new java.awt.Dimension(160, 90));

        jBEsperarCliente.setText("Esperar Cliente");
        jBEsperarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEsperarClienteActionPerformed(evt);
            }
        });

        jBCompartCarp.setText("Selec. Carpeta Compartida");
        jBCompartCarp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCompartCarpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jTADetalles1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTADetalles2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jBDesconectarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jBCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(8, 8, 8))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jBEsperarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLEstado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLCarpCompartida, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBCompartCarp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jBEnviar)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTADetalles1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTADetalles2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jBCerrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBEsperarCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBDesconectarCliente)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBEnviar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLCarpCompartida, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBCompartCarp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCerrarActionPerformed
        try {
            dos.writeUTF("*/QUIT*");
            hiloRecep.interrupt();
            dos.close();
            s.close();
            servidor.close();
            MensajesConsola("Cliente expulsado por el servidor");
        } catch (Exception e) {
            MensajesConsola("Cliente expulsado por el servidor");
        }
        setOnBotonConectar(true);
        System.exit(0);
    }//GEN-LAST:event_jBCerrarActionPerformed

    private void jBDesconectarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBDesconectarClienteActionPerformed
        try {
            dos.writeUTF("*/QUIT*");
            hiloRecep.interrupt();
            dos.close();
            s.close();
            MensajesConsola("Cliente expulsado por el servidor");
        } catch (Exception e) {
            MensajesConsola("Cliente expulsado por el servidor");
        }
        setOnBotonConectar(true);
    }//GEN-LAST:event_jBDesconectarClienteActionPerformed

    private void jBEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEnviarActionPerformed
        String texto = jTexto.getText();
        EscribirEnChat(texto, true);
        try {
            dos.writeUTF(texto);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        jTexto.setText("");
    }//GEN-LAST:event_jBEnviarActionPerformed

    private void jBEsperarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEsperarClienteActionPerformed
        IniciarConexion();
    }//GEN-LAST:event_jBEsperarClienteActionPerformed

    private void jBCompartCarpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCompartCarpActionPerformed
        String directorio="";
        JFileChooser fileChoo = new JFileChooser();
        fileChoo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChoo.setDialogTitle("Selecciona la carpeta a compartir");
        int returnVal = fileChoo.showDialog(fileChoo, "Seleccionar");
        if (returnVal == JFileChooser.APPROVE_OPTION){
            File file = fileChoo.getSelectedFile();
            directorio = file.getAbsolutePath();
        }
        if (directorio.equals("")){
            JOptionPane.showMessageDialog(this, "Debes elegir un directorio para poder compartir.");
        }
    }//GEN-LAST:event_jBCompartCarpActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaServidor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBCerrar;
    private javax.swing.JButton jBCompartCarp;
    private javax.swing.JButton jBDesconectarCliente;
    private javax.swing.JButton jBEnviar;
    private javax.swing.JButton jBEsperarCliente;
    private javax.swing.JLabel jLCarpCompartida;
    private javax.swing.JLabel jLEstado;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTAChat;
    private javax.swing.JTextArea jTADetalles1;
    private javax.swing.JTextArea jTADetalles2;
    private javax.swing.JTextField jTexto;
    // End of variables declaration//GEN-END:variables

    private void IniciarConexion() {
        setOnBotonConectar(false);
        hiloRecep = new HiloServidorReceptor(this, s, servidor);
        hiloRecep.start();
    }

    public void TerminarConexion() {
        hiloRecep.interrupt();
        try {
            s.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void EscribirEnChat(String texto, boolean isServer) {
        jTAChat.append((isServer ? "Servidor: " : user.getNombre() + ": ") + texto + "\n");
    }

    public void MensajesConsola(String texto) {
        jTAChat.append(texto + "\n");
    }

    public void setUsuario(Usuario user) {
        this.user = user;
    }

    public void DatosUsuario() {
        jTADetalles1.setText(String.format("Nombre: %s\nDireccion: %s\nTlf: %s", user.getApellido(), user.getDireccion(), user.getTelefono()));
        jTADetalles2.setText(String.format("Ip: %s\nUsuario: %s",s.getInetAddress().toString(),user.getNombre()));
    }
    
    public void setDos(DataOutputStream dos, Socket soc){
        this.dos = dos;
        this.s = soc;
    }
    
    public void setOnBotonConectar(boolean isConectar){
        jBEsperarCliente.setEnabled(isConectar);
        jBEnviar.setEnabled(!isConectar);
        jBDesconectarCliente.setEnabled(!isConectar);
    }
}
