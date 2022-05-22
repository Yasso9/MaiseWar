package main.framework.state;


import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.TouchPoint;
import javafx.scene.paint.Color;
import main.framework.entities.Entity;
import main.framework.game.PlayerProperties;
import main.framework.game.rooms.Room1;

/**
 * CombatState est la classe qui définie l'état d'un combat.
 * Elle dérive de la classe IState.
 * Cette classe est caractérisée par les informations suivantes :
 * </p>
 * </p>
 * @author PCEBL
 * @version 2.0
 * @see IState
 */
public class CombatState implements IState {

    /**
     * Scene en cours
     */
    private Scene scene;
    /**
     * Perspective de la caméra
     */
    private PerspectiveCamera camera;
    /**
     * Elements graphiques
     */
    private GraphicsContext graphicsContext;

    /**
     * Personnage du joueur
     */
    private Character playerCharacter;

    /**
     * <b>Constructeur de CombatState</b>
     *
     */
    public CombatState(Scene scene, GraphicsContext graphicsContext) {
        this.scene = scene;
        this.graphicsContext = graphicsContext;
    }

    @Override
    public void init() {
        scene.getCamera().setTranslateY(256);
        scene.getCamera().setTranslateX(256);

    }

    @Override
    public void onEnter() {

    }

    @Override
    public void update(long currentTime) {
        scene.setOnKeyPressed( e -> {
            if (e.getCode() == KeyCode.ENTER) {
                StateStack.push("mainmenu");
            }
        });
    }

    @Override
    public void draw() {
        graphicsContext.fillText("Game over !",256,256,512);
    }

    @Override
    public void onExit() {
        scene.setOnKeyPressed(null);
        StateStack.getCurrentState().onEnter();
    }

    @Override
    public void onClose() {

    }

}
