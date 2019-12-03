/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controll;

import Model.Util.Ambiente;
import Model.Util.GameOverException;
import Model.Util.Item;
import Model.Util.ItemException;
import Model.Util.Jogador;
import Model.Util.JogadorException;
import java.util.Random;

/**
 *
 * @author alfarr
 */
public class JogadorController implements JogadorInterface{
    
    private static JogadorController instance = null;
    private final Jogador jogador;
    private final Random random = new Random();
    
    private JogadorController(Ambiente ambienteInicial){
        System.out.println("Cheguei");
        jogador = Jogador.getInstance(random.nextInt((50 - 20 ) + 1) - 20, ambienteInicial);
    }
    
    public static JogadorController getInstance(Ambiente ambienteInicial){
        if (instance == null){
            instance = new JogadorController(ambienteInicial);
        }
        
        return instance;
    }
    

    @Override
    public void sair() throws JogadorException{
        throw new JogadorException("Jogador saiu do jogo!");
    }

    @Override
    public boolean abrirPorta(String nome) throws JogadorException, GameOverException{
        if (random.nextBoolean()){
            jogador.setAmbienteAtual(jogador.getAmbienteAtual().getAmbiente(nome));
            jogador.decrementarChance();
            return true;
        }
        
        return false;
    }

    @Override
    public void usarItem(Item item) throws ItemException, JogadorException{
        if (jogador.getInventario().contains(item)){            
            try {
                item.fazerAcao();
            } catch (ItemException e) {
                throw new ItemException(e.getMessage() + "Erro ao usar o item");
            } catch (GameOverException ex) {
                throw new JogadorException(ex.getMessage() + "Tesouro está no inventário do jogador.");
            } 
        } else {
            throw new JogadorException("Item não está no inventário! Tá tentando usar cheat é?");
        }
    }
    
}
