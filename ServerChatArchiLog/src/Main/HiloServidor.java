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
    public void run() {try {
            ven.EscribirEnChat("Creando conexion...");
            socket = new Socket();
            ven.EscribirEnChat("Esperando un Cliente...");
            socket = servidor.accept();
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            boolean val = validar();
            if (!val) {
                ven.botCon(true);
                ven.EscribirEnChat("Intento de login fallido.");
                this.interrupt();
            } else {
                ven.EscribirEnChat("Usuario logeado con exito.");
            }
        } catch (Exception ex) {
            ven.EscribirEnChat("Error al crear conexion.");
            ven.botCon(true);
            ex.printStackTrace();
            this.interrupt();
        }
    }

    //Se mantiene escuchando mensajes.
    private void LeerMensajes() {
    }

    //**Funciona**
    //@return Verdadero o falso dependiendo del si son correctos los credenciales.
    private boolean validar() {
        try{
            String[] log = dis.readUTF().split("$$");
            if (db.containsKey(log[0]) && db.get(log[0]).getContrasenna().equals(log[1])) {
                dos.writeBoolean(true);
                ven.DatosUsuario(db.get(log[0]));
                return true;
            }else{
                dos.writeBoolean(false);
                return false;
            }
        } catch (Exception ex) {
            ven.EscribirEnChat("Error fatal de login.");
            return false;
        }
    }
}
