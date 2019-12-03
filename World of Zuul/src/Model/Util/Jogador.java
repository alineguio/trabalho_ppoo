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
public class Jogador {
    private int chances;
    private final ArrayList<Item> inventario;
    private static final Jogador instance = null;
    private final Ambiente ambienteAtual;

    /**Construtor com o número aleatório de chances para abrir portas, e inventário vazio
     * 
     * @param chances - número de tentativas
     * @param ambienteAtual - ambiente no qual o jogador começa jogando
     */
    private Jogador(int chances, Ambiente ambienteAtual) {
        this.chances = chances;
        inventario = new ArrayList<>();
        this.ambienteAtual = ambienteAtual;
    }
    
    /** Construtor Singleton
     * 
     * @param chances - número de tentativas
     * @param ambienteAtual - ambiente no qual o jogador começa jogando
     * @return Objeto da classe Jogador
     */
    public static Jogador getInstance(int chances, Ambiente ambienteAtual){
        if(instance != null){
            return instance;
        }
        
        return new Jogador(chances, ambienteAtual);
    }
    
    /** Método para decrementar as chances do jogador ao tentar abrir uma porta
     * 
     * @throws GameOverException 
     */
    public void decrementarChance() throws GameOverException{
        if (chances > 0){
            chances--;
        } else {
            throw new GameOverException("Suas tentativas acabaram!");
        }
    }
    
    /** Pega um item no ambiente e coloca no inventário
     * 
     * @param item - item encontrado no ambiente
     */
    public void pegarItem(Item item){
        inventario.add(item);
    }
    
    /** Retorna o inventário atual do jogador
     * 
     * @return array com os items já encontrados
     */
    public ArrayList<Item> getInventario(){
        return inventario;
    }
}
