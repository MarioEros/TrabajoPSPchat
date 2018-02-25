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
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

//@author Eros
public class HiloClienteArchivos extends Thread {

    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private FileInputStream fisleer;
    private FileOutputStream fosescribir;
    private VentanaCliente ven;
    private Archivos file;

    public HiloClienteArchivos(VentanaCliente ven, Archivos file) {
        this.file = file;
        this.ven = ven;
    }

    @Override
    public void run() {
        try {
            ven.barraEstado("Esperando a conectarse al servidor...");
            socket = new Socket("localhost", VentanaCliente.NUM_PUERTO_FICH);
            ois = new ObjectInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());
            ven.barraEstado("Esperando Estructura...");
            Archivos nuevo = (Archivos) ois.readObject();
            ven.escribirLog("Sincronizando archivos");
            ven.barraEstado(nuevo.getName());
            ArrayList<Archivos> archis = nuevo.getArchivos();
            comprobarArchivos(archis);
            ven.escribirLog("Archivos actualizados");
            ois.close();
            oos.close();
            socket.close();
        } catch (Exception ex) {
            ven.barraEstado("Error en la transmision de archivos");
            ex.printStackTrace();
        }
    }

    private void comprobarArchivos(ArrayList<Archivos> archis) {
        int totalArchivos = archis.size();
        ven.barraEstado("Actualizando " + totalArchivos + " archivos.");
        ArrayList<Archivos> archivosServer = archis;
        ArrayList<Archivos> archivosPropios = file.getArchivos();
        //Si la carpeta contiene archivos comprobamos uno a uno
        if (!archivosServer.isEmpty()) {
            archivosServer.forEach(a -> {
                if (archivosPropios.contains(a)) {
                    Archivos b = archivosPropios.get(archivosPropios.indexOf(a));
                    if (a.getLastModified() > b.getLastModified()) {
                        try {
                            oos.writeInt(1);
                            oos.flush();
                            recibirArchivo(a);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    } else if (a.getLastModified() < b.getLastModified()) {
                        try {
                            oos.writeInt(2);
                            oos.flush();
                            enviarArchivo(b);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                } else {
                    try {
                        oos.writeInt(1);
                        oos.flush();
                        recibirArchivo(a);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }
        try {
            oos.writeInt(3);
            oos.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (!archivosPropios.isEmpty()) {
            for (int i = archivosPropios.size() - 1; i >= 0; i--) {
                if (!archivosServer.contains(archivosPropios.get(i))) {
                    File quitar = new File(archivosPropios.get(i).getAbsolutePath());
                    ven.escribirLog(quitar.getName() + " Eliminado");
                    quitar.delete();
                }
            }
        }
        ven.barraEstado("Todos los archivos sincronizados");
    }

    private void enviarArchivo(Archivos archivo) {
        //Enviamos el File que corresponde a al Archivo correspondiente
        try {
            oos.writeObject(archivo);
            oos.flush();
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
            oos.writeObject(archivo);
            oos.flush();
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
