package com.mycompany.teste;

import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    private static Socket socket;
    private static ServerSocket server;

    private static ObjectOutputStream saida;
    private static ObjectInputStream entrada;

    public static void main(String[] args) {

        try {

            server = new ServerSocket(50000);

            socket = server.accept();

            DataOutputStream saida = new DataOutputStream(socket.getOutputStream());

            entrada = new ObjectInputStream(socket.getInputStream());

            Pessoa p = (Pessoa) entrada.readObject();

            System.out.println("Nome: " + p.getNome() + " Idade: " + p.getIdade());

            saida.writeBytes("Dados recebidos corretamente!");

            socket.close();

        } catch(Exception e) {

        }

    }
}
