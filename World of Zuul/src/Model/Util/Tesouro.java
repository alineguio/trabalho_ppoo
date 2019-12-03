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
    
<<<<<<< HEAD
    /**  Retorna se o tesouro está no ambiente presente ou não (encontrado ou não)
     * 
     * @return Verdadeiro se encontrado, falso o contrário
     */
    public boolean encontrado() {
        return encontrado;
    }

    /**  Retorna o ambiete em que o tesouro do jogo está
     * 
     * @return ambiente em que o tesouro está
     */
    public Ambiente getAmbiente() {
        return ambiente;
    }
    
     /** Define o estado do tesouro
      * 
      * @param encontrado - verdadeiro ou falso, encontrado ou não
      */
     
    public void setEncontrado(boolean encontrado) {
        this.encontrado = encontrado;
    }
=======
>>>>>>> a33caf71e333f0499ba309706e06496d1f7bea72
    
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
