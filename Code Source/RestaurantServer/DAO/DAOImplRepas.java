package DAO;

import java.util.ArrayList;
import java.util.List;

// import org.springframework.stereotype.Component;

import java.sql.*;
// import DAO.Repas;
public class DAOImplRepas extends getBean implements DAO<Repas>{

    public DAOImplRepas() {
    }

    @Override
    public void addElement(Repas t) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteElement(Repas t) {
        // TODO Auto-generated method stub
        
    }


    @Override
    public List<Repas> getElements() {
        ArrayList<Repas> ar = new ArrayList<Repas>(); 
        try {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM repas");
            ResultSet r = stm.executeQuery();
            while(r.next())
            {
                ar.add(new Repas(r.getInt(1),r.getString(2),r.getInt(3),r.getInt(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ar;
    }

    @Override
    public void updateElement(Repas t) {
        // TODO Auto-generated method stub
        
    }

    
}
