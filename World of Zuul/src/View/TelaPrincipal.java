/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controll.Controlador;
import Model.Util.ImagePanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;


/**
 *
 * @author leoam
 */
public class TelaPrincipal extends JFrame{
    private JPanel painelEsquerda;
    private JPanel painelDireita;
    private JPanel painelBaixo;
    private ImagePanel painelCentro;
    private GridLayout gridLayoutCentro;
    private HashMap<String,int[]> localPosicao;
    private JTextField inpTentativasRestantes;
    private JTextField inpDurabilidadeChaveMestra;
    private JTextArea inpDicas;
    private JTextArea inpInfos;
    private JTextField inpEntrada;
    private Clip musicaPrincipal;
    private String localAtual;
    private Controlador controlador;
    
    /** Método construror da Tela Principal do jogo
    * @param controlador - Controlador que receberá os comandos do usuário
    */
    public TelaPrincipal(Controlador controlador) {
        super("Jogo!");
        this.controlador = controlador;
        localAtual = "tv";
        localPosicao = new HashMap<>();
        inicializarHashMap();
      
        
        gridLayoutCentro = new GridLayout(8, 11);
        

        montarPainelEsq();
        montarPainelDir();
        montarPainelBaixo();
        montarPainelCentro();
        montarJanela();
        tocarMusica("musica.wav");
    }
    
    /** Seta o JTextField de "tentativas restantes" de acordo com o parâmetro. 
    * @param numero - Novo número de tentativas restantes
    */
    
    public void setTentativasRestantes(int numero){
        inpTentativasRestantes.setText(String.valueOf(numero));
    }
    
    /** Seta a durabilidade da chave mestra de acordo com o parâmetro
    * @param numero - Nova durabilidade da chave mestra
    */
    public void setDurabilidadeChave(int numero){
        inpDurabilidadeChaveMestra.setText(String.valueOf(numero));
    }
    
    /** Seta o texto de dicas (apaga o conteúdo anterior e escreve segundo o parâmetro)
    * @param dicas - Novas dicas que serão exibidas para o usuário
    */
    public void setDicas(String dicas){
        inpDicas.setText(dicas);
    }
    
    /** Acrescenta uma nova dica (NÃO apaga o conteúdo anterior, apenas concatena)
    * @param dica - Texto da nova dica que deve ser concatenada
    */
    public void acrescentaDica(String dica){
        inpDicas.setText(inpDicas.getText() +"\n" +dica);
    }
    
    /** Seta o texto de informações (caixa de texto logo acima da entrada do usuário)
    * @param texto - Texto que deve ser exibidio na caixa "Informações"
    */
    public void setInfos(String texto){
        inpInfos.setText(texto);
    }
    
    
    /** Método para terminar o jogo
    * @param vitoria - Define se o usuário plantou a bomba no local certo (portanto venceu o jogo) ou não
    */
    public void plantarBomba(boolean vitoria){
        escurecerCenario();
        esperarSegundos(2);
        musicaPrincipal.stop();
        tocaEfeitosSonoros("suspense.wav");
        esperarSegundos(12);
        tocaEfeitosSonoros("explosao.wav");
        esperarSegundos(2);
        if(vitoria){
            vitoriaFinal();
            tocaEfeitosSonoros("sucesso.wav");
            tocarMusica("vitoria.wav");
        }else{
            esperarSegundos(2);
            tocaEfeitosSonoros("errou.wav");
        }
    }
    
    private void tocarMusica(String caminho){
        try{
            URL som = getClass().getClassLoader().getResource("assets/sounds/"+caminho);
            AudioInputStream audioInputStream =  AudioSystem.getAudioInputStream(som); 
            musicaPrincipal = AudioSystem.getClip(); 
            musicaPrincipal.open(audioInputStream); 
            musicaPrincipal.loop(Clip.LOOP_CONTINUOUSLY);
            musicaPrincipal.start();
        }catch(Exception e){
            JOptionPane.showMessageDialog(this,"Erro ao reproduzir som: "+e.getMessage());
        }
    }
    
    private void tocaEfeitosSonoros(String nomeMusica){
        try{
            URL som = getClass().getClassLoader().getResource("assets/sounds/"+nomeMusica);       
            AudioInputStream audioInputStream =  AudioSystem.getAudioInputStream(som); 
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(0);
            clip.start();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Não foi possível reproduzir um som: "+nomeMusica );
        }
    }
    
    private void esperarSegundos(int tempo){
        try{
            TimeUnit.SECONDS.sleep(tempo);
        }catch(Exception e){
            System.err.println("Erro ao esperar "+tempo+" segundos.");
        }
    }
    
