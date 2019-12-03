/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Util;

/**
 *
 * @author alfarr
 */
public class Tesouro extends Item{
    
    private static Tesouro instance = null;

    /** Construtor com o ambiente no qual o Tesouro foi encontrado
     * 
     * @param ambiente - referência ao objeto da classe Ambiente no qual foi encontrado o Tesouro
     */
    private Tesouro(Ambiente ambiente) {
        super(ambiente);
    }
    
    /** Construtor Singleton
     * 
     * @param ambiente - referência ao objeto da classe Ambiente no qual foi encontrado o Tesouro
     * @return Objeto da classe Tesouro
     */
    public static Tesouro getInstance(Ambiente ambiente){
        if (instance == null){
            instance = new Tesouro(ambiente);
        }
        
        return instance;
    }
    
    
    /** Lança exceção de GameOver de acordo com o estado do Tesouro
     * 
     * @return 
     * @throws GameOverException 
     */
    @Override
    public String fazerAcao() throws GameOverException{
        if (Jogador.getInstance().getAmbienteAtual().equals(super.getAmbiente())){
            throw new GameOverException("vitoria");
        } else {
            throw new GameOverException("derrota");
        }
            
    }
    
    
}
