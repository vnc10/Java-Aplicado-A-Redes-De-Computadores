package utfpr.rmi.exemplo2;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClienteRMI {

    public static void main(String[] args) {

        try {

            Registry registro = LocateRegistry.getRegistry("localhost", 1099);

            Calculadora stub = (Calculadora) registro.lookup("election");

            System.out.println(stub.add(13, 2));
            System.out.println(stub.sub(13, 2));
            System.out.println(stub.mul(13, 2));
            System.out.println(stub.div(13, 2));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
