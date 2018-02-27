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
            //Creamos el socket y los Streams el socket para ficheros tiene otro puerto
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
        } catch (Exception ex) {
            ven.barraEstado("Error Catastrófico.");
            ex.printStackTrace();
        }
    }

    private void esperarRespuesta() {
        Integer resp;
        //Esto son contadores para escribir en la barra de estado los archivos actualizados.
        int recib = 0, envi = 0;
        try {
            ven.barraEstado("Esperando respuesta");
            //Mientras la respuesta no sea 3 se mantiene esperando peticiones.
            while (true) {
                //Recibimos la peticion
                resp = ois.readInt();
                if (resp == 1) {
                    ven.barraEstado("actualizando archivo en cliente");
                    //despues de recibir la respuesta, el cliente envia
                    //el Archivos que quiere.
                    enviarArchivo((Archivos) ois.readObject());
                    envi++;
                } else if (resp == 2) {
                    ven.barraEstado("actualizando archivo en servidor");
                    //despues de recibir la respuesta, el cliente envia
                    //el Archivos que nos va a enviar.
                    recibirArchivo((Archivos) ois.readObject());
                    recib++;
                } else if (resp == 3) {
                    break;
                } else {
                    ven.barraEstado("Solicitud desconocida");
                    break;
                }
            }
            if (recib == 0 && envi == 0) {
                ven.barraEstado("El cliente está actualizado");
            } else {
                ven.barraEstado(recib + " archivo/s recibidos, " + envi + " archivos enviado/s.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Al principio me confundí y mandaba el File hasta que me di cuenta de que
     * no era necesario porque File contiene todo el archivo, pero luego me
     * pareció mas cómodo. Así que realizo la solicitud del File enviando el
     * Archivos.
     *
     * @param archivo Archivos que contiene el nombre
     */
    private void enviarArchivo(Archivos archivo) {
        try {
            //Cargo el File correspondiente al absolute del Archivos.
            File arch = new File(archivo.getAbsolutePath());
            //Envio el File.
            oos.writeObject(arch);
            ven.escribirLog("Enviado " + arch.getName());
            oos.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Recibimos el File al que hace referencia el Archivos
     *
     * @param archivo el Archivos con el nombre
     */
    private void recibirArchivo(Archivos archivo) {

        //Creamos File correspondiente a al Archivo y recibimos el File.
        try {
            //Abrimos el File con el nombre del Archivos
            File salida = new File(file.getAbsolutePath() + "\\" + archivo.getName());
            //Recibimos el File
            File leido = (File) ois.readObject();
            fisleer = new FileInputStream(leido);
            //Leemos del File recibido y escribimos en el de
            //salida mediante un buffer
            fosescribir = new FileOutputStream(salida);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fisleer.read(buffer)) > 0) {
                fosescribir.write(buffer, 0, length);
            }
            ven.escribirLog("Recibido " + salida.getName());
            fisleer.close();
            fosescribir.close();
            salida.setLastModified(leido.lastModified());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
