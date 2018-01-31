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
public class HiloServidor extends Thread {

    DataInputStream dis;
    DataOutputStream dos;
    Socket socket;
    ServerSocket servidor;
    Ventana ven;
    BaseDeDatosCutre db;
    public Usuario usuario;

    public HiloServidor(Ventana ven, Socket socket, ServerSocket servidor) {
        this.socket = socket;
        this.servidor = servidor;
        this.ven = ven;
        this.db = new BaseDeDatosCutre();
    }

    @Override
    public void run() {
        while (true) {
            try {
                ven.EscribirEnChat("Esperando un Cliente...");
                this.socket = servidor.accept();
                dis = new DataInputStream(socket.getInputStream());
                dos = new DataOutputStream(socket.getOutputStream());
                ven.EscribirEnChat("Autenticando cliente...");
                while (!validar()) {
                }
                ven.EscribirEnChat("Cliente autenticado.");
                LeerMensajes();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    //Se mantiene escuchando mensajes.
    private void LeerMensajes() {
        while (!socket.isClosed()) {
            try{
                ven.EscribirEnChat(dis.readUTF());
            }catch(Exception ex){}
        }
    }

    //**Funciona**
    //@return Verdadero o falso dependiendo del si son correctos los credenciales.
    private boolean validar() {
        try {
            String cred = dis.readUTF();
            if (db.containsKey(cred)) {
                dos.writeBoolean(true);
                usuario = db.get(cred);
                cred = dis.readUTF();
                boolean correcto = usuario.getContrasenna().equals(cred);
                dos.writeBoolean(correcto);
                return correcto;
            } else {
                dos.writeBoolean(false);
            }
        } catch (Exception ex) {
            ven.EscribirEnChat("Error fatal de login.");
        }
        return false;
    }
}
