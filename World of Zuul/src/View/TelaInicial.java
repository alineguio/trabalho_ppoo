/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controll.Controlador;
import Model.Util.AmbienteException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author leoam
 */
public class TelaInicial extends JPanel{
    
    public TelaInicial() throws AmbienteException {
        
        montarJanela();
    }
    private void montarJanela(){
        this.setBackground(Color.black);
        this.setLayout(new GridLayout(4, 1));
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Font fonte = new Font(Font.MONOSPACED,Font.PLAIN,30);
        JLabel rotulo = new JLabel("Blackout Hunt");
        rotulo.setForeground(Color.white);
        rotulo.setHorizontalAlignment(JLabel.CENTER);
        rotulo.setFont(fonte);
        this.add(rotulo);
        JButton jb = new JButton("Jogar!");
        jb.setBackground(new Color(143, 186, 255,100));
        jb.setFont(fonte);
        jb.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            Controlador.getInstance().jogar();
                        } catch (AmbienteException ex) {
                            System.out.println("Ambiente inicial não encontrado!");                        }
                    }
                }
        );
        this.add(jb);
        this.add(Box.createHorizontalGlue());
        jb = new JButton("Créditos");
        jb.setBackground(new Color(143, 186, 255,100));
        jb.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(((JButton)e.getSource()).getParent(), 
                                "Pensado e desenvolvido por: \n"
                                        + "Aline Rodrigues Guimarães \n"
                                        + "Álvaro Martins Espíndola\n"
                                        + "Leonardo Amorim de Sena \n"
                                        + "Luiz Otávio Andrade Soares\n\n"
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
        
    }
}
