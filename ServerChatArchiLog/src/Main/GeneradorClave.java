/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 *
 * @author Eros
 */
public class GeneradorClave {
    public static void generarClave(){
        try{
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(128);
            SecretKey clave = kg.generateKey();
            File archivo = new File("Clave");
            if(!archivo.exists())archivo.mkdir();
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Clave\\clave.key"));
            oos.writeObject(clave);
            oos.flush();
            oos.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
