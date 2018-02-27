/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
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
public class VentanaServidor extends javax.swing.JFrame {

    //Inicio todas las variables
    public static int NUM_PUERTO_MENS = 44444;
    public static int NUM_PUERTO_FICH = 55555;
    //Esto lo copié del libro y se me ha olvidado borrarlo.
    //No me gustó la clase e hice la mia propia
    public static EstructuraFicheros EstFich;
    private ServerSocket servidor;
    private Socket socket;
    private HiloServidorReceptor hiloRecep;
    private Usuario user;
    private DataOutputStream dos;
    //Esta es mi clase para enviar la estructura de ficheros.
    private Archivos archi;
    //Estos son los Objetos para encriptar y desencriptar
    private Cipher encrip;
    private Cipher desencrip;

    public VentanaServidor() {
        initComponents();

        //Inicio el socket servidor
        try {
            servidor = new ServerSocket(NUM_PUERTO_MENS);
            MensajesConsola("Servidor conectado.");
            escribirLog("Servidor iniciado");
        } catch (Exception ex) {
            MensajesConsola("Error al iniciar el Servidor.");
        }
        //Obtenemos la clave
        obtenerClave();
    }

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
        //Enviamos el cierre, cerramos el hilo y los sockets y cerramos la app
        try {
            enviarMensajeACliente("*/QUIT*");
            hiloRecep.interrupt();
            dos.close();
            socket.close();
            servidor.close();
            MensajesConsola("Cliente expulsado por el servidor");
            escribirLog("Cliente desconectado");
        } catch (Exception e) {
            MensajesConsola("Cliente expulsado por el servidor");
            escribirLog("Cliente desconectado");
        }
        escribirLog("Servidor cerrado");
        setOnBotonConectar(true);
        System.exit(0);
    }//GEN-LAST:event_jBCerrarActionPerformed

    private void jBDesconectarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBDesconectarClienteActionPerformed
        //Enviamos el cierre, cerramos el hilo y los sockets
        //y habilitamos los botones para esperar un nuevo cliente.
        try {
            enviarMensajeACliente("*/QUIT*");
            hiloRecep.interrupt();
            dos.close();
            socket.close();
            MensajesConsola("Cliente expulsado por el servidor");
        } catch (Exception e) {
            MensajesConsola("Conexion cancelada");
        }
        escribirLog("Cliente desconectado");
        setOnBotonConectar(true);
    }//GEN-LAST:event_jBDesconectarClienteActionPerformed

    private void jBEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEnviarActionPerformed
        //Leemos el texto del jTexto, lo escribimos en el chat
        //y se lo pasamos al metodo enviar
        //tambien vaciamos el Jtexto
        String texto = jTexto.getText();
        EscribirEnChat(texto, true);
        try {
            enviarMensajeACliente(texto);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        jTexto.setText("");
    }//GEN-LAST:event_jBEnviarActionPerformed

    private void jBEsperarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEsperarClienteActionPerformed
        //Llamamos al metodo iniciar conexion
        IniciarConexion();
    }//GEN-LAST:event_jBEsperarClienteActionPerformed

    private void jBCompartCarpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCompartCarpActionPerformed
        //Lanzamos un JFileChooser y hacemos que solo muestre carpetas
        JFileChooser fileChoo = new JFileChooser();
        fileChoo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChoo.setDialogTitle("Selecciona la carpeta a compartir");
        int returnVal = fileChoo.showDialog(fileChoo, "Seleccionar");
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            //Cuando el usuario selecciona un archivo, lo convierte en la clase Archivos
            //iniciamos el hilo al que le pasamos la estructura de ficheros y la ventana
            archi = new Archivos(fileChoo.getSelectedFile());
            jLCarpCompartida.setText(archi.getName());
            HiloServidorArchivos hiloArchi = new HiloServidorArchivos(this, archi);
            hiloArchi.start();
        } else {
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

    /**
     * Iniciamos el hilo que lee los mensajes, que también será el encargado de
     * iniciar y validar la conexion
     */
    private void IniciarConexion() {
        setOnBotonConectar(false);
        hiloRecep = new HiloServidorReceptor(this, socket, servidor, desencrip);
        hiloRecep.start();
    }

    /**
     * Detiene el hiloReceptor No estoy seguro de si funciona correctamente. Lo
     * llamo desdeHiloServidorReceptor.
     */
    public void TerminarConexion() {
        hiloRecep.interrupt();
        try {
            dos.close();
            socket.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Metodo que escribe en la ventana de chat
     *
     * @param texto el mensaje a escribir
     * @param isServer un boolean que indica si es el servidor o el cliente
     */
    public void EscribirEnChat(String texto, boolean isServer) {
        jTAChat.append((isServer ? "Servidor: " : user.getNombre() + ": ") + texto + "\n");
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
     * Metodo que recibe el usuario validado por el HiloReceptor
     *
     * @param user el user
     */
    public void setUsuario(Usuario user) {
        this.user = user;
    }

    /**
     * Metodo que escribe en los cuadros correspondientes los datos del usuario
     * y conexion
     */
    public void DatosUsuario() {
        jTADetalles1.setText(String.format("Nombre: %s\nDireccion: %s\nTlf: %s", user.getApellido(), user.getDireccion(), user.getTelefono()));
        jTADetalles2.setText(String.format("Ip: %s\nUsuario: %s", socket.getInetAddress().toString(), user.getNombre()));
        escribirLog(user.getNombre() + " logeado.");
    }

    /**
     * Metodo que establece el DataOutput y el Socket abiertos por el
     * HiloReceptor (lo hice así porque me daba errores si creaba el "dos" desde
     * aqui directamente)
     *
     * @param dos el DataOutput
     * @param soc el Socket
     */
    public void setDos(DataOutputStream dos, Socket soc) {
        this.dos = dos;
        this.socket = soc;
    }

    /**
     * Un metodo que habilita y deshabilita los botones de conexion y enviar
     *
     * @param isConectar el boolean que dice si sí o no
     */
    public void setOnBotonConectar(boolean isConectar) {
        jBEsperarCliente.setEnabled(isConectar);
        jBEnviar.setEnabled(!isConectar);
        jBDesconectarCliente.setEnabled(!isConectar);
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
     * Metodo que escribe en el log
     *
     * @param texto el texto a escribir
     */
    public void escribirLog(String texto) {
        //Creo una fecha y le doy formato 24H para el log
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yy HH:mm:ss", Locale.US);
        String fecha = formatter.format(new Date(System.currentTimeMillis()));
        File file = new File("Log.txt");
        //Si el archivo no existe, se crea
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
     * Obtiene la clave del fichero clave.key y la carga en los Cipher
     */
    private void obtenerClave() {
        File archivoClave = new File("Clave\\clave.key");
        //Si no existe la clave, se llama a la clase GeneradorClave para generarla
        //mediante un metodo estático
        if (!archivoClave.exists()) {
            GeneradorClave.generarClave();
            escribirLog("Clave generada");
        }
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
    private void enviarMensajeACliente(String texto) {
        try {
            dos.writeUTF(Base64.getEncoder().encodeToString(encrip.doFinal(texto.getBytes())));
            dos.flush();
        } catch (Exception ex) {
            System.out.println("Error al enviar mensaje");
        }
    }
}
