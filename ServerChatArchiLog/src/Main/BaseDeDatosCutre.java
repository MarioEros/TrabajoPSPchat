/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.HashMap;

/**
 * @author Eros Base de datos con los usuarios.
 */
public class BaseDeDatosCutre extends HashMap<String, Usuario> {

    public BaseDeDatosCutre() {
        this.put("Mario", new Usuario("Mario", "admin", "Mario Eros", "C/Pio Pio", "987654321"));
        this.put("Paco", new Usuario("Paco", "admin", "Paquito Paquez", "C/General Paco", "646465655"));
        this.put("Carlo", new Usuario("Carlo", "admin", "Carlo Magno", "C/Alejandia", "15654321"));
    }
}
