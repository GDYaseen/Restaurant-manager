package DAO;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;


import DAO.Facture;
@Component
public class DAOImplFacture extends getBean implements DAO<Facture>{

    @Override
    public void addElement(Facture t) {
        try {
            PreparedStatement stm = conn.prepareStatement("INSERT INTO `facture` (demande_id,total) VALUES (?,?)");
            stm.setInt(1, t.getDemande().getId());
            stm.setInt(2, t.getTotal());
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteElement(Facture t) {
        // TODO Auto-generated method stub
        
    }


    @Override
    public List<Facture> getElements() {
        ArrayList<Facture> f = new ArrayList<Facture>(); 
        try {
            PreparedStatement stm = conn.prepareStatement("select * from facture,demandes where demande_id =demandes.id and paye=0");
            ResultSet r = stm.executeQuery();
            while(r.next()){
                byte[] serializedObject = r.getBytes(6);
                ObjectInputStream in;
                try {
                    in = new ObjectInputStream(new ByteArrayInputStream(serializedObject));
                    HashMap<Repas, Integer> map = (HashMap<Repas, Integer>) in.readObject();
                    Demande d = new Demande(r.getInt(2),map);
                    f.add(new Facture(r.getInt(1), d, r.getInt(3), r.getInt(4)));
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
        return f;
    }

    @Override
    public void updateElement(Facture t) {
        try {
            PreparedStatement stm = conn.prepareStatement("UPDATE facture SET paye=1 WHERE id=?");
            stm.setInt(1,t.getId());
            stm.executeUpdate();         
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    
}
