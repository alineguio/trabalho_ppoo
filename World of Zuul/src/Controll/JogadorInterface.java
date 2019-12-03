/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controll;

import Model.Util.Comando;
import Model.Util.Item;

/**
 *
 * @author alfarr
 */
public interface JogadorInterface {
    
    public void executarComando(Comando comando);
    public void sair();
    public void abrirPorta();
    public void usarItem(Item item);
    
    
}
