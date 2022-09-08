package utfpr.rmi.exemplo2;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;


public class ServidorImpl implements Calculadora {

    public ServidorImpl() {       
    }

    @Override
    public long add(long a, long b) throws RemoteException {
        return a+b;
    }

    @Override
    public long sub(long a, long b) throws RemoteException {
        return a-b;
    }

    @Override
    public long mul(long a, long b) throws RemoteException {
        return a*b;
    }

    @Override
    public long div(long a, long b) throws RemoteException {
        return a/b;
    }

    public static void main(String[] args) {
        try {
            
            ServidorImpl s = new ServidorImpl();

            Calculadora skeleton = (Calculadora) UnicastRemoteObject.exportObject(s, 0);
            
            Registry registro = LocateRegistry.createRegistry(1099);
            
            registro.rebind("election", skeleton);
            
            System.out.println("Servidor RMI está pronto!");
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
