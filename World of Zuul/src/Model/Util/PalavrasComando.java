/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alfarr
 */
public class PalavrasComando {
    private static final PalavrasComando instance = null;
    private static List<String> comandosValidos;
    
    /** Construtor dos comandos estáticos do jogo    
    */
    private PalavrasComando(){
        comandosValidos = new ArrayList<>();
        comandosValidos.add("abrir");
        comandosValidos.add("sair");
        comandosValidos.add("observar");
        comandosValidos.add("ajuda");
        comandosValidos.add("chave");
    }
    
    
    /** Utilizando padrão de projeto Singleton, permite apenas uma instância dessa classe
     * 
     * @return Objeto da classe PalavrasComando
     */
    public static PalavrasComando getInstance(){
        if(instance != null){
            return instance;
        }
        
        return new PalavrasComando();
    }
    
    /** Retorna concatencação, separados por espaço, de cada comando disponível
     * 
     * @return lista de comandos
     */
    public String getComandos(){
        String rStr = "";
        
        for(String cmd : comandosValidos){
            rStr += cmd + " ";
        }
        
        return rStr;
    }
    
    /** Verifica se comando existe no sistema
     * 
     * @param cmd - comando digitado pelo jogador
     * @return - Verdadeiro se existir, falso, o contrário
     */
    public boolean ehComando(String cmd){
        return (comandosValidos.contains(cmd));
    }
    
}
