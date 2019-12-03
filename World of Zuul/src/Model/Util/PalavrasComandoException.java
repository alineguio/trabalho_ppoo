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
public class PalavrasComandoException extends Exception{
    
    /** Construtor padrão da classe Exception
     */
    public PalavrasComandoException(){
        super();
    }
    
    /** Construtor das exceções lançadas pela classe PalavrasComando
     * 
     * @param message - texto de erro 
     */
    public PalavrasComandoException(String message){
        super("Erro em PalavrasComando: " + message);
    }
}
