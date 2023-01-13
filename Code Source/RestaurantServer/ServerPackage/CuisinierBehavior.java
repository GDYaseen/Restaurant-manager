package ServerPackage;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import DAO.DAO;
import DAO.DAOImplDemande;
import DAO.DAOImplFacture;
import DAO.Demande;
import DAO.Facture;
import DAO.Repas;

public class CuisinierBehavior extends Behavior{
    private DAO<Demande> daoDem;
    private DAO<Facture> daoFact;

    public CuisinierBehavior(Socket s) {
        super(s);
        daoDem = new DAOImplDemande();
        daoFact = new DAOImplFacture();
    }

    @Override
    public void run() {
        new Thread(){
            @Override
            public void run(){
                while(true){
                    try{
                        Demande demande = (Demande)read();
                        daoDem.updateElement(demande);
                        int total=0;
                        Iterator<Map.Entry<Repas,Integer>> it = demande.getRepasMap().entrySet().iterator();
                        while (it.hasNext()) {
                            Map.Entry<Repas,Integer> rep = (Map.Entry<Repas,Integer>)it.next();
                            total+=(rep.getValue()*rep.getKey().getPrix());
                        }
                        daoFact.addElement(new Facture(0,demande,total,0));
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
                    ArrayList<Demande> r = (ArrayList<Demande>)daoDem.getElements();
                    send(r);
                    wait(2000);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
    }
    }
    
}
