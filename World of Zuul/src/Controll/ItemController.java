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
import java.util.Random;


public class ItemController implements ItemInterface {
    
    /** Cria os itens do jogo 
     * 
     * @param ambientes - array com todos ambientes que existem no jogo
     * @return array com todos itens do jogo
     */
    
    @Override
    public ArrayList<Item> getGeneratedItens(ArrayList<Ambiente> ambientes){
        ArrayList<Item> itens = new ArrayList<>();
        ArquivoController arquivoController = new ArquivoController();
        ArrayList<Ambiente> copia = new ArrayList<>(); 
        for(Ambiente a: ambientes){ // cópia é feita para não existir duplicação de ambiente em que item está localizado 
            copia.add(a);
        }
        
        ChaveMestra chave = gerarChaveMestra(copia);
        Tesouro tesouro = gerarTesouro(copia);
        ArrayList<Dica> dicas = gerarDica(copia, tesouro);
        
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
        ChaveMestra chave = ChaveMestra.getInstance(sorteioAmbiente(ambientes, true), r.nextInt(13));
        
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
        Ambiente ambienteTesouro;  // ambiente que será colocado na dica sobre o tesouro

        for (int i = 0; i < 2; i++){
            ambienteDica = sorteioAmbiente(ambientes, true);
            ambienteTesouro = sorteioAmbiente(ambientes, false);
           
            dicas.add(new Dica(ambienteDica, "O tesouro não está no(a) " + ambienteTesouro.getDescricao()));
        }
        
        ambienteDica = sorteioAmbiente(ambientes, true);
        
        do{
            ambienteTesouro = tesouro.getAmbiente().getAmbiente(sorteioAmbiente(ambientes, false).getDescricao()); // pega as saídas do ambiente
        } while (ambienteTesouro == null);

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
        return Tesouro.getInstance(sorteioAmbiente(ambientes, true));
    }
    
    /** Retorna um ambiente aleatorio que está dentro de um array 
     * 
     * @param ambientes - array com todos ambientes que podem ser sorteados
     * @return ambiente sorteado
     */
    
    private Ambiente sorteioAmbiente(ArrayList<Ambiente> ambientes, boolean remover){
        Random r = new Random();
        Ambiente ambiente;
        
        ambiente = ambientes.get(r.nextInt(ambientes.size()));
        
        if(remover){
            ambientes.remove(ambiente);
        }
        
        return ambiente;
    }
    
}
