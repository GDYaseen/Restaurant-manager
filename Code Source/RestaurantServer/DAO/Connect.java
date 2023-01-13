package DAO;
import java.sql.*;

import javax.swing.JOptionPane;

import org.springframework.stereotype.Component;

@Component
public class Connect {
    static Connect c = null;
    static Connection con;
    private String url,databasename,username,password;
    private Connect(String url, String databasename, String username, String password) {
        this.url = url;
        this.databasename = databasename;
        this.username = username;
        this.password = password;
    }
    public static Connect getInstance() {
        if (c == null) {
            throw new IllegalStateException("Connection has not been created yet.");
        }
        return c;
    }
    public Connection getConnection(){
        if(con==null){
            try {
                con = DriverManager.getConnection("jdbc:mysql://"+url+"/"+databasename,username,password);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erreur Base de données");
                e.printStackTrace();
            }
            return con;
        }
        return con;
    }
}
