/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.*;

/**
 *
 * @author Eros
 *
 *
 * ESTA CLASE NO SIRVE, LA COPIÉ DEL LIBRO PERO NO ME GUSTÓ Y NO LA USO.
 *
 *
 */
public class EstructuraFicheros implements Serializable {

    private String name;
    private String path;
    private boolean isDir;
    private int numeFich;
    private EstructuraFicheros[] lista;

    public EstructuraFicheros(String ruta) throws FileNotFoundException {
        File file = new File(ruta);
        this.name = file.getName();
        this.path = file.getPath();
        this.isDir = file.isDirectory();
        this.lista = getListaFiles();
        if (file.isDirectory()) {
            File[] ficheros = file.listFiles();
            this.numeFich = 0;
            if (!(ficheros == null)) {
                this.numeFich = ficheros.length;
            }
        }
    }

    public EstructuraFicheros(String name, String path, boolean isDir, int numeFich) {
        this.name = name;
        this.path = path;
        this.isDir = isDir;
        this.numeFich = numeFich;
    }

    public int getNumeFuch() {
        return numeFich;
    }

    public boolean isDir() {
        return isDir;
    }

    public String getPath() {
        return path;
    }

    public EstructuraFicheros[] getLista() {
        return lista;
    }

    public String getName() {
        String name_dir = name;
        if (isDir) {
            int l = path.lastIndexOf(File.pathSeparator);
            name_dir = path.substring(l + 1, path.length());
        }
        return name_dir;
    }

    @Override
    public String toString() {
        String nom = this.name;
        if (this.isDir) {
            nom = "(DIR) " + name;
        }
        return nom;
    }

    public EstructuraFicheros[] getListaFiles() {
        EstructuraFicheros[] lista = null;
        String sDirectorio = this.path;
        File f = new File(sDirectorio);
        File[] ficheros = f.listFiles();
        int longidud = ficheros.length;
        if (longidud > 0) {
            lista = new EstructuraFicheros[longidud];
            for (int i = 0; i < ficheros.length; i++) {
                EstructuraFicheros elemento = new EstructuraFicheros(ficheros[i].getName(), ficheros[i].getPath(), ficheros[i].isDirectory(), (isDir ? ficheros[i].listFiles() == null ? 0 : ficheros[i].listFiles().length : 0));
                lista[i] = elemento;
            }
        }
        return lista;
    }
}
