import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    private static Socket socket;
    private static ServerSocket server;

    private static DataInputStream entrada;
    private static DataOutputStream saida;

    public static void main(String[] args) {

        try {

            //Criar porta de recepção
            server = new ServerSocket(50000);
            socket = server.accept();

            //Criar os fluxos de entrada e saída
            entrada = new DataInputStream(socket.getInputStream());
            saida = new DataOutputStream(socket.getOutputStream());

            //Recebimento do cpf
            String cpf = entrada.readUTF();
            System.out.println(cpf);

            //Processamento do valor
            ValidarCpf validarCpf = new ValidarCpf();

            //Envio dos dados (resultado)
            saida.writeUTF(String.valueOf(validarCpf.validarCpf(cpf)));

            socket.close();

        } catch(Exception e) {
            System.out.println(e);
        }

    }





}