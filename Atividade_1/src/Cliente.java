import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Cliente {

    private static Socket socket;
    private static DataInputStream entrada;
    private static DataOutputStream saida;

    public static void main(String[] args) {

        try {

            socket = new Socket("127.0.0.1", 50000);

            entrada = new DataInputStream(socket.getInputStream());
            saida = new DataOutputStream(socket.getOutputStream());

            //Recebe do usuário algum valor
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Digite o valor do CPF: ");
            String cpf = String.valueOf(br.readLine());

            //O valor é enviado ao servidor
            saida.writeUTF(cpf);

            //Recebe-se o resultado do servidor
            String resultado = entrada.readUTF();

            //Mostra o resultado na tela
            System.out.println(resultado);

            socket.close();

        } catch(Exception e) {
            System.out.println(e);
        }

    }

}