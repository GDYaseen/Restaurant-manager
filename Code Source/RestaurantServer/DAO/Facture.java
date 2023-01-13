package DAO;

import java.io.Serializable;

public class Facture implements Serializable{
    private int id,total,paye;
    private Demande demande;

    public Facture(int id, Demande demande, int total, int paye) {
        this.id = id;
        this.demande = demande;
        this.total = total;
        this.paye = paye;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Demande getDemande() {
        return demande;
    }

    public void setDemande(Demande demande) {
        this.demande = demande;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPaye() {
        return paye;
    }

    public void setPaye(int paye) {
        this.paye = paye;
    }
    
}
