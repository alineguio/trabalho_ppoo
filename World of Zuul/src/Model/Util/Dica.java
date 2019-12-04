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
    private static final Dica instance = null;
    
    /**Construtor da dica
     * 
     * @param ambiente - ambiente em que a dica se encontra
     * @param texto - string que contem a dica
     */
    private Dica(Ambiente ambiente, String texto) {
        super(ambiente);
        this.texto = texto;
    }
    
    /** Construtor Singleton
     * 
     * @param ambiente - ambiente em que a dica se encontra
     * @param texto - string que contem a dica
     * @return - Objeto da classe Dica
    */
    public static Dica getInstance(Ambiente ambiente, String texto){
        if (instance != null){
            return instance;
        }
        
        return new Dica(ambiente, texto);
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
   

    public String fazerAcao() throws ItemException {
        return texto;
    }
    
}
