package DAO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

// import DAO.Demande;
@Component
public class DAOImplDemande extends getBean implements DAO<Demande>{

    @Override
    public void addElement(Demande t) {
        try {
            PreparedStatement stm = conn.prepareStatement("INSERT INTO demandes (repasMap) VALUES (?)");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(t.getRepasMap());
                oos.flush();
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            stm.setBytes(1, baos.toByteArray());
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteElement(Demande t) {
        // TODO Auto-generated method stub        
    }
 
    @Override
    public List<Demande> getElements() {
        ArrayList<Demande> d = new ArrayList<Demande>(); 
        try {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM demandes where ready=0");
            ResultSet r = stm.executeQuery();
            while(r.next()){
                byte[] serializedObject = r.getBytes(2);
                ObjectInputStream in;
                try {
                    in = new ObjectInputStream(new ByteArrayInputStream(serializedObject));
                    HashMap<Repas, Integer> map = (HashMap<Repas, Integer>) in.readObject();
                    d.add(new Demande(r.getInt(1),map));
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return d;
    }

    @Override
    public void updateElement(Demande t) {
        // setDemande to ready and also create a facture with unpaid by default
        try {
            PreparedStatement stm = conn.prepareStatement("UPDATE Demandes Set ready=1 WHERE id=?");
            stm.setInt(1,t.getId());
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    
}
