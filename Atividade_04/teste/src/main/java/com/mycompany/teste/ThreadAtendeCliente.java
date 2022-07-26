package com.mycompany.teste;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

public class ThreadAtendeCliente implements Runnable {

    private JTextArea tArea;
    private Socket conexao;
    private DataInputStream entrada;

    public ThreadAtendeCliente() {
    }

    public ThreadAtendeCliente(JTextArea jT, Socket c) {
        this.tArea = jT;
        this.conexao = c;
    }

    @Override
    public void run() {
        try {
            this.tArea.append("Conexão estabelecida...\n");
            entrada = new DataInputStream(conexao.getInputStream());
            for(int i=0;i<20;i++) {            
                this.tArea.append("Informação recebida:\t" + entrada.readUTF());
                scrollDown();
            }
            conexao.close();
        } catch (IOException ex) {
            this.tArea.append("\t");
        }
        this.tArea.append("Fechando conexão...\n");

    }

    public void scrollDown() {
        tArea.setCaretPosition(tArea.getText().length());
    }

}
