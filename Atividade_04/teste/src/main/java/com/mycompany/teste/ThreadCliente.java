package com.mycompany.teste;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Integer.parseInt;

public class ThreadCliente implements Runnable {

    private Socket conexao;
    private String jTextField1;

    private String jTextField3;

    private JTextField jTextField2;
    private int porta;
    private DataInputStream entrada;
    private ObjectOutputStream saida;

    public ThreadCliente(String jTextField1, JTextField JTextField2, String JTextField3, int p) {
        this.jTextField1 = jTextField1;
        this.jTextField2 = JTextField2;
        this.jTextField3 = JTextField3;
        this.porta = p;
    }

    @Override
    public void run() {
        try {
            conexao = new Socket("localhost", porta);
            Pessoa pessoa = new Pessoa(jTextField1, parseInt(jTextField3));
            saida = new ObjectOutputStream(conexao.getOutputStream());
            saida.writeObject(pessoa);
            BufferedReader fromServer = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            String dados = fromServer.readLine();
            jTextField2.setText(dados);
            Thread.sleep(100);

        } catch (IOException | InterruptedException ex) {
            ex.getMessage();
        } finally {
            try {
                saida.flush();
                conexao.close();
            } catch (IOException ex) {
                Logger.getLogger(ThreadCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
