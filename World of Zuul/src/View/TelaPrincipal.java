/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Util.ImagePanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
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
import javax.swing.border.Border;
import javax.swing.border.LineBorder;


/**
 *
 * @author leoam
 */
public class TelaPrincipal {
    private JPanel painelEsquerda;
    private JPanel painelDireita;
    private JPanel painelBaixo;
    private ImagePanel painelCentro;
    private JFrame janela;
    private GridLayout gridLayoutCentro;
    private HashMap<String,int[]> localPosicao;
    private JTextField inpTentativasRestantes;
    private JTextField inpDurabilidadeChaveMestra;
    private JTextArea inpDicas;
    private JTextArea inpInfos;
    private JTextField inpEntrada;
    private Clip musicaPrincipal;
    
    
    public TelaPrincipal() {
        localPosicao = new HashMap<>();
        inicializarHasMap();
        
        
        janela = new JFrame("Jogo!");
        
        gridLayoutCentro = new GridLayout(8, 11);
        

        montarPainelEsq();
        montarPainelDir();
        montarPainelBaixo();
        montarPainelCentro();
        montarJanela();
        tocarMusica();
    }
    
    private void tocarMusica(){
        try{
            
            
            URL som = getClass().getClassLoader().getResource("assets/sounds/musica.wav");
            
            AudioInputStream audioInputStream =  AudioSystem.getAudioInputStream(som); 
            
            // create clip reference 
            musicaPrincipal = AudioSystem.getClip(); 

            // open audioInputStream to the clip 
            musicaPrincipal.open(audioInputStream); 

            musicaPrincipal.loop(Clip.LOOP_CONTINUOUSLY);
            musicaPrincipal.start();
            //System.out.println(caminho);
        }catch(Exception e){
            JOptionPane.showMessageDialog(janela, e.getMessage());
        }
    }
    
    private void tocaEfeitosSonoros(String nomeMusica){
        try{
            URL som = getClass().getClassLoader().getResource("assets/sounds/"+nomeMusica);       
            AudioInputStream audioInputStream =  AudioSystem.getAudioInputStream(som); 
            Clip clip = AudioSystem.getClip(); 
            clip.open(audioInputStream); 

            clip.start();
        }catch(Exception e){
            JOptionPane.showMessageDialog(janela, "Não foi possível reproduzir um som: "+nomeMusica );
        }
    }
    
    public void abrirPorta(String novoAmbiente){
        try{
            escurecerCenario();
            painelCentro.revalidate();
            painelCentro.repaint();
            TimeUnit.SECONDS.sleep(1);
            tocaEfeitosSonoros("abrindo_porta.wav");
            TimeUnit.SECONDS.sleep(2);
            posicionaPersonagem(novoAmbiente);
            painelCentro.revalidate();
            painelCentro.repaint();
        }catch(Exception e){
            JOptionPane.showMessageDialog(janela, "ERRO!");
        }
        
    }
    
    
    
    private void montarJanela(){
        janela.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        janela.setResizable(false);
        janela.setLayout(new BorderLayout());
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        janela.add(painelBaixo,BorderLayout.SOUTH);
        janela.add(painelEsquerda,BorderLayout.WEST);
        janela.add(painelCentro,BorderLayout.CENTER);
        janela.add(painelDireita,BorderLayout.EAST);
        
        //janela.pack();
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
        inpDicas.setText("teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste teste ");
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
        painelBaixo.add(inpEntrada);
        
    }

    private void montarPainelCentro() {
        
        Image img = new ImageIcon(getClass().getClassLoader().getResource("assets/img/cenario.png"))
                .getImage()
                .getScaledInstance(850, 550, Image.SCALE_DEFAULT);
        
        painelCentro = new ImagePanel(img);
        painelCentro.setLayout(gridLayoutCentro);

        posicionaPersonagem("tv");
        
        
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
                    opacidade = Math.abs(conta * 30);
                    if(opacidade > 255) opacidade= 255;
                    
                    
                    Color escuro = new Color(0,0,0,opacidade);
                    rotulo.setBackground(escuro);
                    painelCentro.add(rotulo);
                }
            }
        }
    } 
    
    public void exibir(){
        janela.setVisible(true);
    }

    private void inicializarHasMap() {
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
        localPosicao.put("querto2", pos);
        
        pos = new int[2];
        pos[0] = 3;
        pos[1] = 9;
        localPosicao.put("querto3", pos);
        
        pos = new int[2];
        pos[0] = 5;
        pos[1] = 8;
        localPosicao.put("querto4", pos);
        
        pos = new int[2];
        pos[0] = 5;
        pos[1] = 9;
        localPosicao.put("banheiro2", pos);
        
        
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
    }
    
    
    
}

