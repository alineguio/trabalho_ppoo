/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controll;

import Model.Util.Ambiente;
import Model.Util.AmbienteException;
import Model.Util.Analisador;
import Model.Util.ChaveMestra;
import Model.Util.Comando;
import Model.Util.ComandoException;
import Model.Util.Dica;
import Model.Util.GameOverException;
import Model.Util.Item;
import Model.Util.ItemException;
import Model.Util.Jogador;
import Model.Util.JogadorException;
import Model.Util.Jogo;
import Model.Util.PalavrasComando;
import Model.Util.Tesouro;
import View.TelaInicial;
import View.TelaPrincipal;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextField;


public class Controlador implements ControladorInterface,ActionListener{
    private JFrame janelaPrincipal;
    private TelaPrincipal tp;
    private final Jogo jogoModel;
    private static Controlador instance = null;
    private final JogadorController jogadorController;
    private final Analisador analisador;
    private final ItemController itemController;

    
    /** Construtor da GUI, do core do jogo, do Controlador da classe Jogador e do Analisador
     * 
     * @throws AmbienteException - lugar de início não encontrado
     */
    private Controlador() throws AmbienteException{
        tp = null;
        janelaPrincipal = new JFrame("Jogo!");
        ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("assets/img/personagem.png"));
        janelaPrincipal.setIconImage(img.getImage());
        janelaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janelaPrincipal.setMinimumSize(new Dimension(500,500));
        janelaPrincipal.add(new TelaInicial());
        janelaPrincipal.setVisible(true);
        janelaPrincipal.pack();
        itemController = new ItemController();
        ArrayList<Ambiente> ambientes = criarAmbientes();
        jogoModel = Jogo.getInstance(itemController.getGeneratedItens(ambientes), ambientes);
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
        janelaPrincipal.setPreferredSize(new Dimension(1900,1900));
        janelaPrincipal.setExtendedState( janelaPrincipal.getExtendedState()|JFrame.MAXIMIZED_BOTH );
        
        
        janelaPrincipal.dispose();
        
        janelaPrincipal.setUndecorated(true);
        janelaPrincipal.setVisible(true);
        //janelaPrincipal.setResizable(false);
        
        janelaPrincipal.setVisible(true);
        tp = new TelaPrincipal();
        Container cp = janelaPrincipal.getContentPane();
        cp.removeAll();
        
        cp.add(tp);
        
        
        boasVindas();
        tp.setTentativasRestantes(Jogador.getInstance().getChances());
        tp.setDurabilidadeChave(0);
        janelaPrincipal.pack();
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
      
        try {
            String comando = ((JTextField)ae.getSource()).getText();
            Comando cmd = analisador.pegaComando(comando);
            switch(cmd.pegaPalavra(0)){
            case "sair":
                tp.exibirAlerta("Obrigado por jogar! Até mais!");
                System.exit(0);
                break;
            case "abrir":
                if(Jogador.getInstance().getAmbienteAtual().getAmbiente(cmd.pegaPalavra(1)) != null){
                    if (jogadorController.abrirPorta(cmd.pegaPalavra(1))){
                        tp.abrirPorta(cmd.pegaPalavra(1));
                        tp.setInfos("As saídas são: " + Jogador.getInstance().getAmbienteAtual().saidasToString());
                        
                        if(jogoModel.verificaSeHaItem(Jogador.getInstance().getAmbienteAtual()) != null){
                            Item item = jogoModel.verificaSeHaItem(Jogador.getInstance().getAmbienteAtual());
                            if(!Jogador.getInstance().getInventario().contains(item)){
                                Jogador.getInstance().pegarItem(item);

                                if(item instanceof Dica){
                                    tp.acrescentaDica(item.fazerAcao());
                                }
                                
                                if(item instanceof ChaveMestra){
                                    tp.setDurabilidadeChave(ChaveMestra.getInstance().getUsos());
                                }
                            }
                        }
                    } else {
                        tp.portaTrancada();
                    }
                    tp.setTentativasRestantes(Jogador.getInstance().getChances());
                } else {
                    tp.setInfos("Não há saídas para esse lugar!");
                }
                break;
            case "chave":
                jogadorController.usarChave();
                tp.abrirPorta(cmd.pegaPalavra(1));
                Jogador.getInstance().setAmbienteAtual(jogoModel.getAmbiente(cmd.pegaPalavra(1)));
                tp.setDurabilidadeChave(ChaveMestra.getInstance().getUsos());
                tp.setInfos("As saídas são: " + Jogador.getInstance().getAmbienteAtual().saidasToString());
                break;
            case "ajuda":
                tp.setInfos("Os comandos possíveis são: \n" + PalavrasComando.getInstance().getComandos());
                break;
            case "observar":
                tp.setInfos("As saídas são: " + Jogador.getInstance().getAmbienteAtual().saidasToString());
                break;
            case "explodir":
                Tesouro.getInstance().fazerAcao();
            default:
                break;
            }
        } catch (ItemException | ComandoException ex) {
            System.err.println("Erro em Controlador: " + ex.getMessage());
        } catch (GameOverException ex) {

            boolean venceu = false;

            if(ex.getMessage().equals("vitoria")){
                tp.plantarBomba(true);
                venceu = true;
            } else {
                tp.plantarBomba(false);
            }
        } catch (JogadorException ex){
            tp.setInfos(ex.getMessage());
        } catch (Exception e){
            System.err.println(e.getMessage() + "Erro na interface");
        }
        
        
        
    }

    @Override
    public void boasVindas() {
        tp.setInfos("Bem-vindo ao Blackout Hunt");
        tp.setInfos("Os comandos possíveis são: " + PalavrasComando.getInstance().getComandos());
    }

    

}
