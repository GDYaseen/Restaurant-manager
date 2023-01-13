package MAIN.Personnes;

import java.util.ArrayList;

import DAO.Repas;
import MAIN.Frames.ServeuseFrame;
import MAIN.Personne;
import MAIN.ServerConnect.ServerClient;

public class Serveuse extends Personne{

    public Serveuse(/*int id, String nom,*/String address,int port) {
        super(/*id, nom,*/ "serveuse", ServerClient.getInstance(address, port,"serveuse"), new ServeuseFrame());
        connect();
    }
    @Override
    public void connect() {
        while(true)
        {
            Object[] data = (Object[])getSc().read();
            if(((String)data[0]).equals("elements")){

                ArrayList<Repas> r = (ArrayList<Repas>)data[1];
                getFrame().updateList(r);
                try {
                    synchronized(this)
                    {
                        wait(4000);
                    }
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            }else{
                getFrame().annoncerDemandeReady((int)data[1]);
            }
        }
    }
    
}
