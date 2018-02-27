/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Locale;
import javax.crypto.Cipher;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Eros
 */
public class VentanaCliente extends javax.swing.JFrame {

    //Inicio todas las variables
    Socket socket;
    DataInputStream dis;
    DataOutputStream dos;
    String User;
    HiloClienteReceptor hiloRecep;
    public static int NUM_PUERTO_MENS = 44444;
    public static int NUM_PUERTO_FICH = 55555;
    //Esta es mi clase para enviar la estructura de ficheros.
    private Archivos archi;
    //Estos son los Objetos para encriptar y desencriptar
    private Cipher encrip;
    private Cipher desencrip;

    public VentanaCliente() {
        initComponents();
        escribirLog("Cliente iniciado");
        obtenerClave();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLEstado = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTAChat = new javax.swing.JTextArea();
        jBCerrar = new javax.swing.JButton();
        jTexto = new javax.swing.JTextField();
        jBEnviar = new javax.swing.JButton();
        jLUsuario = new javax.swing.JLabel();
        jBConectar = new javax.swing.JButton();
        jBDesconectar = new javax.swing.JButton();
        jLCarpCompartida = new javax.swing.JLabel();
        jBCompartCarp = new javax.swing.JButton();

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

        jBEnviar.setText("Enviar");
        jBEnviar.setEnabled(false);
        jBEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEnviarActionPerformed(evt);
            }
        });

        jBConectar.setText("Conectar");
        jBConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBConectarActionPerformed(evt);
            }
        });

        jBDesconectar.setText("Desconectar");
        jBDesconectar.setEnabled(false);
        jBDesconectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBDesconectarActionPerformed(evt);
            }
        });

        jLCarpCompartida.setText("Ninguna carpeta compartida");
        jLCarpCompartida.setEnabled(false);

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
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLCarpCompartida, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBCompartCarp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jBConectar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBDesconectar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(155, 155, 155)
                        .addComponent(jBCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBEnviar)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLEstado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jBCerrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jBConectar)
                    .addComponent(jBDesconectar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBEnviar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLCarpCompartida, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBCompartCarp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCerrarActionPerformed
        //Enviamos el cierre, cerramos los sockets y cerramos la app
        try {
            enviarMensajeAServidor("*/QUIT*");
            dos.close();
            dis.close();
            socket.close();
            escribirLog("Cliente cerrado");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
    }//GEN-LAST:event_jBCerrarActionPerformed

    private void jBConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBConectarActionPerformed
        PedirCredenciales();
    }//GEN-LAST:event_jBConectarActionPerformed

    private void jBDesconectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBDesconectarActionPerformed
        //Enviamos el cierre y cerramos el socket y habilitamos los botones
        try {
            enviarMensajeAServidor("*/QUIT*");
            socket.close();
            setOnBotonConectar(true);
            escribirLog("Desconectado del servidor");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jBDesconectarActionPerformed

    private void jBEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEnviarActionPerformed
        //Leemos el texto del jTexto, lo escribimos en el chat
        //y se lo pasamos al metodo enviar
        //tambien vaciamos el Jtexto
        String texto = jTexto.getText();
        EscribirEnChat(texto, false);
        try {
            enviarMensajeAServidor(texto);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        jTexto.setText("");
    }//GEN-LAST:event_jBEnviarActionPerformed

    private void jBCompartCarpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCompartCarpActionPerformed
        //Lanzamos un JFileChooser y hacemos que solo muestre carpetas
        JFileChooser fileChoo = new JFileChooser();
        fileChoo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChoo.setDialogTitle("Selecciona la carpeta a compartir");
        int returnVal = fileChoo.showDialog(fileChoo, "Seleccionar");
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            //Cuando el usuario selecciona un archivo, lo convierte en la clase Archivos
            //iniciamos el hilo al que le pasamos la estructura de ficheros y la ventana
            File ark = fileChoo.getSelectedFile();
            archi = new Archivos(ark);
            jLCarpCompartida.setText(archi.getName());
            HiloClienteArchivos hiloArchi = new HiloClienteArchivos(this, archi);
            hiloArchi.start();
        } else {
            JOptionPane.showMessageDialog(this, "Debes elegir un directorio para poder compartir.");
        }
    }//GEN-LAST:event_jBCompartCarpActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBCerrar;
    private javax.swing.JButton jBCompartCarp;
    private javax.swing.JButton jBConectar;
    private javax.swing.JButton jBDesconectar;
    private javax.swing.JButton jBEnviar;
    private javax.swing.JLabel jLCarpCompartida;
    private javax.swing.JLabel jLEstado;
    private javax.swing.JLabel jLUsuario;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTAChat;
    private javax.swing.JTextField jTexto;
    // End of variables declaration//GEN-END:variables

    /**
     * En este metodo realizo la conexion y envio el resultado de los 2 Joption
     * en una sola cadena luego lee la respuesta del servidor que será un true,
     * si se validó y false en caso contrario si la validación es correcta, se
     * inicia el hiloReceptor
     */
    private void PedirCredenciales() {
        try {
            setOnBotonConectar(false);
            socket = new Socket("127.0.0.1", NUM_PUERTO_MENS);
            //new Socket(InetAddress.getByAddress("188.127.166.98", new byte[]{(byte)188,(byte)127,(byte)166,(byte)98}), NUM_PUERTO_MENS);
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            String user = JOptionPane.showInputDialog(this, "Intoduzca Usuario", "Credenciales", JOptionPane.INFORMATION_MESSAGE);
            String pass = JOptionPane.showInputDialog(this, "Intoduzca Contraseña", "Credenciales", JOptionPane.INFORMATION_MESSAGE);
            if (user != null && pass != null) {
                enviarMensajeAServidor(user + "##" + pass);
                if (dis.readBoolean()) {
                    MensajesConsola("Logeado con el servidor!");
                    escribirLog("logeado como " + user);
                    jBDesconectar.setEnabled(true);
                    User = user;
                    setOnBotonConectar(false);
                    ArrancarHilos();
                } else {
                    MensajesConsola("Credenciales Erroneos");
                    escribirLog("Login fallido");
                    setOnBotonConectar(true);
                }
            } else {
                enviarMensajeAServidor("**NULL**" + "##" + "**NULL**");
                MensajesConsola("Login cancelado.");
                escribirLog("Login cancelado");
                setOnBotonConectar(true);
            }
        } catch (Exception ex) {
            MensajesConsola("Error al intentar conectar. ¿Esta el servidor online?");
            setOnBotonConectar(true);
            ex.printStackTrace();
        }
    }

    //Arranco el HiloClienteReceptor pasandole el socket,
    //el DataInputStream y el Cipher para desencriptar
    private void ArrancarHilos() {
        hiloRecep = new HiloClienteReceptor(this, socket, dis, desencrip);
        hiloRecep.start();
    }

    /**
     * Metodo que escribe en la ventana de chat
     *
     * @param texto el mensaje a escribir
     * @param isServer un boolean que indica si es el servidor o el cliente
     */
    public void EscribirEnChat(String texto, boolean isServer) {
        jTAChat.append((isServer ? "Servidor: " : User + ": ") + texto + "\n");
    }

    /**
     * Metodo que añade texto al chat sin que aparezca server o cliente
     *
     * @param texto el menjase a escribir
     */
    public void MensajesConsola(String texto) {
        jTAChat.append(texto + "\n");
    }

    /**
     * Un metodo que habilita y deshabilita los botones de conexion y enviar
     *
     * @param isConectar el boolean que dice si sí o no
     */
    public void setOnBotonConectar(boolean isConectar) {
        jBConectar.setEnabled(isConectar);
        jBEnviar.setEnabled(!isConectar);
        jBDesconectar.setEnabled(!isConectar);
    }

    /**
     * Metodo que escribe en la barra de estado de archivos
     *
     * @param stado el mensaje a escrbir
     */
    public void barraEstado(String stado) {
        jLEstado.setText(stado);
    }

    /**
     * Este metodo fue una primera version del log y no la uso para nada, se me
     * olvidó borrarla
     */
    public void appendLog(String texto) {
        File archi = new File("Log.txt");
        try {
            if (!archi.exists()) {
                archi.createNewFile();
            }
            FileWriter fw = new FileWriter(archi);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.append(texto);
            bw.newLine();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Metodo que escribe en el log
     *
     * @param texto el texto a escribir
     */
    public void escribirLog(String texto) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yy HH:mm:ss", Locale.US);
        String fecha = formatter.format(new Date(System.currentTimeMillis()));
        File file = new File("Log.txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            PrintWriter pw = new PrintWriter(bw);
            pw.println(fecha + "-->" + texto);
            pw.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Obtiene la clave del fichero clave.key y la carga en los Cipher si no la
     * tiene envia un aviso para copiar la clave o descargarla(ya no, antes si
     * porque la validación iva sin encriptar.)
     */
    private void obtenerClave() {
        File archivo = new File("Clave");
        if (!archivo.exists()) {
            archivo.mkdir();
        }
        File archivoClave = new File("Clave\\clave.key");
        if (archivoClave.exists()) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivoClave));
                Key clave = (Key) ois.readObject();
                encrip = Cipher.getInstance("AES/ECB/PKCS5Padding");
                desencrip = Cipher.getInstance("AES/ECB/PKCS5Padding");
                encrip.init(Cipher.ENCRYPT_MODE, clave);
                desencrip.init(Cipher.DECRYPT_MODE, clave);
                escribirLog("Clave cargada");
            } catch (Exception ex) {
                EscribirEnChat("Error al cargar claves", true);
                ex.printStackTrace();
            }
        } else {
            EscribirEnChat("Faltan claves de encriptación, descaguelas o copielas manualmente\n en \\Claves y reinicie el cliente (desde la misma ubicación en el servidor)", true);
        }
    }

    /**
     * Este metodo envia los mensajes al cliente, pero primero los encripta.
     *
     * Este metodo me dio muchisimos problemas, porque el Cipher con decode pide
     * que el byte[] sea de un tamaño multiplo de 16 o algo así. intenté
     * mediante varios metodos hacer que fuese así y no encontré solución así
     * que busqué en internet otras formas de cifrar. Encontré Base64 que aunque
     * no es un cifrado como tal, convierte byte[] a un string enviable por UTF
     * y del mismo modo en el otro lado lo convierte de nuevo en byte[]
     * conservando el cifrado. es una encapsulacion: base64(cifrado(mensaje))
     *
     * @param texto el texto a enviar
     */
    private void enviarMensajeAServidor(String texto) {
        try {
            dos.writeUTF(Base64.getEncoder().encodeToString(encrip.doFinal(texto.getBytes())));
            dos.flush();
        } catch (Exception ex) {
            System.out.println("Error al enviar mensaje");
        }
    }
}
