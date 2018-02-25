/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.DataInputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import javax.crypto.Cipher;

/**
 *
 * @author Eros
 */
public class HiloClienteReceptor extends Thread {

    private VentanaCliente ven;
    private Socket socket;
    private DataInputStream dis;
    private ObjectInputStream ois;
    private Cipher desencrip;

    public HiloClienteReceptor(VentanaCliente ven, Socket socket, DataInputStream dis,Cipher desencrip) {
        this.ven = ven;
        this.socket = socket;
        this.dis = dis;
        this.desencrip=desencrip;
    }

    @Override
    public void run() {
        while (true) {
            try {
                //byte[] texto = new byte[1024];
                //dis.read(texto);
                //String mensaje = new String(desencrip.doFinal(texto));
                String mensaje = dis.readUTF();
                if (mensaje.equals("*/QUIT*")) {
                    ven.MensajesConsola("Conexion cerrada desde el Servidor.");
                    ven.escribirLog("Desconectado del servidor");
                    ven.setOnBotonConectar(true);
                    break;
                } else {
                    ven.EscribirEnChat(mensaje, true);
                }
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }

}
