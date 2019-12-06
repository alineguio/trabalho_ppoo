/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Util;



public class Comando {
    private final String[] palavras_comando;
    
    /**Construtor de comando
     * 
     * @param linha - linha completa do comando digitado
     */
    public Comando(String linha) {
        this.palavras_comando = linha.split(" ");
    }
    
    /** Pega palavra específica da linha de comando
     * 
     * @param index - posição da palavra na linha do comando
     * @return
     * @throws ComandoException 
     */
    public String pegaPalavra(int index) throws ComandoException{
        try{
            return palavras_comando[index];
        } catch (ArrayIndexOutOfBoundsException e){
            throw new ComandoException("Palavra não encontrada no comando");
        }
    }
            
}
