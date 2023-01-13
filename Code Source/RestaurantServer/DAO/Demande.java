package DAO;

import java.io.Serializable;
import java.util.HashMap;

public class Demande implements Serializable{
    private int id;
    private HashMap<Repas,Integer> repasMap = new HashMap<Repas,Integer>();
    
    public Demande(int id, HashMap<Repas, Integer> repasMap) {
        this.id = id;
        this.repasMap = repasMap;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public HashMap<Repas, Integer> getRepasMap() {
        return repasMap;
    }
    public void setRepasMap(HashMap<Repas, Integer> repasMap) {
        this.repasMap = repasMap;
    }
    public String toString(){
        return "[ id: "+id+" ||| repasMap: "+repasMap+" ]";
    }
}
