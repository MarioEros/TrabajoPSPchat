/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *  Este hilo se encargará de leer los mensajes del cliente y escribirlas en la sala de chat del servidor
 * @author Eros
 */
public class HiloServidorEscritor  extends Thread {

    DataInputStream dis;
    Socket socket;
    ServerSocket servidor;
    Ventana ven;

    public HiloServidorEscritor(Ventana ven, Socket socket, ServerSocket servidor) {
        this.socket = socket;
        this.servidor = servidor;
        this.ven = ven;
    }

    @Override
    public void run() {
    }
}
