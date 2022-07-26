package com.mycompany.teste;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

public class ThreadAtendeCliente implements Runnable {

    private JTextArea tArea;
    private Socket conexao;
    private ObjectInputStream entrada;

    private ObjectOutputStream saida;

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
            this.tArea.append("Informação recebida: \n");
            this.tArea.append("Nome: "+ p.getNome() + "\n");
            this.tArea.append("Idade: "+ p.getIdade() + "\n");
            DataOutputStream saida = new DataOutputStream(conexao.getOutputStream());
            saida.writeBytes("Dados recebidos corretamente!");
            scrollDown();
            conexao.close();
        } catch (IOException | ClassNotFoundException ex) {
            this.tArea.append("\t");
        }
        this.tArea.append("Fechando conexão...\n");

    }

    public void scrollDown() {
        tArea.setCaretPosition(tArea.getText().length());
    }

}
