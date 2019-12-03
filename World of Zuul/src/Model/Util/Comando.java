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

    public Comando(String palavraComando, String segundaPalavra) {
        this.palavraComando = palavraComando;
        this.segundaPalavra = segundaPalavra;
    }
    
    public Comando(String palavraComando){
        this.palavraComando = palavraComando;
        segundaPalavra = null;
    }
    
    public String getPalavraComando(){
        return palavraComando;
    }
    
    public String getSegundaPalavra(){
        return segundaPalavra;
    }
    
    public boolean comandoVazio(){
        return (palavraComando == null);
    }
}