    /** Muda o personagem para o local passado por parâmetro 
    * @param novoAmbiente - Novas dicas que serão exibidas para o usuário (Opções váidas: [escritorio,jantar,tv,jardim,cozinha,banheiro1,quarto1,quarto2,quarto3,quarto4,banheiro2])
    */
    public void abrirPorta(String novoAmbiente){
        escurecerCenario();
        esperarSegundos(1);
        tocaEfeitosSonoros("abrindo_porta.wav");
        esperarSegundos(2);
        posicionaPersonagem(novoAmbiente);
    }
    /** Personagem tentou mudar de ambiente porém sem sucesso 
    */
    public void portaTrancada(){
        escurecerCenario();
        esperarSegundos(1);
        tocaEfeitosSonoros("porta_fechada.wav");
        esperarSegundos(2);
        posicionaPersonagem(localAtual);
    }
    
    private void montarJanela(){
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        //this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.add(painelBaixo,BorderLayout.SOUTH);
        this.add(painelEsquerda,BorderLayout.WEST);
        this.add(painelCentro,BorderLayout.CENTER);
        this.add(painelDireita,BorderLayout.EAST);
        
        //this.pack();
    }

    private void montarPainelEsq() {
        painelEsquerda = new JPanel();
        
        painelEsquerda.setLayout(new BoxLayout(painelEsquerda, BoxLayout.Y_AXIS));
        
        JLabel rotulo = new JLabel("<html>Número de tentativas restantes:</html>");
        rotulo.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
        rotulo.setPreferredSize(new Dimension(50,100));
        painelEsquerda.add(rotulo);
        
        inpTentativasRestantes = new JTextField("0");
        inpTentativasRestantes.setEditable(false);
        inpTentativasRestantes.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
        inpTentativasRestantes.setMaximumSize(new Dimension(100,50));
        inpTentativasRestantes.setBorder(new LineBorder(Color.RED,0) );
        painelEsquerda.add(inpTentativasRestantes);
        
        
        rotulo = new JLabel("<html>Durabilidade da chave mestra:</html>");
        rotulo.setPreferredSize(new Dimension(200,100));
        rotulo.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
        painelEsquerda.add(rotulo);
        
        inpDurabilidadeChaveMestra = new JTextField("0");
        inpDurabilidadeChaveMestra.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
        inpDurabilidadeChaveMestra.setEditable(false);
        inpDurabilidadeChaveMestra.setMaximumSize(new Dimension(100,50));
        inpDurabilidadeChaveMestra.setBorder(new LineBorder(Color.RED,0) );
        painelEsquerda.add(inpDurabilidadeChaveMestra);
        
    }

    private void montarPainelDir() {
        painelDireita = new JPanel();
        painelDireita.setLayout(new BoxLayout(painelDireita, BoxLayout.Y_AXIS));
        /* LABEL TESTE! */
        JLabel rotulo = new JLabel("<html>Dicas encontradas:</html>");
        rotulo.setPreferredSize(new Dimension(200,100));
        rotulo.setFont(new Font(Font.DIALOG,Font.BOLD,20));
        painelDireita.add(rotulo);
        
        inpDicas = new JTextArea();
        inpDicas.setBackground(new Color(0,0,0,0));
        inpDicas.setText("");
        inpDicas.setLineWrap(true);
        inpDicas.setWrapStyleWord(true);
        inpDicas.setFont(new Font(Font.DIALOG,Font.BOLD,15));
        inpDicas.setEditable(false);
        inpDicas.setColumns(1);
        JScrollPane scrollPane = new JScrollPane(inpDicas);
        painelDireita.add(scrollPane);
        
    }

