/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controll;

import Model.Util.Ambiente;
import Model.Util.ChaveMestra;
import Model.Util.Dica;
import Model.Util.Item;
import Model.Util.Tesouro;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author alinerguio
 */
public class ItemController implements ItemInterface {
    

    
    @Override
    public ArrayList<Item> getGeneratedItens(ArrayList<Ambiente> ambientes){
        ArrayList<Item> itens = new ArrayList<>();
        ArquivoController arquivoController = new ArquivoController();
        
        ChaveMestra chave = gerarChaveMestra(ambientes);
        Tesouro tesouro = gerarTesouro(ambientes);
        ArrayList<Dica> dicas = gerarDica(ambientes, tesouro);
        
        arquivoController.gravarArquivo(tesouro, chave, dicas);
        
        itens.add(chave);
        itens.add(tesouro);
        
        dicas.forEach((d) -> {
            itens.add(d);
        });
        
        return itens;
    }
    
    /** Cria a chave mestra do jogo 
     * 
     * @param ambientes - array com todos ambientes que existem no jogo e algum item pode ser colocado
     * @return chave mestra do jogo
     */
    @Override
    public ChaveMestra gerarChaveMestra(ArrayList<Ambiente> ambientes) {
        Random r = new Random();
        ChaveMestra chave = ChaveMestra.getInstance(sorteioAmbiente(ambientes), r.nextInt(13));
        
        return chave;
    }
    
    /** Cria as dicas no jogo 
     * 
     * @param ambientes - array com todos ambientes que existem no jogo e algum item pode ser colocado
     * @param tesouro - objeto do tesouro com informações de onde ele está
     * @return dicas do jogo
     */
    @Override
    public ArrayList<Dica> gerarDica(ArrayList<Ambiente> ambientes, Tesouro tesouro) {
        ArrayList<Dica> dicas = new ArrayList<>();
        Random r = new Random();
        
        Ambiente ambienteDica;
        Ambiente ambienteAleatorio;
        
        ArrayList<Ambiente> ambienteComItem = new ArrayList<>(); // ambientes que possuem itens já
        ArrayList<Ambiente> ambienteNaDica = new ArrayList<>(); // ambientes que já estão em dicas
        
        Ambiente ambienteTesouro;  // ambiente que será colocado na dica sobre o tesouro
        
        ambienteComItem.add(tesouro.getAmbiente());
        ambienteNaDica.add(tesouro.getAmbiente()); // evita que no while precise de verificação de igualdade do ambiente do tesouro com os dois ambientes 
        
        for (int i = 0; i < 2; i++){
            ambienteDica = sorteioAmbienteEspecifico(ambientes, ambienteComItem);
            ambienteTesouro = sorteioAmbienteEspecifico(ambientes, ambienteNaDica);
            
            while (ambienteDica.equals(ambienteTesouro)){
                ambienteTesouro = sorteioAmbienteEspecifico(ambientes, ambienteNaDica);
            }
            
            ambienteComItem.add(ambienteDica);
            ambienteNaDica.add(ambienteTesouro);
            
            dicas.add(new Dica(ambienteDica, "O tesouro não está no(a) " + ambienteTesouro.getDescricao()));
        }
        ambienteDica = sorteioAmbienteEspecifico(ambientes, ambienteComItem);
        ambienteComItem.add(ambienteDica);

        
        ambienteAleatorio = sorteioAmbienteEspecifico(ambientes, ambienteNaDica);
        ambienteTesouro = (tesouro.getAmbiente()).getAmbiente(ambienteAleatorio.getDescricao()); // pega as saídas do ambiente
         

        dicas.add(new Dica(ambienteDica, "O tesouro está próximo ao " + ambienteTesouro.getDescricao()));
           
        return dicas;
    }
    
    /** Cria o tesouro no jogo 
     * 
     * @param ambientes - array com todos ambientes que existem no jogo e algum item pode ser colocado
     * @return tesouro do jogo
     */
    @Override
    public Tesouro gerarTesouro(ArrayList<Ambiente> ambientes) {
        Tesouro tesouro = Tesouro.getInstance(sorteioAmbiente(ambientes));
        
        return tesouro;
    }
    
    /** Retorna um ambiente aleatorio que está dentro de um array 
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
    
    /** Retorna um ambiente aleatorio que está dentro do primeiro array e não esta dentro do segundo 
     * 
     * @param ambientes - array com todos ambientes que podem ser sorteados
     * @param ambientes - array com todos ambientes que ja foram sorteados
     * @return ambiente sorteado
     */
    private Ambiente sorteioAmbienteEspecifico(ArrayList<Ambiente> ambientes, ArrayList<Ambiente> listaComparativa){
        Ambiente ambiente = sorteioAmbiente(ambientes);
        while(listaComparativa.contains(ambiente)){ // se já existir no array list, continuar fazendo sorteio até não conter mais
            ambiente = sorteioAmbiente(ambientes);
        }
        return ambiente;
    }
}
