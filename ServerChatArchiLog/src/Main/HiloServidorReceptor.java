/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Base64;
import javax.crypto.Cipher;

/**
 *
 * @author Eros
 */
public class HiloServidorReceptor extends Thread {

    private DataInputStream dis;
    private DataOutputStream dos;
    private Socket socket;
    private ServerSocket servidor;
    private VentanaServidor ven;
    private BaseDeDatosCutre db;
    private Cipher desencrip;
    public Usuario usuario;

    public HiloServidorReceptor(VentanaServidor ven, Socket socket, ServerSocket servidor, Cipher desencrip) {
        this.socket = socket;
        this.servidor = servidor;
        this.ven = ven;
        this.db = new BaseDeDatosCutre();
        this.desencrip = desencrip;
    }

    @Override
    public void run() {
        try {
            //Espera un cliente
            ven.MensajesConsola("Creando conexion, esperando un Cliente...");
            socket = servidor.accept();
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            ven.setDos(dos, socket);
            //Comprueba los credenciales.
            boolean val = validar();
            if (!val) {
                ven.setOnBotonConectar(true);
                ven.MensajesConsola("Intento de login fallido.");
                ven.escribirLog("Login fallido");
                this.interrupt();
            } else {
                ven.MensajesConsola("Usuario logeado con exito.");
                LeerMensajes();
            }
        } catch (Exception ex) {
            ven.MensajesConsola("Error al crear conexion.");
            ven.setOnBotonConectar(true);
            ex.printStackTrace();
        }
    }

    //Se mantiene escuchando mensajes hasta recibir un */QUIT*.
    private void LeerMensajes() {
        while (true) {
            try {
                //Realiza aqui la desencriptacion del string
                //lee el String, Base64 lo convierte en byte[],
                //el Cipher lo transforma en byte[] sin cifrar y
                //new String lo convierte en texto
                String mensaje = new String(desencrip.doFinal(Base64.getDecoder().decode(dis.readUTF())));
                if (mensaje.equals("*/QUIT*")) {
                    ven.MensajesConsola("Sesion desconectada por el Cliente.");
                    ven.escribirLog("Cliente desconectado.");
                    ven.TerminarConexion();
                    ven.setOnBotonConectar(true);
                    dis.close();
                    break;
                } else {
                    ven.EscribirEnChat(mensaje, false);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                break;
            }
        }
    }

    //@return Verdadero o falso dependiendo del si son correctos los credenciales.
    private boolean validar() {
        try {
            //Realiza aqui la desencriptacion del string
            //lee el String, Base64 lo convierte en byte[],
            //el Cipher lo transforma en byte[] sin cifrar y
            //new String lo convierte en texto
            String leido = new String(desencrip.doFinal(Base64.getDecoder().decode(dis.readUTF())));
            String[] log = leido.split("##");
            if (db.containsKey(log[0]) && db.get(log[0]).getContrasenna().equals(log[1])) {
                dos.writeBoolean(true);
                ven.setUsuario(db.get(log[0]));
                ven.DatosUsuario();
                return true;
            } else {
                dos.writeBoolean(false);
                return false;
            }
        } catch (Exception ex) {
            ven.MensajesConsola("Error fatal de login.");
            return false;
        }
    }
}
