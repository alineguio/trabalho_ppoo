/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controll;

import Model.Util.Ambiente;
import Model.Util.AmbienteException;
import Model.Util.Jogo;
import View.TelaInicial;
import View.TelaPrincipal;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;

/**
 *
 * @author leoam
 */

/** Controlador do Jogo 
 * 
 * @author alfarr
 */
public class Controlador implements ControladorInterface{
    JFrame janelaPrincipal;
    TelaPrincipal tp;
    Jogo jogoModel;
    private static final Controlador instance = null;
    private final JogadorController jogadorController;
    private Random random;
    
    
    /** Construtor da GUI, do core do jogo e do 
     * 
     * @throws AmbienteException - lugar de início não encontrado
     */
    private Controlador() throws AmbienteException {
        tp = null;
        janelaPrincipal = new TelaInicial(this);
        janelaPrincipal.setVisible(true);
        jogoModel = Jogo.getInstance(null, criarAmbientes());
        jogadorController = JogadorController.getInstance(jogoModel.getAmbiente("tv"));        
    }
    
    /** Construtor Singleton
     * 
     * @return Objeto da classe Controlador
     * @throws Model.Util.AmbienteException - lugar de início não encontrado
     */
    public static Controlador getInstance() throws AmbienteException{
        if (instance != null){
            return instance;
        }
        
        return new Controlador();
    }
    
    /** Método que inicializa o jogo e desenha a tela
     * 
     */
    @Override
    public void jogar(){
        tp = new TelaPrincipal(this);
        janelaPrincipal.dispose();
        janelaPrincipal = tp;
        janelaPrincipal.setVisible(true);
        
        
    }
    
    /** Método para tratar o comando passado via caixa de texto na GUI
     * 
     * @param comando - texto digitado pelo usuário
     */
    @Override
    public void acaoComando(String comando){
        
    }
    
    /** Crie os ambientes fixos do jogo e retorna para criar o Model Jogo
     * 
     */
    private ArrayList<Ambiente> criarAmbientes(){
        Ambiente escritorio, jantar, tv, jardim, cozinha, banheiro1, quarto1,
                quarto2, quarto3, quarto4, banheiro2, corredor;
        
        // Inicializa com o nome do ambiente
        escritorio = new Ambiente("escritorio");
        jantar = new Ambiente("jantar");
        tv = new Ambiente("tv");
        jardim = new Ambiente("jardim");
        cozinha = new Ambiente("cozinha");
        banheiro1 = new Ambiente("banheiro1");
        quarto1 = new Ambiente("quarto1");
        quarto2 = new Ambiente("quarto2");
        quarto3 = new Ambiente("quarto3");
        quarto4 = new Ambiente("quarto4");
        banheiro2 = new Ambiente("banheiro2");
        corredor = new Ambiente("corredor");
        
        // Define as adjacências de cada cômodo
        
        escritorio.ajustarSaidas("tv", tv);
        
        tv.ajustarSaidas("escritorio", escritorio);
        tv.ajustarSaidas("jantar", jantar);
        tv.ajustarSaidas("jardim", jardim);
        
        jardim.ajustarSaidas("tv", tv);
        jardim.ajustarSaidas("cozinha", cozinha);
        
        cozinha.ajustarSaidas("jardim", jardim);
        cozinha.ajustarSaidas("jantar", jantar);
        
        jantar.ajustarSaidas("tv", tv);
        jantar.ajustarSaidas("cozinha", cozinha);
        jantar.ajustarSaidas("corredor", corredor);
        
        corredor.ajustarSaidas("quarto1", quarto1);
        corredor.ajustarSaidas("quarto2", quarto2);
        corredor.ajustarSaidas("quarto3", quarto3);
        corredor.ajustarSaidas("quarto4", quarto4);
        corredor.ajustarSaidas("banheiro1", banheiro1);
        corredor.ajustarSaidas("jantar", jantar);
        
        quarto1.ajustarSaidas("corredor", corredor);
        
        quarto2.ajustarSaidas("corredor", corredor);
        
        quarto3.ajustarSaidas("corredor", corredor);
        quarto3.ajustarSaidas("banheiro2", banheiro2);
        
        quarto4.ajustarSaidas("corredor", corredor);
        
        banheiro1.ajustarSaidas("corredor", corredor);
        
        banheiro2.ajustarSaidas("quarto3", quarto3);
        
        return new ArrayList<Ambiente>() {{ 
            add(escritorio);
            add(tv);
            add(jantar);
            add(jardim);
            add(cozinha);
            add(corredor);
            add(quarto1);
            add(quarto2);
            add(quarto3);
            add(quarto4);
            add(banheiro1);
            add(banheiro2);
        }};
    }

}