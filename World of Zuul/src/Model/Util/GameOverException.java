/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Util;


public class GameOverException extends Exception{
    
    /** Construtor padrão da classe Exception    */
    public GameOverException(){
        super();
    }
    
    /** Construtor padrão da classe Exception
        @param message - Exception para declarar que o jogo acabou
    */
    public GameOverException(String message){
        super(message);
    }
}
