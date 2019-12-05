/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Util;


public class ComandoException extends Exception{
    /** Construtor padrão da classe Exception    */
    public ComandoException(){
        super();
    }
    
    /** Construtor com mensagem de erro da classe Comando
     * 
     * @param message - texto de erro
     */
    public ComandoException(String message){
        super("Erro em Comando: " + message);
    }
    
}
