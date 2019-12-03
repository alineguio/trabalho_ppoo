/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controll;

import Model.Util.ChaveMestra;
import Model.Util.Dica;
import Model.Util.Tesouro;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author alinerguio
 */
public class ArquivoController implements ArquivoInterface {

    @Override
    public void gravarArquivo(Tesouro tesouro, ChaveMestra chave, ArrayList<Dica> dicas){
      try{
          FileWriter arq = new FileWriter("itens.txt");
          arq.write("Tesouro está no ambiente " + tesouro.getAmbiente().getDescricao());
          arq.write("\n Chave Mestra está no ambiente " + chave.getAmbiente().getDescricao());
          arq.write("\n As dicas do jogo são: ");
          for(Dica d: dicas) {
              arq.write("\n -> " + d.getTexto());
          }
          arq.close();
      } 
      catch (IOException e){
          System.out.println("Falha ao salvar itens no arquivo: " + e);
      }
    }
}
