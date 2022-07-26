package com.mycompany.teste;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.ObjectOutputStream;

public class ThreadCliente implements Runnable {

    private Socket conexao;
    private JTextArea tArea;
    private int porta;
    private DataInputStream entrada;
    private ObjectOutputStream saida;

    public ThreadCliente(JTextArea tA, int p) {
        this.tArea = tA;
        this.porta = p;
    }

    @Override
    public void run() {
        try {
            conexao = new Socket("localhost", porta);
            tArea.append("Conexão estabelecida...\n");
                Pessoa pessoa = new Pessoa("Vini", 15);
                tArea.append("Enviando dados de pessoa: " + pessoa.getNome() + pessoa.getIdade() + "\n");
                scrollDown();
                saida = new ObjectOutputStream(conexao.getOutputStream());
                saida.writeObject(pessoa);
                Thread.sleep(100);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(tArea, "Servidor terminou a conexão!");
        } catch (InterruptedException ex) {
            ex.getMessage();
        } finally {
            try {
                saida.flush();
                tArea.append("Fechando conexão...\n");
                conexao.close();
            } catch (IOException ex) {
                Logger.getLogger(ThreadCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void scrollDown() {
        tArea.setCaretPosition(tArea.getText().length());
    }

}
