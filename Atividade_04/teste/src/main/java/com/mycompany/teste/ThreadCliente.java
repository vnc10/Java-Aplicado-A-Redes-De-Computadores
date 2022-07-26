package com.mycompany.teste;

import com.mycompany.teste.ClientInterface;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class ThreadCliente implements Runnable {

    private Socket conexao;
    private JTextArea tArea;
    private int porta;
    private DataInputStream entrada;
    private DataOutputStream saida;

    public ThreadCliente(JTextArea tA, int p) {
        this.tArea = tA;
        this.porta = p;
    }

    @Override
    public void run() {
        try {
            conexao = new Socket("localhost", porta);
            tArea.append("Conexão estabelecida...\n");
            while (!Thread.currentThread().isInterrupted()) {
                tArea.append("Enviando dados...\n");
                scrollDown();
                saida = new DataOutputStream(conexao.getOutputStream());
                saida.writeUTF("Recebendo dados do cliente...\n");
                Thread.sleep(100);
            }            
                        
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(tArea, "Servidor terminou a conexão!");
        } catch(InterruptedException ex){
            ex.getMessage();
        } finally{
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
