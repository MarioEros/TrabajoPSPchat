/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.HashMap;

/**
 *
 * @author Eros
 */
public class BaseDeDatosCutre extends HashMap<String, Usuario>{

    public BaseDeDatosCutre() {
        this.put("Mario", new Usuario("Mario", "admin", "Rodriguez", "C/Pio Pio", "987654321"));
        this.put("Paco", new Usuario("Paco", "admin", "Paquez", "C/General Paco", "646465655"));
        this.put("Carlo", new Usuario("Carlo", "admin", "Magno", "C/Alejandia", "15654321"));
    }
}
