package main.framework.game;


import javafx.scene.PerspectiveCamera;
import main.framework.controller.Controleur;
import main.framework.controller.Mouvement;
import main.framework.entities.Personnage;
import main.framework.object2D.MouvementPersonnage;

public enum ProprietesJoueur {

    Joueur1;

    private PerspectiveCamera camera = null;
    private Personnage personnage = null;
    private MouvementPersonnage mouvementPersonnage = null;
    private Controleur controleur = null;
    private Mouvement mouvement = null;

    public PerspectiveCamera getCamera() {
        return camera;
    }

    public Personnage getPersonnage() {
        return personnage;
    }

    public MouvementPersonnage getMouvementPersonnage() {
        return mouvementPersonnage;
    }

    public Controleur getControleur() {
        return controleur;
    }

    public Mouvement getMouvement() {
        return mouvement;
    }

    public void setCamera(PerspectiveCamera camera) {
        this.camera = camera;
    }

    public void setPersonnage(Personnage personnage) {
        this.personnage = personnage;
    }

    public void setMouvementPersonnage(MouvementPersonnage mouvementPersonnage) {
        this.mouvementPersonnage = mouvementPersonnage;
    }

    public void setControleur(Controleur controleur) {
        this.controleur = controleur;
    }

    public void setMouvement(Mouvement mouvement) {
        this.mouvement = mouvement;
    }

}
