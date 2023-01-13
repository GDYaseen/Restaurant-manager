package MAIN.Personnes;

import MAIN.Frames.CaissierFrame;

import java.util.ArrayList;

import DAO.Facture;
import MAIN.Personne;
import MAIN.ServerConnect.ServerClient;

public class Caissier extends Personne{

    public Caissier(/*int id, String nom,*/String address,int port) {
        super(/*id, nom,*/ "caissier", ServerClient.getInstance(address, port,"caissier"), new CaissierFrame());
        connect();
    }

    @Override
    public void connect() {
        while(true){
            ArrayList<Facture> r = (ArrayList<Facture>)getSc().read();
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
