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
public class Dica extends Item{
    
    private String texto;
    
    /**Construtor da dica
     * 
     * @param ambiente - ambiente em que a dica se encontra
     * @param texto - string que contem a dica
     */
    public Dica(Ambiente ambiente, String texto) {
        super(ambiente);
        this.texto = texto;
    }
    
    /** Retorna a dica
     * 
     * @return string que contem a dica
     */
    public String getTexto() {
        return texto;
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
