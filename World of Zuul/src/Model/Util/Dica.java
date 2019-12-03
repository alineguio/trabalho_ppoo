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
    
    public Dica(Ambiente ambiente, String texto) {
        super(ambiente);
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

    @Override
    public void fazerAcao() throws ItemException {
        System.out.println(texto);
    }
    
    
       
}
