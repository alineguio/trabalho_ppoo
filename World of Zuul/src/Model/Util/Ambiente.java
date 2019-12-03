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
      
    Ambiente(String descricao){
        this.descricao = descricao;
        saidas = new HashMap<>();
    }
    
    public void ajustarSaidas(String nome, Ambiente ambiente){
        saidas.put(nome, ambiente);
    }

    public String getDescricao() {
        return descricao;
    }

    public Ambiente getAmbiente(String nome) {
        return saidas.get(nome);
    }
   
    public String saidasToString(){
        String rStr = "";
        
        for (String saida : saidas.keySet()){
            rStr += saida + " ";
        }
        
        return rStr;
    }
    
}
