/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.DataOutputStream;
import java.net.Socket;

/**
 *
 * @author Eros
 */
public class HiloClienteEmisor extends Thread{
    
    VentanaCliente ven;
    Socket socket;
    DataOutputStream dos;

    public HiloClienteEmisor(VentanaCliente ven, Socket socket, DataOutputStream dos) {
        this.ven = ven;
        this.socket = socket;
        this.dos = dos;
    }
}
