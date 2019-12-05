/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Util;


public class ItemException extends Exception{
    
    /** Construtor padrão da classe Exception
     * 
     */
    public ItemException(){
        super();
    }
    
    /** Construtor para erros na classe e subclasses Item   
     * 
     * @param message - mensagem de erro
     */ 
    public ItemException(String message){
        super("Erro em Item: " + message);
    }
}
