/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controll.Controlador;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author leoam
 */
public class TelaInicial extends JFrame{
    private Controlador c;
    public TelaInicial(Controlador c) {
        super("Bem vindo ao jogo! Clique para jogar!");
        this.c=c;
        montarJanela();
    }
    private void montarJanela(){
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        //this.setResizable(false);
        this.setPreferredSize(new Dimension(500,500));
        this.setResizable(false);
        this.setLayout(new GridLayout(4, 1));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Font fonte = new Font(Font.MONOSPACED,Font.PLAIN,30);
        JLabel rotulo = new JLabel("Bem vindo ao jogo!");
        rotulo.setHorizontalAlignment(JLabel.CENTER);
        rotulo.setFont(fonte);
        this.add(rotulo);
        JButton jb = new JButton("Jogar!");
        jb.setFont(fonte);
        jb.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        c.jogar();
                    }
                }
        );
        this.add(jb);
        this.add(Box.createHorizontalGlue());
        jb = new JButton("Créditos");
        jb.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(((JButton)e.getSource()).getParent(), 
                                "Pensado e desenvolvido por: \n"
                                        + "Aline Rodrigues Guimarães \n"
                                        + "Álvaro Martins Espíndola\n"
                                        + "Leonardo Amorim de Sena \n\n"
                                        + "Imagens: \n"
                                        + "Freepik - br.freepik.com \n\n"
                                        + "Músicas e efeitos sonoros: \n"
                                        + "Zapslat - zapsplat.com\n"
                                        + "Kevin MacLeod - incompetech.com", "Créditos",JOptionPane.INFORMATION_MESSAGE);
                        
                    }
                }
        );
        
        jb.setFont(fonte);
        this.add(jb);
        this.pack();
        
    }
}
