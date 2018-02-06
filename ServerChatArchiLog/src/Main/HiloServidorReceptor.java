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

/**
 *
 * @author Eros
 */
public class HiloServidorReceptor extends Thread {

    DataInputStream dis;
    DataOutputStream dos;
    Socket socket;
    ServerSocket servidor;
    VentanaServidor ven;
    BaseDeDatosCutre db;
    public Usuario usuario;

    public HiloServidorReceptor(VentanaServidor ven, Socket socket, ServerSocket servidor) {
        this.socket = socket;
        this.servidor = servidor;
        this.ven = ven;
        this.db = new BaseDeDatosCutre();
    }

    @Override
    public void run() {
        try {
            ven.MensajesConsola("Creando conexion...");
            socket = new Socket();
            ven.MensajesConsola("Esperando un Cliente...");
            socket = servidor.accept();
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            ven.setDos(dos,socket);
            boolean val = validar();
            if (!val) {
                ven.setOnBotonConectar(true);
                ven.MensajesConsola("Intento de login fallido.");
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

    //Se mantiene escuchando mensajes.
    private void LeerMensajes() {
        while (true) {
            try {
                String mens = dis.readUTF();
                if (mens.equals("*/QUIT*")) {
                    ven.MensajesConsola("Sesion desconectada por el Cliente.");
                    ven.TerminarConexion();
                    ven.setOnBotonConectar(true);
                    break;
                } else {
                    ven.EscribirEnChat(mens, false);
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
            String leido = dis.readUTF();
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
