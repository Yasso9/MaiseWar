package main.framework.state;


import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.TouchPoint;
import javafx.scene.paint.Color;
import main.framework.game.PlayerProperties;
import main.framework.game.rooms.Room1;

public class CombatState implements IState {

    private Scene scene;
    private PerspectiveCamera camera;
    private GraphicsContext graphicsContext;

    private Character playerCharacter;

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
