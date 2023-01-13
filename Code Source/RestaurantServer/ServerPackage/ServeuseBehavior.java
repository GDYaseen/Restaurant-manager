package ServerPackage;

// import java.io.IOException;
import java.net.Socket;
// import java.net.SocketException;

// import org.springframework.beans.factory.annotation.Autowired;

import DAO.DAO;
import DAO.DAOImplDemande;
import DAO.DAOImplRepas;
import DAO.Demande;
import DAO.Repas;

public class ServeuseBehavior extends Behavior{
    private DAO<Repas> daoRep;
    private DAO<Demande> daoDem;
    
    public ServeuseBehavior(Socket s) {
        super(s);
        daoRep = new DAOImplRepas();
        daoDem = new DAOImplDemande();
    }

    @Override
    public void run() {
        new Thread(){
            @Override
            public void run(){
                while(true){
                    try{
                        Demande demande = (Demande)read();
                        daoDem.addElement(demande);
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
                data[0]= "elements";
                data[1]= daoRep.getElements();
                send(data);
                    wait(2000);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
    }
    }

    public DAO<Repas> getDao() {
        return daoRep;
    }

    public void setDao(DAO<Repas> dao) {
        this.daoRep = dao;
    }
    
}
