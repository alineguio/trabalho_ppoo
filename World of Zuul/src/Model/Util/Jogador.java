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
    private static Jogador instance = null;
    private Ambiente ambienteAtual;

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
        System.out.println("Erro");
        if(instance == null){
            instance = new Jogador(chances, ambienteAtual);
        }
         
        return instance;
    }
    
    public static Jogador getInstance(){
        return instance;
    }
    
    /** Método para decrementar as chances do jogador ao tentar abrir uma porta
     * 
     * @throws GameOverException 
     */
    public void decrementarChance() throws GameOverException{
        if (chances > 0){
            chances--;
        } else {
            throw new GameOverException("derrota");
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
    
    /** Retorna o ambiente que o jogador se encontra
     * 
     * @return - Objeto da classe Ambiente onde o jogador está
     */
    public Ambiente getAmbienteAtual(){
        return ambienteAtual;
    }
    
    public void setAmbienteAtual(Ambiente ambiente){
        this.ambienteAtual = ambiente;
    }
    
}
