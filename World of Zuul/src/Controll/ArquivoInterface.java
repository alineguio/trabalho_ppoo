/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controll;

import Model.Util.ChaveMestra;
import Model.Util.Dica;
import Model.Util.Tesouro;
import java.util.ArrayList;



/** Interface para métodos da classe Controlador/ArquivoController
 *
 * @author alinerguio
 */
public interface ArquivoInterface {
    public void gravarArquivo(Tesouro tesouro, ChaveMestra chave, ArrayList<Dica> dicas);
}
