/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.DataInputStream;
import java.net.Socket;

/**
 *
 * @author Eros
 */
public class HiloClienteReceptor extends Thread {

    VentanaCliente ven;
    Socket socket;
    DataInputStream dis;

    public HiloClienteReceptor(VentanaCliente ven, Socket socket, DataInputStream dis) {
        this.ven = ven;
        this.socket = socket;
        this.dis = dis;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String mensaje = dis.readUTF();
                if (mensaje.equals("*/QUIT*")) {
                    ven.MensajesConsola("Conexion cerrada desde el Servidor.");
                    ven.setOnBotonConectar(true);
                    break;
                } else {
                    ven.EscribirEnChat(mensaje, true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
