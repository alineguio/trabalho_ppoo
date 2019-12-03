/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controll;

import View.TelaInicial;
import View.TelaPrincipal;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;

/**
 *
 * @author leoam
 */
public class Controlador {
    JFrame janelaPrincipal;
    TelaPrincipal tp;
    
    public Controlador() {
        tp = null;
        janelaPrincipal = new TelaInicial(this);
        janelaPrincipal.setVisible(true);
    }
    public void jogar(){
        tp = new TelaPrincipal(this);
        janelaPrincipal.dispose();
        janelaPrincipal = tp;
        janelaPrincipal.setVisible(true);
        
        
    }
    
    public void acaoComando(String comando){
        System.out.println("COMANDO: "+comando);
    }
    
}
