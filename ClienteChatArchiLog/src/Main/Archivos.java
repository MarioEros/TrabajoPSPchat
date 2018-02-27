/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Eros Esta es la clase "equivalente" a estructura de ficheros
 */
public class Archivos implements Serializable {

    //Nombre del archivo
    private String name;
    //Ruta completa del archivo
    private String absolutePath;
    //Ultima modificacion
    private long lastModified;
    //Longitud del mensaje (creo que al final no lo uso)
    private long length;
    //Array de archivos dentro de la carpeta (que será el archivo original)
    private ArrayList<Archivos> archivos;

    public Archivos(File file) {
        this.name = file.getName();
        this.lastModified = file.lastModified();
        this.length = file.length();
        this.archivos = new ArrayList<>();
        this.absolutePath = file.getAbsolutePath();
        File[] archis = file.listFiles();
        if (archis != null) {
            for (File arch : archis) {
                archivos.add(new Archivos(arch));
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getLastModified() {
        return lastModified;
    }

    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public ArrayList<Archivos> getArchivos() {
        return archivos;
    }

    public void setArchivos(ArrayList<Archivos> archivos) {
        this.archivos = archivos;
    }

    public boolean equals(Object o) {
        return this.getName().equals(((Archivos) o).getName());
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }
}
