package utfpr.rmi.exemplo2;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;


public class ServidorImpl implements Election {

    List<String> candidates = new ArrayList<>();

    List<Integer> votes = new ArrayList<>();

    List<Integer> sumVotes = new ArrayList<>(Collections.nCopies(3, 0));
    public ServidorImpl() {       
    }

    @Override
    public Map<String, Integer> countVotes() throws RemoteException {
        for(int i = 0; i < votes.size(); i++){
            this.sumVotes.set(i, this.sumVotes.get(i) + votes.get(i));
        }

        Map<String, Integer> mapSumVotes = new HashMap<>();
        for(int i = 0; i < candidates.size(); i++){
            mapSumVotes.put(candidates.get(i), sumVotes.get(i));
        }
        System.out.println(mapSumVotes);
        return mapSumVotes;
    }

    @Override
    public void receiveVotes(List<String> candidates, List<Integer> votes) throws RemoteException {
        this.candidates = candidates;
        this.votes = votes;
    }


    public static void main(String[] args) {
        try {
            
            ServidorImpl s = new ServidorImpl();

            Election skeleton = (Election)UnicastRemoteObject.exportObject(s, 0);
            
            Registry registro = LocateRegistry.createRegistry(1099);
            
            registro.rebind("election", skeleton);
            
            System.out.println("Servidor RMI está pronto!");
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
