package utfpr.rmi.exemplo2;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface Election extends Remote {

    Map<String, Integer> countVotes() throws RemoteException;

    void receiveVotes(List<String> candidates, List<Integer> votes) throws RemoteException;
}
