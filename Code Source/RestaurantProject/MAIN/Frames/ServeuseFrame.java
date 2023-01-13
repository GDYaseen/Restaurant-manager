package MAIN.Frames;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import DAO.Demande;
import DAO.Repas;
// import MAIN.Personnes.Serveuse;
import MAIN.ServerConnect.ServerClient;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ServeuseFrame extends MainFrame implements ActionListener, ListSelectionListener{
    public JPanel demandes,removeAndSend;
    public JButton send,clear,addItem;
    public JList<String> list;

    public ServeuseFrame() {
        super();
        
        main.add(new JLabel("Repas Saisis"));
        
        demandes= new JPanel();
        removeAndSend=new JPanel();
        send=new JButton("Envoyer");send.addActionListener(this);
        clear=new JButton("Supprimer Tous");clear.addActionListener(this);
        addItem=new JButton("Ajouter");addItem.addActionListener(this);
        list= new JList<String>();
        DefaultListModel<String> model = new DefaultListModel<String>();
        model.addElement("Chargement...");
        list.setModel(model);
        removeAndSend.add(send);
        removeAndSend.add(clear);
        demandes.setPreferredSize(new Dimension(390,300));
        removeAndSend.setPreferredSize(new Dimension(390,60));
        demandes.setBackground(Color.RED);
        main.add(demandes);
        removeAndSend.setBackground(Color.lightGray);
        main.add(removeAndSend);
        // demandes.add(new MenuItemPanel(new FlowLayout(), "Pizza",0));

        list.setPreferredSize(new Dimension(280,300));
        side.add(new JLabel("Menu"));
        side.add(list);
        side.add(addItem);
        addItem.setEnabled(false);
        list.addListSelectionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String b = e.getActionCommand();
        if(b.equals("Envoyer")){
            //make a demande list , send the list to server, then delete
            //don't forget to modify this loop when classes are ready
            HashMap<Repas,Integer> repasMap = new HashMap<Repas,Integer>();
            for(Component comp: demandes.getComponents()){
                //sendfunction ()
                MenuItemPanel mp = (MenuItemPanel)comp;
                repasMap.put(mp.rep,Integer.parseInt(
                    (
                        (JLabel)((JPanel)((JPanel)comp).getComponent(1)).getComponent(1)
                        ).getText()
                    ));
                    System.out.println(new Demande(0,repasMap));
                ServerClient.getInstance("",0,"").send(new Demande(0,repasMap));
                MainFrame.destroy(comp);
            }
        }else if(b.equals("Supprimer Tous")){
            for(Component comp: demandes.getComponents()){
                MainFrame.destroy(comp);
            }
        }else if(b.equals("Ajouter")){
            for(Component comp: demandes.getComponents()){
                if(((JLabel)((JPanel)comp).getComponent(0)).getText().equals(list.getSelectedValue())) return;
            }
            demandes.add(new MenuItemPanel(list.getSelectedValue(),REPS.get(list.getSelectedIndex())));
            this.revalidate();
            this.repaint();
        }    
    }

class MenuItemPanel extends JPanel{
    public Repas rep;
    public MenuItemPanel(String t,Repas i) {
        super(new GridLayout(1,2));
        rep=i;

        this.add(new JLabel(t));
        JPanel counter = new JPanel();
        JButton minus = new JButton("-");
        JButton plus = new JButton("+");
        JLabel c = new JLabel("1");
        counter.add(minus);
        counter.add(c);
        counter.add(plus);
        this.setPreferredSize(new Dimension(380,40));
        this.add(counter);
        class behaviorAdd implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                String b = e.getActionCommand();
                if(b.equals("+")){
                    c.setText(Integer.toString(Integer.parseInt(c.getText())+1));                
                } else if(b.equals("-")){
                    c.setText(Integer.toString(Integer.parseInt(c.getText())-1));                
                    if(c.getText().equals("0")){
                        destroyItem();
                    }
                }
            }
        }
        minus.addActionListener(new behaviorAdd());
        plus.addActionListener(new behaviorAdd());
    }
    void destroyItem(){
        MainFrame.destroy(this);
    }
    
}

ArrayList<Repas> REPS=new ArrayList<Repas>();
@Override
    public void updateList(ArrayList r){
        REPS.clear();
        addItem.setEnabled(false);
        DefaultListModel<String> listModel = new DefaultListModel<String>();
            for (int i = 0; i < r.size(); i++)
            {
                REPS.add((Repas) r.get(i));
                listModel.addElement(((Repas) r.get(i)).getNom()+" ("+((Repas) r.get(i)).getDuree()+" minutes)");
            }
            list.setModel(listModel);       
        this.repaint();
        this.revalidate();
    }
@Override
public void valueChanged(ListSelectionEvent e) {
    if(!list.isSelectionEmpty()){
        addItem.setEnabled(true);
    }
}
@Override
public void annoncerDemandeReady(Object id) {
    JOptionPane.showMessageDialog(null,"Les repas de la demande "+id+" sont prêts. Viens les récupérer!","ANNONCE",1);
    
}
}

