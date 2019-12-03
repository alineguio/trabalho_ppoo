/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controll;

import Model.Util.Ambiente;
import Model.Util.ChaveMestra;
import Model.Util.Dica;
import Model.Util.Tesouro;
import java.util.ArrayList;

/** Interface para métodos da classe Controlador/ItemController
 *
 * @author alinerguio
 */
public interface ItemInterface {  
    public ChaveMestra gerarChaveMestra(ArrayList<Ambiente> ambientes);
    public ArrayList<Dica> gerarDica(ArrayList<Ambiente> ambientes, Tesouro tesouro);
    public Tesouro gerarTesouro(ArrayList<Ambiente> ambientes);
}
