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

//@author Eros
public class HiloClienteArchivos extends Thread {

    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private FileInputStream fisleer;
    private FileOutputStream fosescribir;
    private VentanaCliente ven;
    private Archivos file;
    //Variables que contaran los archivos actualizados.
    private int envi;
    private int recib;

    public HiloClienteArchivos(VentanaCliente ven, Archivos file) {
        this.file = file;
        this.ven = ven;
    }

    @Override
    public void run() {
        try {
            ven.barraEstado("Esperando a conectarse al servidor...");
            //Creamos el socket y los Streams el socket para ficheros tiene otro puerto
            socket = new Socket("localhost", VentanaCliente.NUM_PUERTO_FICH);
            ois = new ObjectInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());
            ven.barraEstado("Esperando Estructura...");
            //Leemos el Archivos que tiene el contenido de la carpeta a compartir
            Archivos nuevo = (Archivos) ois.readObject();
            ven.escribirLog("Sincronizando archivos desde " + nuevo.getName());
            ven.barraEstado(nuevo.getName());
            ArrayList<Archivos> archis = nuevo.getArchivos();
            //comprobamos el contenido
            comprobarArchivos(archis);
            ois.close();
            oos.close();
            socket.close();
            ven.escribirLog("Archivos sincronizados");
        } catch (Exception ex) {
            ven.barraEstado("Error en la transmision de archivos");
            ex.printStackTrace();
        }
    }

    private void comprobarArchivos(ArrayList<Archivos> archis) {
        //Ponemos a 0 las variables
        envi = 0;
        recib = 0;
        int borrados = 0;
        //Contamos los archivos para ponerlo en la barra de estado
        int totalArchivos = archis.size();
        ven.barraEstado("Actualizando " + totalArchivos + " archivos.");
        ArrayList<Archivos> archivosServer = archis;
        ArrayList<Archivos> archivosPropios = file.getArchivos();
        //Si la carpeta contiene archivos comprobamos uno a uno
        if (!archivosServer.isEmpty()) {
            archivosServer.forEach(a -> {
                if (archivosPropios.contains(a)) {
                    Archivos b = archivosPropios.get(archivosPropios.indexOf(a));
                    //si la fecha de a es mayor, se recibe el archivo
                    if (a.getLastModified() > b.getLastModified()) {
                        try {
                            oos.writeInt(1);
                            oos.flush();
                            recibirArchivo(a);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        //si la fecha de b es mayor, se envia el archivo
                    } else if (a.getLastModified() < b.getLastModified()) {
                        try {
                            oos.writeInt(2);
                            oos.flush();
                            enviarArchivo(b);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    //si ambas son iguales no se hace nada
                    //si el cliente no tiene el fichero, se recibe el archivo
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
        //se envia el numero que indica que ha finalizado
        try {
            oos.writeInt(3);
            oos.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //ahora si el cliente tiene archivos, comprueba que todos estén en el servidor
        //si no están estos se borran.
        if (!archivosPropios.isEmpty()) {
            for (int i = archivosPropios.size() - 1; i >= 0; i--) {
                if (!archivosServer.contains(archivosPropios.get(i))) {
                    File quitar = new File(archivosPropios.get(i).getAbsolutePath());
                    ven.escribirLog(quitar.getName() + " Eliminado");
                    quitar.delete();
                    borrados++;
                }
            }
        }
        //se actualiza la barra de estado de ficheros.
        if (recib == 0 && envi == 0) {
            ven.barraEstado("Los archivos están actualizados. " + (borrados == 0 ? "" : borrados + " archivo/s borrados."));
        } else {
            ven.barraEstado(recib + " archivo/s recibidos, " + envi + " archivos enviado/s. " + (borrados == 0 ? "" : borrados + " archivo/s borrados."));
        }
    }

    //Enviamos el Archivos que representa el File a enviar y luego se envia el File.
    private void enviarArchivo(Archivos archivo) {
        //Enviamos el File que corresponde a al Archivo correspondiente
        try {
            oos.writeObject(archivo);
            oos.flush();
            File arch = new File(archivo.getAbsolutePath());
            oos.writeObject(arch);
            ven.escribirLog("Enviado " + arch.getName());
            oos.flush();
            envi++;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //Enviamos el Archivos que queremos recibir y luego se recibe el File
    private void recibirArchivo(Archivos archivo) {
        //Creamos File correspondiente a al Archivo y recibimos el File.
        try {
            oos.writeObject(archivo);
            oos.flush();
            File salida = new File(file.getAbsolutePath() + "\\" + archivo.getName());
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
            recib++;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
