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
    
    /**Construtor da chave mestra 
     * 
     * @param ambiente - ambiente em que a chave se encontra
     * @param usos - quantidade de vezes que a chave pode ser usada
     */
    private ChaveMestra(Ambiente ambiente, int usos){
        super(ambiente);
        this.usos = usos;
    }
    
    /** Construtor Singleton
     * 
     * @param ambiente - ambiente que a chave mestra está
     * @param usos - quantidade de vezes que a chave pode ser usada
     * @return - Objeto da classe ChaveMestra
    */
    public static ChaveMestra getInstance(Ambiente ambiente, int usos){
        if (instance != null){
            return instance;
        }
        
        return new ChaveMestra(ambiente, usos);
    }
    
    /** Método para decrementar a quantidade de vezes que a chave mestra ainda pode ser utilizada
     * 
     * @throws ItemException 
     */
    @Override
    public void fazerAcao() throws ItemException{
        if(usos > 0){
            usos--;
        } else {
            throw new ItemException("Chave mestra quebrou!");
        }
        
        
    }
    
}
