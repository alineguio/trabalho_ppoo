/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controll;

import Model.Util.Ambiente;
import Model.Util.Comando;
import Model.Util.Item;
import Model.Util.Jogador;
import java.util.Random;

/**
 *
 * @author alfarr
 */
public class JogadorController implements JogadorInterface{
    
    private static final JogadorController instance = null;
    private final Jogador jogador;
    private Random random;
    
    private JogadorController(Ambiente ambienteInicial){
        jogador = Jogador.getInstance(random.nextInt((50 - 20 ) + 1) - 20, ambienteInicial);
    }
    
    public static JogadorController getInstance(Ambiente ambienteInicial){
        if (instance != null){
            return instance;
        }
        
        return new JogadorController(ambienteInicial);
    }
    
    @Override
    public void executarComando(Comando comando) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sair() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void abrirPorta() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void usarItem(Item item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
