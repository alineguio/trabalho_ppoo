/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Util;


public class JogoException extends Exception{
    
    /**
     * Construtor padrão Exception
     */
    JogoException(){
        super();
    }
    
    /** Construtor com mensagem de erro da classe Jogo
     * 
     * @param message - texto de erro
     */
    JogoException(String message){
        super(message);
    }
    
}
