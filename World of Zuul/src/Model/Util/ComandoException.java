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
public class ComandoException extends Exception{
    
    public ComandoException(){
        super();
    }
    
    public ComandoException(String message){
        super("Erro em Comando: " + message);
    }
    
}
