package com.mycompany.teste;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

public class ThreadAtendeCliente implements Runnable {

    private JTextArea tArea;
    private Socket conexao;
    private ObjectInputStream entrada;

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
            entrada = new ObjectInputStream(conexao.getInputStream());
            Pessoa p = (Pessoa) entrada.readObject();
            this.tArea.append("Informação recebida:\t" + p.getNome());
            scrollDown();
            conexao.close();
        } catch (IOException ex) {
            this.tArea.append("\t");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        this.tArea.append("Fechando conexão...\n");

    }

    public void scrollDown() {
        tArea.setCaretPosition(tArea.getText().length());
    }

}
