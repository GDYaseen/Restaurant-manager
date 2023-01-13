package MAIN;
import MAIN.Frames.MainFrame;
import MAIN.ServerConnect.ServerClient;

public abstract class Personne {
    // private int id;
    // private String nom;
    private String role;
    private ServerClient sc;
    private MainFrame frame;

    public Personne(/*int id, String nom,*/ String role, ServerClient sc, MainFrame frame) {
        // this.id = id;
        // this.nom = nom;
        this.role = role;
        this.sc = sc;
        this.frame = frame;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public MainFrame getFrame() {
        return frame;
    }

    public void setFrame(MainFrame frame) {
        this.frame = frame;
    }

    public ServerClient getSc() {
        return sc;
    }

    public void setSc(ServerClient sc) {
        this.sc = sc;
    }

    // public int getId() {
    //     return id;
    // }

    // public void setId(int id) {
    //     this.id = id;
    // }

    // public String getNom() {
    //     return nom;
    // }

    // public void setNom(String nom) {
    //     this.nom = nom;
    // }

    abstract public void connect();
}
