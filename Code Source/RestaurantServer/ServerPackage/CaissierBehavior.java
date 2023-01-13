package ServerPackage;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import DAO.DAO;
import DAO.DAOImplFacture;
import DAO.Demande;
import DAO.Facture;

public class CaissierBehavior extends Behavior{

    private DAO<Facture> daoFact;
    public CaissierBehavior(Socket s) {
        super(s);
        daoFact = new DAOImplFacture();
    }

    @Override
    public void run() {
        new Thread(){
            @Override
            public void run(){
                while(true){
                    try{
                        Object fac = (Object)read();
                        if(fac==null)
                        {return;}else{
                            Integer facture = (Integer)fac;
                            daoFact.updateElement(new Facture(facture, new Demande(0,new HashMap<>()),0,0));
                        }
                        //besoin id facture seulement
                        synchronized(this){
                            wait(100);
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
            }
            }
        }.start();
        while(true){
            try{
                synchronized(this){
                    ArrayList<Facture> r = (ArrayList<Facture>)daoFact.getElements();
                    // System.out.println(r);
                    send(r);
                    wait(2000);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
    }    }
    
}
