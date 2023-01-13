package MAIN.Frames;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import DAO.Demande;
import DAO.Repas;
import MAIN.ServerConnect.ServerClient;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class CuisinierFrame extends MainFrame implements ActionListener{
    public JPanel demandes,repasSection,currentDemande;
    public JButton send;
    public JTable repas;
    public JLabel demandesIdLabel;
    public Demande demandeToSend;
    public CuisinierFrame() {
        super();
        side.setLayout(new BoxLayout(side, BoxLayout.Y_AXIS));
        demandes= new JPanel();
        repasSection=new JPanel();

        Object[] columnNames = {"Id","Nom","Nombre"};
        Object[][] data = {};
        JScrollPane sp = new JScrollPane();
        sp.setPreferredSize(new Dimension(390,100));
        DefaultTableModel model = new DefaultTableModel(data,columnNames);
        repas = new JTable();
        repas.setModel(model);
        sp.setViewportView(repas);

        demandesIdLabel=new JLabel("Demande : ");
        demandesIdLabel.setPreferredSize(new Dimension(100,40));
        demandes.setPreferredSize(new Dimension(390,250));
        repasSection.setPreferredSize(new Dimension(390,110));
        demandes.setBackground(Color.RED);
        repasSection.setBackground(Color.orange);
        send=new JButton("Prêt!!");send.setEnabled(false);send.addActionListener(this);
        repasSection.add(sp);

        main.add(new JLabel("Demandes"));
        main.add(demandes);
        main.add(repasSection);
        side.add(demandesIdLabel);
        side.add(send);


        // connect();
        this.revalidate();
        this.repaint();
    }
    @Override
    public void actionPerformed(ActionEvent e) {//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        ServerClient.getInstance("",0,"").send(demandeToSend);
        send.setEnabled(false);
        MainFrame.destroy(currentDemande);
        this.revalidate();
        this.repaint();
        
    }
    class DemandeItemPanel extends JPanel{
        public Demande id;
        public DemandeItemPanel(Demande i) {
            super(new GridLayout(1,2));
            id=i;
    
            this.add(new JLabel(Integer.toString(i.getId())));
            JButton select = new JButton("Selectionner");
            this.setPreferredSize(new Dimension(380,40));
            this.add(select);
            class behaviorSelect implements ActionListener{
                @Override
                public void actionPerformed(ActionEvent e) {
                    demandeToSend=id;
                    send.setEnabled(true);
                    demandesIdLabel.setText("Demande : "+id.getId());
                    currentDemande=DemandeItemPanel.this;
                    //get all repas associated with this demandeToSend from server, then present them in jtable repas
                    
                    DefaultTableModel model = (DefaultTableModel)repas.getModel();
                    model.setRowCount(0);
                    Iterator<Map.Entry<Repas,Integer>> it = id.getRepasMap().entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry<Repas,Integer> rep = (Map.Entry<Repas,Integer>)it.next();
                        Object[] row = {((Repas)rep.getKey()).getId(),((Repas)rep.getKey()).getNom(),rep.getValue()};
                        model.addRow(row);
                    }
                    repas.setModel(model);
                }
            }
            select.addActionListener(new behaviorSelect());
        }
    }
    @Override
    public void updateList(ArrayList r) {
        demandes.removeAll();
        ArrayList<Demande> dems = (ArrayList<Demande>)r;
        for(Demande d : dems){
            demandes.add(new DemandeItemPanel(d));
        }
        this.repaint();
        this.revalidate();
    }
    @Override
    public void annoncerDemandeReady(Object object) {        
    }
}
