/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Util;


public class Dica extends Item{
    
    private final String texto;
    
    /**Construtor da dica
     * 
     * @param ambiente - ambiente em que a dica se encontra
     * @param texto - string que contem a dica
     */
    public Dica(Ambiente ambiente, String texto) {
        super(ambiente);
        this.texto = texto;
    }
    
    /** Método para printar o texto que contem a dica
     * 
     * @throws ItemException 
     */
   

    @Override
    public String fazerAcao() throws ItemException {
        return texto;
    }
    
}
