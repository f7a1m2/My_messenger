import java.io.*;

class Commandes implements Runnable {
  Serveur _blablaServ; 
  BufferedReader _in;
  String _strCommande="";
  Thread _t;
    
  Commandes(Serveur blablaServ) {
    _blablaServ=blablaServ;    
    _in =new BufferedReader(new InputStreamReader(System.in));
    _t =new Thread(this); 
    _t.start();
  }

  public void run() {
    try
  {
    while((_strCommande=_in.readLine())!=null) {
      if(_strCommande.equalsIgnoreCase("quit"))
          System.exit(0);
      else  if(_strCommande.equalsIgnoreCase("total")) {
          System.out.println("Nombre de connectes : "+_blablaServ.getNbClients());
          System.out.println("--------");
      } 
      else {
          System.out.println("Cette commande n'est pas supportee");
          System.out.println("Quitter : \"quit\"");
          System.out.println("Nombre de connectes : \"total\"");
          System.out.println("--------");
      }
        System.out.flush();
    }
  } catch(IOException e) {}
  }
}


