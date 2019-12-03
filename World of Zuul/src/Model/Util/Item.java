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

/*
    Classe pai dos itens do jogo: Dica, Tesouro e Chave Mestra 
*/
public abstract class Item {
    final Ambiente ambiente;
    
    /** Construtor com o ambiente que o item está
        
        @param - Ambiente referencia ao objeto que o item se encontra
    */
    Item(Ambiente ambiente){
        this.ambiente = ambiente;
    }

    /** Retorna o ambiente que o item se encontra
     * 
     * @return referencia ao objeto que o item se encontra
     */
    public Ambiente getAmbiente() {
        return ambiente;
    }
    
    /** Poliformismo: subclasse executa uma ação
     * 
     * @throws Exception 
     */
    public abstract void fazerAcao() throws Exception;
    
    
}
