import java.net.*;
import java.io.*;
import java.util.*;

public class Serveur {
    private Vector _tabClients =new Vector(); 
    private int _nbClients=0;

    public static void main(String args[]) {
        Serveur Serv =new Serveur();
    try
    {
        int port = 0;
        if(args.length<=0) port= 5000;
            new Commandes(Serv);

        ServerSocket ss =new    ServerSocket(port);
        System.out.println("Welcome "+port);
        while(true) 
        {
            new ServThread(ss.accept(),Serv);
        }
    }catch(Exception e) {}
    }

    static private void printWelcome(Integer port)
    {
        System.out.println("--------");
        System.out.println("BlablaServeur : Par Minosis - Julien Defaut");
        System.out.println("Copyright : 2004 - Minosis.com");
        System.out.println("Derniere version : 10/04/2004");
        System.out.println("--------");
        System.out.println("Demarre sur le port : "+port.toString());
        System.out.println("--------");
        System.out.println("Quitter : tapez \"quit\"");
        System.out.println("Nombre de connectes : tapez \"total\"");
        System.out.println("--------");
    }
  
    synchronized public void sendAll(String message,String sLast) {
        PrintWriter out;
        for(int i =0; i < _tabClients.size(); i++) {
            out =(PrintWriter) _tabClients.elementAt(i); 
            if(out !=null) {
                out.print(message+sLast);
                out.flush(); 
            }
        }
    }

    synchronized public void delClient(int i) {
        _nbClients--;
        if(_tabClients.elementAt(i) !=null) {
            _tabClients.removeElementAt(i);
        }
    }
   
    synchronized public int addClient(PrintWriter out) {
        _nbClients++;
        _tabClients.addElement(out);
    return _tabClients.size()-1;
    }

    synchronized public int getNbClients() {
    return _nbClients; 
    }
}

