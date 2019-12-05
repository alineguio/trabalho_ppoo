/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controll;

import Model.Util.ChaveMestra;
import Model.Util.Dica;
import Model.Util.ItemException;
import Model.Util.Tesouro;
import java.io.*;
import java.util.ArrayList;
/**
 *
 * 
 */
public class ArquivoController implements ArquivoInterface {

    @Override
    public void gravarArquivo(Tesouro tesouro, ChaveMestra chave, ArrayList<Dica> dicas){
      try{
          FileWriter arq = new FileWriter("itens.txt");
          arq.write("Tesouro está no ambiente " + tesouro.getAmbiente().getDescricao());
          arq.write("\nChave Mestra está no ambiente " + chave.getAmbiente().getDescricao() + " e possui " + chave.getUsos() + " usos");
          arq.write("\nAs dicas do jogo estão no ambiente e são: ");
          for(Dica d: dicas) {
              try {
                  arq.write("\n  -> " + d.getAmbiente().getDescricao() + ": " + d.fazerAcao());
              } catch (ItemException ex) {
                  System.err.println(ex.getMessage() + "Erro ao armazenar Dica no arquivo");
              }
          }
          arq.close();
      } 
      catch (IOException e){
          System.out.println("Falha ao salvar itens no arquivo: " + e);
      }
    }
}
