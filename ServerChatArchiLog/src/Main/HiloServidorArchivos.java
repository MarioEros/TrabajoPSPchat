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

/**
 *
 * @author Eros
 */
public class HiloServidorArchivos extends Thread{
    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private File file;

    public HiloServidorArchivos(Socket socket, File file) {
        this.socket = socket;
        this.file = file;
    }
    @Override
    public void run() {
        try{
            FileOutputStream fos = null;
        }catch(Exception ex){ex.printStackTrace();}
    }
    private void EnviarFichero(File fichero){
        FileInputStream fis = null;
        try{
            fis = new FileInputStream(fichero);
            long bytes = fichero.length();
            byte[] buff = new byte[(int) bytes];
            int i,j=0;
            while((i=fis.read())!=-1){
                buff[j]=(byte)i;
                j++;
            }
            fis.close();
            oos.writeObject(buff);
        }catch(Exception ex){ex.printStackTrace();}
    }
}
