/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Util;

import java.util.Scanner;

/**
 *
 * @author alfarr
 */
public class Analisador {
    private final Scanner scanner;
    private final PalavrasComando palavrasComando;
    private static final Analisador instance = null;
    
    /** Construtor do analisador de comandos
     * 
     */
    private Analisador(){
        scanner = new Scanner(System.in);
        palavrasComando = PalavrasComando.getInstance();        
    }
    
    /** Construtor Singleton
      * 
     * @return - Objeto da classe Analisador
    */
    public static Analisador getInstance(){
        if (instance != null){
            return instance;
        }
        
        return new Analisador();
    }
    
    /* Método para pegar o comando do prompt
    
    */
    public Comando pegaComando(){
        String linha;  
        String palavra1 = null;
        String palavra2 = null;

        System.out.print("> ");     

        linha = scanner.nextLine();

        
        Scanner tokenizer = new Scanner(linha);
        if(tokenizer.hasNext()) {
            palavra1 = tokenizer.next();      
            if(tokenizer.hasNext()) {
                palavra2 = tokenizer.next();      
            }
        }

        if(palavrasComando.ehComando(palavra1)) {
            return new Comando(palavra1, palavra2);
        }
        else {
            return new Comando(null, palavra2); 
        }
    }
    
    
    
    
}
