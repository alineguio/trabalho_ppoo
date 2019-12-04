/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controll;

import Model.Util.AmbienteException;


/** Interface para métodos da classe Controlador/JogoController
 *
 * @author alfarr
 */
public interface ControladorInterface {
    public void jogar() throws AmbienteException;
    public void boasVindas();
}
