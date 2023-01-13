package MAIN.ServerConnect;
import java.io.*;
import java.net.*;

import javax.swing.JOptionPane;

// import DAO.Demande;
// import DAO.Repas;
public class ServerClient {
    public static ServerClient c=null;
    public static Socket s;
    private ServerClient(String adress,int port,String role){
        Runtime.getRuntime().addShutdownHook(new Thread()
        {
            @Override
            public void run() 
            {   try {
                    s.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        try {
            s= new Socket(adress,port);
        } catch (UnknownHostException | ConnectException e) {
            JOptionPane.showMessageDialog(null, "Le serveur avec cet addresse et port n'est pas online.",null,0);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static ServerClient getInstance(String adress,int port,String role){
        if(c==null) {
            c= new ServerClient(adress,port, role);
            c.send(role);
        }
        return c;
    }
    public void send(Object o){
        ObjectOutputStream oos=null;
        try {
            oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(o);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Object read(){
        ObjectInputStream ois=null;
        Object o = null;
        try {
            ois = new ObjectInputStream(s.getInputStream());
            o=ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return o;
    }
}
