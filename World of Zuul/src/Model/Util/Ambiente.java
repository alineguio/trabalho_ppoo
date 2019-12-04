/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Util;


import java.util.HashMap;
import java.util.Objects;

/**
 *
 * @author alfarr
 */


public class Ambiente {
    private final String descricao;
    private final HashMap<String, Ambiente> saidas;

    public Ambiente(String descricao){
    
    /**Construtor com a descrição do ambiente e saídas vazia
     * 
     * @param descricao - descrição do ambiente
     */
    
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

    /** Retorna a ambiente de acordo com o nome 
     * 
     * @param nome
     * @return objeto ambiente que esta mapeado na Hash Map saidas
     */
    public Ambiente getAmbiente(String nome) {
        if(saidas.containsKey(nome))
            return saidas.get(nome);
        return null;
    }
    
    /** Retorna os ambientes adjacentes ao ambiente atual
     * 
     * @return objeto ambiente que esta mapeado na Hash Map saidas
     */
    public HashMap<String, Ambiente> getSaidas() {
        return saidas;
    }
    
    /** Retorna uma string com todas as saídas do ambiente concatenadas e separadas por um espaço
     * 
     * @return string com saídas
     */
    public String saidasToString(){
        String rStr = "";
        
        for (String saida : saidas.keySet()){
            rStr += saida + "  ";
        }
        
        return rStr;
    }
    
    @Override
    public boolean equals(Object o) {
        // self check
        if (this == o)
            return true;
        // null check
        if (o == null)
            return false;
        // type check and cast
        if (getClass() != o.getClass())
            return false;
        Ambiente ambiente = (Ambiente) o;
        // field comparison
        return Objects.equals(descricao, ambiente.descricao);
    }
}
