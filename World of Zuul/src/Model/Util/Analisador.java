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
    
    /** Método para pegar o comando do prompt
     * @param linha - texto digitado pelo jogador na caixa da GUI
     * @return Objeto do tipo Comando
     * @throws Model.Util.ComandoException
    */
    public Comando pegaComando(String linha) throws ComandoException{
        
        String comandos[] = linha.split(" ");
        
        if (palavrasComando.ehComando(comandos[0])){
            return new Comando(linha);
        }
        
        throw new ComandoException("Comando inexistente!");
        
    }
    
    
    
    
}
