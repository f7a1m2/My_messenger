/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my_messenger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author PC
 */
public class Client {
    
    Principale principale;
    Socket clientSocket;
    BufferedReader in;
    PrintWriter out;
    Index index;
    String nom;

    public Index getIndex() {
        return index;
    }

    public void setIndex(Index index) {
        this.index = index;
    }

    final Scanner sc = new Scanner(System.in);
  
    public Client(Principale principale) {
        setPrincipale(principale);
     
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }   

    public Principale getPrincipale() {
        return principale;
    }

    public void setPrincipale(Principale principale) {
        this.principale = principale;
    }
    
    public void conect() {
        try {
            /*
             *les informations du serveur (port et adresse IP ou nom d'hote)
             *127.0.0.1 est l'adresse local de la machine 
             */    
            clientSocket = new Socket("127.0.0.1",5000);
            //flux pour envoyer
            out = new PrintWriter(clientSocket.getOutputStream());
            //flux pour recevoir
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public String SetNomClient() {
        String name = getNom();
            name =  getPrincipale().getTextArea().getText();
            out.print(name);
            out.flush();
        return name;
    }
    
    public void Send() {
            Thread envoyer = new Thread(new Runnable() {
                String msg = getIndex().getZone_de_message().getText();
                @Override
                public void run() {
                    while(true) {
                        msg = sc.nextLine();
                        out.println(msg);
                        out.flush();
                    }
                }
            });     
            envoyer.start();
   
    }
    
    public void Receive() {
        Thread recevoir = new Thread(new Runnable() {
            String msg;
            @Override
            public void run() {
                try {
                    msg = in.readLine();
                    while(msg!=null) {
                        System.out.println(msg);
                        msg = in.readLine();
                    }
                    System.out.println("Serveur deconnecter");
                    out.close();
                    clientSocket.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }); 
        recevoir.start();
    }
}
