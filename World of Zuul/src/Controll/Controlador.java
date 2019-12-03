/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controll;

import Model.Util.Ambiente;
import Model.Util.AmbienteException;
import Model.Util.Analisador;
import Model.Util.Comando;
import Model.Util.GameOverException;
import Model.Util.ItemException;
import Model.Util.JogadorException;
import Model.Util.Jogo;
import View.TelaInicial;
import View.TelaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author leoam
 */

/** Controlador do Jogo 
 * 
 * @author alfarr
 */
public class Controlador implements ControladorInterface,ActionListener{
    private JFrame janelaPrincipal;
    private TelaPrincipal tp;
    private Jogo jogoModel;
    private static Controlador instance = null;
    private final JogadorController jogadorController;
    private final Analisador analisador;

    
    /** Construtor da GUI, do core do jogo, do Controlador da classe Jogador e do Analisador
     * 
     * @throws AmbienteException - lugar de início não encontrado
     */
    private Controlador() throws AmbienteException{
        tp = null;
        janelaPrincipal = new TelaInicial(this);
        janelaPrincipal.setVisible(true);
        jogoModel = Jogo.getInstance(null, criarAmbientes());
        jogadorController = JogadorController.getInstance(jogoModel.getAmbiente("tv"));     
        analisador = Analisador.getInstance();
    }
    
    /** Construtor Singleton
     * 
     * @return Objeto da classe Controlador
     * @throws Model.Util.AmbienteException - lugar de início não encontrado
     */
    public static Controlador getInstance() throws AmbienteException{
        if (instance == null){
            instance = new Controlador();
        }
        
        return instance;
    }
    
    /** Método que inicializa o jogo e desenha a tela
     * 
     * @throws Model.Util.AmbienteException
     */
    @Override
    public void jogar() throws AmbienteException{
        tp = new TelaPrincipal();
        janelaPrincipal.dispose();
        janelaPrincipal = tp;
        janelaPrincipal.setVisible(true);        
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
    
    /** Método para tratar o comando passado via caixa de texto na GUI
     * 
     * @param ae - Evento da ação
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        String comando = ((JTextField)ae.getSource()).getText();
        
        Comando cmd = analisador.pegaComando(comando);
        try {
            switch(cmd.getPalavraComando()){
            case "sair":
                jogadorController.sair();
                break;
            case "ir":
                if (jogadorController.abrirPorta(cmd.getSegundaPalavra())){
                    tp.abrirPorta(cmd.getSegundaPalavra());
                } else {
                    tp.portaTrancada();
                }
                break;
            case "chave":
                jogadorController.usarItem(null); // Arrumar com código da Aline!
                break;
            default:
                break;
        }
        } catch (JogadorException | ItemException ex) {
            System.out.println("Erro em Controlador: " + ex.getMessage());
        } catch (GameOverException ex) {
            if(ex.getMessage().equals("vitoria")){
                tp.plantarBomba(true);
            } else {
                tp.plantarBomba(false);
            }
        }
    }

    

}