    private void montarPainelBaixo() {
        painelBaixo = new JPanel();
        painelBaixo.setLayout(new BoxLayout(painelBaixo, BoxLayout.Y_AXIS));
        
        inpInfos = new JTextArea();
        inpInfos.setFont(new Font(Font.DIALOG,Font.ITALIC,16));
        inpInfos.setRows(8);
        inpInfos.setEditable(false);
        
        painelBaixo.add(inpInfos);
        
        inpEntrada = new JTextField();
        inpEntrada.setFont(new Font(Font.DIALOG_INPUT,Font.ITALIC,16));
        inpEntrada.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controlador.acaoComando(inpEntrada.getText());
                        inpEntrada.setText("");
                    }
                }
        );
        
        painelBaixo.add(inpEntrada);
        
    }

    private void montarPainelCentro() {
        
        Image img = new ImageIcon(getClass().getClassLoader().getResource("assets/img/cenario.png"))
                .getImage()
                .getScaledInstance(850, 550, Image.SCALE_DEFAULT);
        
        painelCentro = new ImagePanel(img);
        painelCentro.setLayout(gridLayoutCentro);

        posicionaPersonagem(localAtual);
        
        
    }
    private void posicionaPersonagem(String local){
        painelCentro.removeAll();
        int posI = localPosicao.get(local)[0];
        int posJ = localPosicao.get(local)[1];
        
        
        for(int i=0;i<gridLayoutCentro.getRows();i++){
            for(int j=0;j<gridLayoutCentro.getColumns();j++){
                if(i==posI && j==posJ){
                    JLabel rotulo = new JLabel("");
                    ImageIcon imgPersonagem = new ImageIcon( (new ImageIcon(getClass().getClassLoader().getResource("assets/img/pMexe.gif")))
                            .getImage()
                            .getScaledInstance(50, 50, Image.SCALE_DEFAULT));
                     
                   rotulo.setIcon(imgPersonagem);
                    rotulo.setHorizontalAlignment(JLabel.CENTER);
                    rotulo.setVerticalAlignment(JLabel.CENTER);
                    painelCentro.add(rotulo );
                }else{
                    JLabel rotulo = new JLabel("");
                    rotulo.setOpaque(true);
                    int opacidade;
                    int conta = Math.abs(i - posI) + Math.abs(j - posJ) - 2;
                    opacidade = Math.abs(conta * 80);
                    if(opacidade > 255) opacidade= 255;
                    Color escuro = new Color(0,0,0,opacidade);
                    rotulo.setBackground(escuro);
                    painelCentro.add(rotulo);
                }
            }
        }
        localAtual = local;
        painelCentro.revalidate();
        painelCentro.repaint();
    } 
    private void vitoriaFinal(){
        
        painelCentro.removeAll();
        int posI = localPosicao.get(localAtual)[0];
        int posJ = localPosicao.get(localAtual)[1];
        
        
        
        for(int i=0;i<gridLayoutCentro.getRows();i++){
            for(int j=0;j<gridLayoutCentro.getColumns();j++){
                if(i==posI && posJ == j){

                    JLabel rotulo = new JLabel("");
                    ImageIcon imgPersonagem = new ImageIcon( (new ImageIcon(getClass().getClassLoader().getResource("assets/img/diamanteMexe.gif")))
                            .getImage()
                            .getScaledInstance(50, 50, Image.SCALE_DEFAULT));

                    rotulo.setIcon(imgPersonagem);
                    rotulo.setHorizontalAlignment(JLabel.CENTER);
                    rotulo.setVerticalAlignment(JLabel.CENTER);
                    painelCentro.add(rotulo );
                }else if(i == posI + 1  && posJ == j){
                    JLabel rotulo = new JLabel("");
                    ImageIcon imgPersonagem = new ImageIcon( (new ImageIcon(getClass().getClassLoader().getResource("assets/img/pMexe.gif")))
                            .getImage()
                            .getScaledInstance(50, 50, Image.SCALE_DEFAULT));

                    rotulo.setIcon(imgPersonagem);
                    rotulo.setHorizontalAlignment(JLabel.CENTER);
                    rotulo.setVerticalAlignment(JLabel.CENTER);
                    painelCentro.add(rotulo );
                }else{
                    JLabel rotulo = new JLabel("");
                    painelCentro.add(rotulo);
                }
            }
        }
        painelCentro.revalidate();
        painelCentro.repaint();
    } 
    


    private void inicializarHashMap() {
        int pos[] = new int[2];
        
        pos[0] = 1;
        pos[1] = 1;
        localPosicao.put("escritorio", pos);
        
        pos = new int[2];
        pos[0] = 3;
        pos[1] = 3;
        localPosicao.put("jantar", pos);
        
        pos = new int[2];
        pos[0] = 4;
        pos[1] = 1;
        localPosicao.put("tv", pos);
        
        pos = new int[2];
        pos[0] = 7;
        pos[1] = 3;
        localPosicao.put("jardim", pos);
        
        pos = new int[2];
        pos[0] = 5;
        pos[1] = 3;
        localPosicao.put("cozinha", pos);
        
        pos = new int[2];
        pos[0] = 5;
        pos[1] = 5;
        localPosicao.put("banheiro1", pos);
        
        pos = new int[2];
        pos[0] = 2;
        pos[1] = 6;
        localPosicao.put("quarto1", pos);
        
        pos = new int[2];
        pos[0] = 2;
        pos[1] = 8;
        localPosicao.put("quarto2", pos);
        
        pos = new int[2];
        pos[0] = 3;
        pos[1] = 9;
        localPosicao.put("quarto3", pos);
        
        pos = new int[2];
        pos[0] = 5;
        pos[1] = 8;
        localPosicao.put("quarto4", pos);
        
        pos = new int[2];
        pos[0] = 5;
        pos[1] = 9;
        localPosicao.put("banheiro2", pos);
        
        pos = new int[2];
        pos[0] = 3;
        pos[1] = 6;
        localPosicao.put("corredor", pos);
        
    }

    private void escurecerCenario() {
        painelCentro.removeAll();
        for(int i=0;i<gridLayoutCentro.getRows();i++){
            for(int j=0;j<gridLayoutCentro.getColumns();j++){
                JLabel rotulo = new JLabel("");
                rotulo.setOpaque(true);
                Color escuro = new Color(0,0,0,255);
                rotulo.setBackground(escuro);
                painelCentro.add(rotulo);
            }
        }
        painelCentro.revalidate();
        painelCentro.repaint();
    }
    
    
    
}

