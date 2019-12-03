/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controll;


/** Interface para métodos da classe Controlador/JogoController
 *
 * @author alfarr
 */
public interface ControladorInterface {
    public abstract void jogar();
    public abstract void acaoComando(String comando);  
}
