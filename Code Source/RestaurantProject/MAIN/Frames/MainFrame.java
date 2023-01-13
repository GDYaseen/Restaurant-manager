package MAIN.Frames;
import javax.swing.*;

import DAO.Repas;

import java.awt.*;
import java.util.ArrayList;

public abstract class MainFrame extends JFrame{
    public JPanel main,side;
    public MainFrame()
    {
        main=new JPanel();
        main.setBackground(Color.WHITE);
        side=new JPanel();
        this.setSize(700,400);
        main.setPreferredSize(new Dimension(400,400));
        side.setPreferredSize(new Dimension(300,400));
        this.setLayout(new FlowLayout());
        this.add(main);this.add(side);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
    }

    //Trouver un composant
    public static Component getSibling(Component component, int index) {
        Container parent = component.getParent();
        if (parent == null) {
          return null;
        }
        Component[] siblings = parent.getComponents();
        for (int i = 0; i < siblings.length; i++) {
          if (i == index) {
            return siblings[i];
          }
        }
        return null;
      }
      //Supprimer un composant
      public static void destroy(Component component) {
        Container parent = component.getParent();
        if (parent == null) {
          return;
        }
        parent.remove(component);
        component.setVisible(false);
        component = null;
        parent.revalidate();
        parent.repaint();
        if (parent instanceof JFrame) {
          ((JFrame) parent).pack();
        }
      }

      abstract public void updateList(ArrayList r);

      abstract public void annoncerDemandeReady(Object object);
}
