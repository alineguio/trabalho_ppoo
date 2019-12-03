/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controll;

import View.TelaPrincipal;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author leoam
 */
public class Main{
    public static void main(String[] args) throws Exception{
        TelaPrincipal tp = new TelaPrincipal();
        tp.exibir();
        TimeUnit.SECONDS.sleep(5);
        tp.abrirPorta("escritorio");
    }
}
