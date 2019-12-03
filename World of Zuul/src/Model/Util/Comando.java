/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Util;

/**
 *
 * @author alfarr
 */
public class Comando {
    private final String palavraComando;
    private final String segundaPalavra;

    /**Construtor de comando com duas palavras
     * 
     * @param palavraComando - primeira palavra do comando
     * @param segundaPalavra - segunda palavra do comando
     */
    public Comando(String palavraComando, String segundaPalavra) {
        this.palavraComando = palavraComando;
        this.segundaPalavra = segundaPalavra;
    }
    
    /**Construtor de comando com uma palavra
     * 
     * @param palavraComando - palavra do comando
     */
    public Comando(String palavraComando){
        this.palavraComando = palavraComando;
        segundaPalavra = null;
    }
    
    /** Retorna a primeira palavra do comando
     * 
     * @return string com primeira palavra do comando
     */
    public String getPalavraComando(){
        return palavraComando;
    }
    
    /** Retorna a segunda palavra do comando
     * 
     * @return string com segunda palavra do comando
     */
    public String getSegundaPalavra(){
        return segundaPalavra;
    }
    
    /** Retorna booleano - true se o comando estiver vazio, false se não estiver
     * 
     * @return boolean que verifica se o comando esta vazio 
     */
    public boolean comandoVazio(){
        return (palavraComando == null);
    }
}
