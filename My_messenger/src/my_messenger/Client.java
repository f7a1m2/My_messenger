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
  
    public Client(Principale principale,Socket soc) {
        setPrincipale(principale);
        this.setSocket(soc);
    }

    public Client(Principale principale) {
        setPrincipale(principale);
    }

    public void setChangement(){
        try {
            this.out = new PrintWriter(this.getSocket().getOutputStream());
            InputStreamReader rin = new InputStreamReader(this.getSocket().getInputStream());
            this.in = new BufferedReader(rin);     
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setSocket(Socket a){
        this.clientSocket = a;
        this.setChangement();
    }
    public Socket getSocket(){
        return this.clientSocket;
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
    
    public static Socket conect() throws Exception {
        try {
            /*
             *les informations du serveur (port et adresse IP ou nom d'hote)
             *127.0.0.1 est l'adresse local de la machine 
             */    
            Socket s = new Socket("127.0.0.1",5000);
            return s;
        } catch (IOException e) {
            e.printStackTrace();
            throw e ;
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
        String msg = getIndex().getZone_de_message().getText();
        out.println(msg);
        out.flush();
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
