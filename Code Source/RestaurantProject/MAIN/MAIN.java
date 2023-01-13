package MAIN;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import MAIN.Personnes.Caissier;
import MAIN.Personnes.Cuisinier;
import MAIN.Personnes.Serveuse;

public class MAIN {
    public static void main(String[] args) {
        boolean fieldsFull = false;
        while(!fieldsFull){
        JPanel panel = new JPanel();
        // JLabel idLabel = new JLabel("Id: ");
        // JTextField id = new JTextField();
        // id.setPreferredSize(new Dimension(100,30));
        // JLabel nomLabel = new JLabel("Nom: ");
        // JTextField nom = new JTextField();
        // nom.setPreferredSize(new Dimension(100,30));

        JLabel addressLabel = new JLabel("Address: ");
        JTextField address = new JTextField();
        address.setPreferredSize(new Dimension(100,30));
        JLabel portLabel = new JLabel("Port: ");
        JTextField port = new JTextField();
        port.setPreferredSize(new Dimension(50,30));
        // panel.add(idLabel);panel.add(id);
        // panel.add(nomLabel);panel.add(nom);
        panel.add(addressLabel);panel.add(address);
        panel.add(portLabel);panel.add(port);
        panel.setLayout(new GridLayout(4,2));
        String[] options = {"Serveuse", "Cuisinier","Caissier"};
        int result = JOptionPane.showOptionDialog(null, panel, "",JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                 null, options, options[0]);

        if (result == 0) {
            if(/*id.getText().equals("") ||nom.getText().equals("") ||*/address.getText().equals("") || port.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Veuillez remplir tout les champs.","Erreur",JOptionPane.DEFAULT_OPTION);
            }else{
                new Serveuse(/*Integer.parseInt(id.getText()),nom.getText(),*/ address.getText(),Integer.parseInt(port.getText()));
                fieldsFull=true;
            }
        } else if (result == 1) {
            if(/*id.getText().equals("") ||nom.getText().equals("") ||*/address.getText().equals("") || port.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Veuillez remplir tout les champs.","Erreur",JOptionPane.DEFAULT_OPTION);
            }else{
                new Cuisinier(/*Integer.parseInt(id.getText()),nom.getText(),*/ address.getText(),Integer.parseInt(port.getText()));
                fieldsFull=true;
            }
        }else if (result == 2) {
            if(/*id.getText().equals("") ||nom.getText().equals("") ||*/address.getText().equals("") || port.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Veuillez remplir tout les champs.","Erreur",JOptionPane.DEFAULT_OPTION);
            }else{
                new Caissier(/*Integer.parseInt(id.getText()),nom.getText(),*/ address.getText(),Integer.parseInt(port.getText()));
                fieldsFull=true;
            }
        }else {
            System.exit(1);
        }}
    }
}
