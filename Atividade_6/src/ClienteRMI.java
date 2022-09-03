package utfpr.rmi.exemplo2;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Map;

public class ClienteRMI {

    public static void main(String[] args) {

        try {

            Registry registro = LocateRegistry.getRegistry("localhost", 1099);

            Election stub = (Election) registro.lookup("election");

            final List<String> candidates = List.of(
                    "Marta",
                    "Ronaldo",
                    "Pedro");

            final List<Integer> votes = List.of(
                    10,
                    20,
                    30
            );

            stub.receiveVotes(candidates, votes);
            System.out.println(stub.countVotes());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
