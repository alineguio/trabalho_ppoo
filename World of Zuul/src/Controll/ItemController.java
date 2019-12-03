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
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author alinerguio
 */
public class ItemController implements ItemInterface {
    
    /** Cria a chave mestra do jogo 
     * 
     * @param ambientes - array com todos ambientes que existem no jogo e algum item pode ser colocado
     * @return chave mestra do jogo
     */
    @Override
    public ChaveMestra gerarChaveMestra(ArrayList<Ambiente> ambientes) {
        Random r = new Random();
        int usos = r.nextInt(13);
        ChaveMestra chave = null;       
        chave.getInstance(sorteioAmbiente(ambientes), usos);
        
        return chave;
    }
    
    /** Cria as dicas no jogo 
     * 
     * @param ambientes - array com todos ambientes que existem no jogo e algum item pode ser colocado
     * @return dicas do jogo
     */
    @Override
    public ArrayList<Dica> gerarDica(ArrayList<Ambiente> ambientes, Tesouro tesouro) {
        ArrayList<Dica> dicas = new ArrayList<>();
        Random r = new Random();
        
        Ambiente ambienteDica;
        HashMap<String, Ambiente> ambientesPertoTesouro;
        ArrayList<Ambiente> copia = ambientes;
        Ambiente ambienteTesouro;  // ambiente que será colocado na dica sobre o tesouro
        Dica nova = null;
        
        copia.remove(tesouro.getAmbiente());
        
        for (int i = 0; i < 3; i++){
            
            ambienteDica = sorteioAmbiente(ambientes);
            ambientes.remove(ambienteDica); 
            
            ambienteTesouro = sorteioAmbiente(copia);
            copia.remove(ambienteTesouro);
            
            nova.getInstance(ambienteDica, "O tesouro não está no(a) " + ambienteTesouro.getDescricao());
            dicas.add(nova);
            nova = null;
        }
        
        ambienteDica = sorteioAmbiente(ambientes);
        ambientes.remove(ambienteDica); 
        
        ambientesPertoTesouro = (tesouro.getAmbiente()).getSaidas();
        ambienteTesouro = ambientesPertoTesouro.get(r.nextInt(ambientes.size()));
        
        nova.getInstance(ambienteDica, "O tesouro está próximo ao " + ambienteTesouro.getDescricao());
        dicas.add(nova);
           
        return dicas;
    }
    
    /** Cria o tesouro no jogo 
     * 
     * @param ambientes - array com todos ambientes que existem no jogo e algum item pode ser colocado
     * @return tesouro do jogo
     */
    @Override
    public Tesouro gerarTesouro(ArrayList<Ambiente> ambientes) {
        Tesouro tesouro = null;  
        tesouro.getInstance(sorteioAmbiente(ambientes));
        
        return tesouro;
    }
    
    /** Retorna um ambiente aleatorio que esta dentro de um array 
     * 
     * @param ambientes - array com todos ambientes que podem ser sorteados
     * @return ambiente sorteado
     */
    
    private Ambiente sorteioAmbiente(ArrayList<Ambiente> ambientes){
        Random r = new Random();
        int idAmbiente;
        Ambiente ambiente;
        
        idAmbiente = r.nextInt(ambientes.size());
        ambiente = ambientes.get(idAmbiente);
        
        return ambiente;
    }
    
}
