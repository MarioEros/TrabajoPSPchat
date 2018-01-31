/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.net.Socket;

/**
 *
 * @author Eros
 */
public class HiloClienteEscribir extends Thread{
    
    Ventana ven;
    Socket socket;

    public HiloClienteEscribir(Ventana ven, Socket socket) {
    }
    
}
