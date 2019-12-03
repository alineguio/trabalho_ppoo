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
    
    /**Construtor com os itens gerados e ambientes e com o analisador gerado com o Construtor Singleton 
     * 
     * @param itensGerados - itens gerados do jogo atual
     * @param ambientes - ambientes do jogo
     */
    private Jogo(ArrayList<Item> itensGerados, ArrayList<Ambiente> ambientes) {
        this.analisador = Analisador.getInstance();
        this.itensGerados = itensGerados;
        this.ambientes = ambientes;
    }
    
    /** Construtor Singleton
     * 
     * @param itensGerados - itens gerados do jogo atual
     * @param ambientes - ambientes do jogo
     * @return - Objeto da classe Jogo
    */
    public Jogo getInstance(ArrayList<Item> itensGerados, ArrayList<Ambiente> ambientes){
        if (instance != null){
            return instance;
        }
        
        return new Jogo(itensGerados, ambientes);
    }
      
    
}
