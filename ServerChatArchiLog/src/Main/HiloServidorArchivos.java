/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Eros
 */
public class HiloServidorArchivos extends Thread {
    
    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private FileInputStream fisleer;
    private FileOutputStream fosescribir;
    private Archivos file;
    private VentanaServidor ven;
    
    public HiloServidorArchivos(VentanaServidor ven, Archivos file) {
        this.file = file;
        this.ven = ven;
    }
    
    @Override
    public void run() {
        try {
            //Creamos el socket y los Streams
            ServerSocket server = new ServerSocket(VentanaServidor.NUM_PUERTO_FICH);
            ven.barraEstado("Esperando que el cliente elija carpeta para compartir...");
            socket = server.accept();
            ven.barraEstado("Cliente conectado, enviando estructura...");
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            //Enviamos el contenido de la carpeta
            oos.writeObject(file);
            ven.escribirLog("Sincronizando carpeta " + file.getName());
            oos.flush();
            esperarRespuesta();
            ven.escribirLog("Cliente actualizado");
            ois.close();
            oos.close();
            socket.close();
            server.close();
            ven.barraEstado("Cliente sincronizado");
        } catch (Exception ex) {
            ven.barraEstado("Error Catastrófico.");
            ex.printStackTrace();
        }
    }
    
    private void esperarRespuesta() {
        Integer resp;
        try {
            ven.barraEstado("Esperando respuesta");
            while (true) {
                //Recibimos la peticion
                resp = ois.readInt();
                if (resp == 1) {
                    ven.barraEstado("actualizando archivo en cliente");
                    enviarArchivo((Archivos) ois.readObject());
                } else if (resp == 2) {
                    ven.barraEstado("actualizando archivo en servidor");
                    recibirArchivo((Archivos) ois.readObject());
                } else if (resp == 3) {
                    break;
                } else {
                    ven.barraEstado("Solicitud desconocida");
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void enviarArchivo(Archivos archivo) {
        //Enviamos el File que corresponde a al Archivo correspondiente
        try {
            File arch = new File(archivo.getAbsolutePath());
            oos.writeObject(arch);
            ven.escribirLog("Enviado " + arch.getName());
            oos.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void recibirArchivo(Archivos archivo) {
        //Creamos File correspondiente a al Archivo y recibimos el File.
        try {
            File salida = new File(file.getAbsolutePath() + "\\" + archivo.getName());
            salida.setLastModified(archivo.getLastModified());
            File leido = (File) ois.readObject();
            fisleer = new FileInputStream(leido);
            fosescribir = new FileOutputStream(salida);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fisleer.read(buffer)) > 0) {
                fosescribir.write(buffer, 0, length);
            }
            ven.escribirLog("Recibido " + salida.getName());
            fisleer.close();
            fosescribir.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
