package MAIN.Personnes;

import MAIN.Frames.CuisinierFrame;

import java.util.ArrayList;

import DAO.Demande;
import MAIN.Personne;
import MAIN.ServerConnect.ServerClient;

public class Cuisinier extends Personne{

    public Cuisinier(/*int id, String nom,*/String address,int port) {
        super(/*id, nom,*/ "cuisinier", ServerClient.getInstance(address, port,"cuisinier"), new CuisinierFrame());
        connect();
    }

    @Override
    public void connect() {
        while(true){
            ArrayList<Demande> r = (ArrayList<Demande>)getSc().read();
                getFrame().updateList(r);

                    synchronized(this)
                    {
                        try {
                            wait(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                
        }
    }
    
}
