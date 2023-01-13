package ServerPackage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
// import java.io.*;

import javax.swing.*;

// import org.springframework.context.ApplicationContext;
// import org.springframework.context.support.ClassPathXmlApplicationContext;

// import java.awt.*;
// import javax.swing.JOptionPane;

public class Server{
    public static void main(String[] args) throws Exception{
        ServerSocket s = new ServerSocket(0);
        s.getInetAddress();
        Socket sc;
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
        //Il doit etre addresse locale dans le réseau
        String message="Server online dans l'address: "+InetAddress.getLocalHost().getHostAddress()+"\n Et dans le port: "+s.getLocalPort();
        System.out.println(message);
        JFrame f = new JFrame();
        JLabel l = new JLabel(message);
        f.add(l);
        f.setSize(470,120);
        f.setResizable(false);
        f.setVisible(true);
        f.setDefaultCloseOperation(3);
        while(true){
            sc = s.accept();
            try {
                ObjectInputStream ois = new ObjectInputStream(sc.getInputStream());
                String role = (String)ois.readObject();
                switch (role) {
                    case "caissier":
                        Behavior b = new CaissierBehavior(sc);
                        Thread t=new Thread(b);
                        t.start();
                        break;
                    case "cuisinier":
                        Behavior b2 = new CuisinierBehavior(sc);
                        Thread t2=new Thread(b2);
                        t2.start();
                        break;
                    case "serveuse":
                        Behavior b3 = new ServeuseBehavior(sc);
                        Thread t3=new Thread(b3);
                        t3.start();
                        break;

                }
                // ois.close();
            }catch(Exception e){
                e.printStackTrace();
                sc.close();
            }
        }        
    }
}