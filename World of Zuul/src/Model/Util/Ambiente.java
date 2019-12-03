/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Util;


import java.util.HashMap;

/**
 *
 * @author alfarr
 */


public class Ambiente {
    private final String descricao;
    private final HashMap<String, Ambiente> saidas;
    
    /**Construtor com a descrição do ambiente e saídas vazia
     * 
     * @param descricao - descrição do ambiente
     */
    
    Ambiente(String descricao){
        this.descricao = descricao;
        saidas = new HashMap<>();
    }
    
    /** Ajusta a saída, Hash Map, com o nome do ambiente e seu respectivo objeto ambiente
     * 
     * @param nome - nome do ambiente
     * @param ambiente - objeto ambiente
     */
    public void ajustarSaidas(String nome, Ambiente ambiente){
        saidas.put(nome, ambiente);
    }
    
    /** Retorna a descrição do ambiente
     * 
     * @return string com descrição do ambiente
     */
    public String getDescricao() {
        return descricao;
    }

    /** Retorna a descrição do ambiente
     * 
     * @return objeto ambiente que esta mapeado na Hash Map saidas
     */
    public Ambiente getAmbiente(String nome) {
        return saidas.get(nome);
    }
    
    /** Retorna uma string com todas as saídas do ambiente concatenadas e separadas por um espaço
     * 
     * @return string com saídas
     */
    public String saidasToString(){
        String rStr = "";
        
        for (String saida : saidas.keySet()){
            rStr += saida + " ";
        }
        
        return rStr;
    }
    
}
