/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Util;

import java.util.ArrayList;


public class Jogo {
    private final Analisador analisador;
    private final ArrayList<Item> itensGerados;
    private final ArrayList<Ambiente> ambientes;
    private static Jogo instance = null;
    
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
    
    public static Jogo getInstance(ArrayList<Item> itensGerados, ArrayList<Ambiente> ambientes){
    /** Construtor Singleton
     * 
     * @param itensGerados - itens gerados do jogo atual
     * @param ambientes - ambientes do jogo
     * @return - Objeto da classe Jogo
    */
        if (instance == null){
           instance = new Jogo(itensGerados, ambientes);
        }
        
        return instance;
    }
    
    /** Retorna o objeto do ambiente com este nome
     * 
     * @param nome - nome do ambiente desejado
     * @return Objeto da classe Ambiente
     * @throws Model.Util.AmbienteException
     */
    public Ambiente getAmbiente(String nome) throws AmbienteException{
        
        Ambiente rAmbiente = null;
        
        for(Ambiente ambiente : ambientes){
            if (ambiente.getDescricao().equals(nome)){
                rAmbiente = ambiente;
            }
        }
        
        if (rAmbiente == null){
            throw new AmbienteException("Erro em getAmbiente(String nome), ambiente não encontrado!");
        }
        
        return rAmbiente;
        
    }
    
    /** Veririca se há algum item (que não seja o tesouro) naquele ambiente e retorna o item encontrado ou nulo
     * 
     * @param ambiente - ambiente que se deseja saber se há item
     * @return 
     */
    public Item verificaSeHaItem(Ambiente ambiente){
        
        Item item = null;
        
        for(Item i : itensGerados){
            if ((i instanceof Dica || i instanceof ChaveMestra) 
                    && i.getAmbiente().getDescricao().equals(ambiente.getDescricao())){
                item = i;
            }
        }
        
        return item;
    }
    
}
