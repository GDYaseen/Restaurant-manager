package MAIN.Frames;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import DAO.Facture;
import DAO.Repas;
import MAIN.ServerConnect.ServerClient;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class CaissierFrame extends MainFrame implements ActionListener{
    public JPanel facture,repasSection,currentFacture;
    public JButton send,calculer;
    public JTable repas;
    public JLabel prixTotal,monnaie,reste;
    public int total=100;
    public Facture factureToSend;
    public String totalString ="";
    public CaissierFrame() {
        super();
        facture= new JPanel();
        repasSection=new JPanel();

        Object[] columnNames = {"Id","Nom","Prix","Nombre","Total"};
        Object[][] data = {};
        JScrollPane sp = new JScrollPane();
        sp.setPreferredSize(new Dimension(390,100));
        DefaultTableModel model = new DefaultTableModel(data,columnNames);
        repas = new JTable();
        repas.setModel(model);
        sp.setViewportView(repas);

        prixTotal=new JLabel("Totale : ");
        prixTotal.setPreferredSize(new Dimension(100,40));
        monnaie=new JLabel("Monnaie : ");
        monnaie.setPreferredSize(new Dimension(100,40));
        reste=new JLabel("Reste : ");
        reste.setPreferredSize(new Dimension(100,40));

        JPanel top=new JPanel(new GridLayout(3,1));
        top.setPreferredSize(new Dimension(300,90));
        top.add(prixTotal);
        top.add(monnaie);
        top.add(reste);
        
        JPanel center=new JPanel(new FlowLayout());
        center.setPreferredSize(new Dimension(200,250));
        for(int i=1;i<10;i++){
            JButton b=new JButton(Integer.toString(i));
            b.setPreferredSize(new Dimension(50,50));
            b.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    totalString+=e.getActionCommand();
                    monnaie.setText("Monnaie : "+totalString+" DH");
                }
            });
            center.add(b);
        }
        // total=Integer.parseInt(e.getActionCommand());
        JButton b=new JButton(Integer.toString(0));
        b.setPreferredSize(new Dimension(50,50));
        b.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                totalString+=e.getActionCommand();
                monnaie.setText("Monnaie : "+totalString+" DH");
            }
        });
        center.add(b);
        JButton clear=new JButton("Clear");
        clear.setPreferredSize(new Dimension(70,50));
        clear.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                totalString="";
                monnaie.setText("Monnaie : ");
            }
        });
        center.add(clear);
       
        calculer = new JButton("Calculer");
        send = new JButton("Envoyer");
        send.setEnabled(false);
        calculer.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                reste.setText("Reste : "+(Integer.parseInt(totalString) - total)+" DH");
                totalString="";
            }
        });
        send.addActionListener(this);

        JPanel bottom=new JPanel(new FlowLayout());
        // bottom.add(new JLabel("test"));
        bottom.setPreferredSize(new Dimension(300,50));
        bottom.setBackground(Color.lightGray);
        bottom.add(calculer);
        bottom.add(send);
        
        facture.setPreferredSize(new Dimension(390,250));
        repasSection.setPreferredSize(new Dimension(390,110));
        facture.setBackground(Color.RED);
        repasSection.setBackground(Color.orange);

        repasSection.add(sp);

        // facture.add(new FactureItemPanel(9932));
        // facture.add(new FactureItemPanel(1576));
        // facture.add(new FactureItemPanel(2043));
        
        main.add(new JLabel("facture"));
        main.add(facture);
        main.add(repasSection);
        side.add(top);
        side.add(center);
        side.add(bottom);


        this.revalidate();
        this.repaint();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        totalString="";
        ServerClient.getInstance("",0,"").send(factureToSend.getId());
        send.setEnabled(false);
        MainFrame.destroy(currentFacture);
        this.revalidate();
        this.repaint(); 
    }
    class FactureItemPanel extends JPanel{
        public Facture id;
        public FactureItemPanel(Facture i) {
            super(new GridLayout(1,2));
            id=i;
    
            this.add(new JLabel(Integer.toString(i.getId())));
            JButton select = new JButton("Selectionner");
            this.setPreferredSize(new Dimension(380,40));
            this.add(select);
            class behaviorSelect implements ActionListener{ 
                @Override
                public void actionPerformed(ActionEvent e) {
                    factureToSend=id;
                    send.setEnabled(true);
                    total=id.getTotal();
                    prixTotal.setText("Totale : "+ Integer.toString(total)+" DH");
                    currentFacture=FactureItemPanel.this;
                    DefaultTableModel model = (DefaultTableModel)repas.getModel();
                    model.setRowCount(0);
                    Iterator<Map.Entry<Repas,Integer>> it = id.getDemande().getRepasMap().entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry<Repas,Integer> rep = (Map.Entry<Repas,Integer>)it.next();
                        Object[] row = {((Repas)rep.getKey()).getId(),((Repas)rep.getKey()).getNom(),((Repas)rep.getKey()).getPrix(),rep.getValue(),rep.getValue()*((Repas)rep.getKey()).getPrix()};
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
        facture.removeAll();
        ArrayList<Facture> facts = (ArrayList<Facture>)r;
        for(Facture d : facts){
            facture.add(new FactureItemPanel(d));
        }
        this.repaint();
        this.revalidate();        
    }
    @Override
    public void annoncerDemandeReady(Object object) {        
    }
}
