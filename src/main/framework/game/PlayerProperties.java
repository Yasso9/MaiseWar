package main.framework.game;


import javafx.scene.Camera;
import javafx.scene.PerspectiveCamera;
import main.framework.controller.Controller;
import main.framework.controller.Mover;
import main.framework.entities.Character;
import main.framework.object2D.Character2D;

/**
 * Classe contenant les propriétés liées au personnage
 */
public enum PlayerProperties {

    Player1;

    /**
     * Perspective de la caméra
     */
    private PerspectiveCamera camera = null;
    private Character character = null;
    private Character2D character2D = null;
    private Controller controller = null;
    private Mover mover = null;

    /**
     * @return La perspective de la caméra
     */
    public PerspectiveCamera getCamera() {
        return camera;
    }

    /**
     * @return L'objet du personnage
     */
    public Character getCharacter() {
        return character;
    }

    /**
     * @return Character2D du personnage
     */
    public Character2D getCharacter2D() {
        return character2D;
    }

    public Controller getController() {
        return controller;
    }

    public Mover getMover() {
        return mover;
    }

    public void setCamera(PerspectiveCamera camera) {
        this.camera = camera;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    /**
     * Redéfinie le charachter2D du personnage
     * @param character2D Nouveau character2D
     */
    public void setCharacter2D(Character2D character2D) {
        this.character2D = character2D;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setMover(Mover mover) {
        this.mover = mover;
    }

}
