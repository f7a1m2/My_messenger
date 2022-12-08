import java.net.*;
import java.io.*;

class ServThread implements Runnable {
    private Thread _t;
    private Socket _s; 
    private PrintWriter _out;
    private BufferedReader _in;
    private Serveur Serv;
    private int _numClient=0;
    
    ServThread(Socket s, Serveur blablaServ) {
        Serv=blablaServ; 
        _s=s;
    try {
        _out =new PrintWriter(_s.getOutputStream());
        _in =new BufferedReader(new InputStreamReader(_s.getInputStream()));
        _numClient = blablaServ.addClient(_out);
    } catch(IOException e){}
        _t =new Thread(this); 
        _t.start(); 
    }

    public void run() {
        String message =""; 
        System.out.println("Un nouveau client s'est connecte, no "+_numClient);
    try {
        char charCur[] =new char[1];
        while(_in.read(charCur, 0, 1)!=-1) {
            if(charCur[0] !='\u0000'&& charCur[0] !='\n'&& charCur[0] !='\r')
                message += charCur[0];
            else if(!message.equalsIgnoreCase("")) {
            if(charCur[0]=='\u0000') 
                Serv.sendAll(message,""+charCur[0]);
            else Serv.sendAll(message,""); 
          message ="";
            }
        }
    }catch(Exception e){}
        finally
    {
    try {
        System.out.println("Le client no "+_numClient+" s'est deconnecte");
        Serv.delClient(_numClient); 
        _s.close();
    }catch(IOException e){}
    }
    
    }
}

