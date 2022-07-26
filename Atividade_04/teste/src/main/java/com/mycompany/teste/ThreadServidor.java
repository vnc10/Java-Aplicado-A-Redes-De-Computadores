package com.mycompany.teste;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

public class ThreadServidor implements Runnable {

    private JTextArea tArea;    
    private ServerSocket servidor;
    private int porta;

    public ThreadServidor() {
    }

    public ThreadServidor(JTextArea jT, int porta) {
        this.tArea = jT;
        this.porta = porta;
    }

    @Override
    public void run() {
        try {
            servidor = new ServerSocket(porta);            
            
            while (true) {                     
                this.tArea.append("Aguardando conexão...\n\n");            
                new Thread(new ThreadAtendeCliente(tArea, servidor.accept())).start();
            }            
            
        } catch (IOException ex) {
            Logger.getLogger(ThreadServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
