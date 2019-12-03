/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Util;

import java.util.ArrayList;

/**
 *
 * @author alfarr
 */
public class Jogo {
    private final Analisador analisador;
    private final ArrayList<Item> itensGerados;
    private final ArrayList<Ambiente> ambientes;
    private static final Jogo instance = null;

    private Jogo(ArrayList<Item> itensGerados, ArrayList<Ambiente> ambientes) {
        this.analisador = Analisador.getInstance();
        this.itensGerados = itensGerados;
        this.ambientes = ambientes;
    }
    
    public Jogo getInstance(ArrayList<Item> itensGerados, ArrayList<Ambiente> ambientes){
        if (instance != null){
            return instance;
        }
        
        return new Jogo(itensGerados, ambientes);
    }
      
    
}
