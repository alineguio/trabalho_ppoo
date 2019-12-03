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
public class ChaveMestra extends Item{
    private int usos;
    private static final ChaveMestra instance = null;
    
    
    private ChaveMestra(Ambiente ambiente, int usos){
        super(ambiente);
        this.usos = usos;
    }
    
    public static ChaveMestra getInstance(Ambiente ambiente, int usos){
        if (instance != null){
            return instance;
        }
        
        return new ChaveMestra(ambiente, usos);
    }
            
    public void fazerAcao() throws ItemException{
        if(usos > 0){
            usos--;
        } else {
            throw new ItemException("Chave mestra quebrou!");
        }
        
        
    }
    
}
