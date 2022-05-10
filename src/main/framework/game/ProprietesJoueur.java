package main.framework.game;


import javafx.scene.PerspectiveCamera;
import main.framework.controller.Manette;
import main.framework.controller.Mouvement;
import main.framework.entities.Personnage;
import main.framework.object2D.PositionPersonnage;

public enum ProprietesJoueur {

    Player1;

    private PerspectiveCamera camera = null;
    private Personnage personnage = null;
    private PositionPersonnage positionPersonnage = null;
    private Manette manette = null;
    private Mouvement mouvement = null;

    public PerspectiveCamera getCamera() {
        return camera;
    }

    public Personnage getPersonnage() {
        return personnage;
    }

    public PositionPersonnage getZonePersonnage() {
        return positionPersonnage;
    }

    public Manette getController() {
        return manette;
    }

    public Mouvement getMover() {
        return mouvement;
    }

    public void setCamera(PerspectiveCamera camera) {
        this.camera = camera;
    }

    public void setCharacter(Personnage personnage) {
        this.personnage = personnage;
    }

    public void setCharacter2D(PositionPersonnage positionPersonnage) {
        this.positionPersonnage = positionPersonnage;
    }

    public void setController(Manette manette) {
        this.manette = manette;
    }

    public void setMover(Mouvement mouvement) {
        this.mouvement = mouvement;
    }

}
