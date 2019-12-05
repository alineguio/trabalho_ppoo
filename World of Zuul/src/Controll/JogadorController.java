/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controll;

import Model.Util.Ambiente;
import Model.Util.AmbienteException;
import Model.Util.ChaveMestra;
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
        jogador = Jogador.getInstance(random.nextInt(30) + 20, ambienteInicial);
    }
    
    public static JogadorController getInstance(Ambiente ambienteInicial){
        if (instance == null){
            instance = new JogadorController(ambienteInicial);
        }
        
        return instance;
    }
    
    @Override
    public boolean abrirPorta(String nome) throws JogadorException, GameOverException{
        jogador.decrementarChance();
        if (random.nextBoolean()){
            jogador.setAmbienteAtual(jogador.getAmbienteAtual().getAmbiente(nome));
            return true;
        }
        
        return false;
    }

    @Override
    public void usarChave() throws ItemException, JogadorException{
        
        Item item = null;    
        for(Item i : jogador.getInventario()){
            if(i instanceof ChaveMestra){
                item = i;
                break;
            }
        }
        
        if(item != null){
            try {
                item.fazerAcao();
            } catch (ItemException e) {
                throw new ItemException(e.getMessage() + "Erro ao usar o item");
            } catch (GameOverException ex) {
                throw new JogadorException(ex.getMessage() + "Tesouro está no inventário do jogador???");
            } 
        } else {
            throw new JogadorException("Ainda não achou a chave!");
        }
    }
}
