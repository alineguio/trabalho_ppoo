/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controll;

import Model.Util.GameOverException;
import Model.Util.ItemException;
import Model.Util.JogadorException;


public interface JogadorInterface {
    
    public boolean abrirPorta(String nome) throws JogadorException, GameOverException;
    public void usarChave() throws ItemException, JogadorException;
    
}
