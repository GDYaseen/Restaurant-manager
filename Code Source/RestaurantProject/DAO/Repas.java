package DAO;

import java.io.Serializable;

// import org.springframework.stereotype.Component;

public class Repas implements Serializable{
    private int id,prix,duree;
    private String nom;
    public Repas(int id,String nom, int prix,int duree) {
        this.id = id;
        this.prix = prix;
        this.duree = duree;
        this.nom = nom;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getPrix() {
        return prix;
    }
    public void setPrix(int prix) {
        this.prix = prix;
    }
    public int getDuree() {
        return duree;
    }
    public void setDuree(int duree) {
        this.duree = duree;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String toString(){
        return "[ id: "+id+" ||| prix: "+prix+ " ||| duree: "+duree+" "+ " ||| nom: "+nom+" ]";
    }}
