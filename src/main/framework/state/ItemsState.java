package main.framework.state;


import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.TouchPoint;
import javafx.scene.paint.Color;
import main.framework.game.PlayerProperties;
import main.framework.game.rooms.Room1;

public class ItemsState implements IState {


    private Scene scene;
    private PerspectiveCamera camera;
    private GraphicsContext graphicsContext;
    private String item;
    private int score;
    private String time;

    private Character playerCharacter;

    public ItemsState(Scene scene, GraphicsContext graphicsContext,String item) {
        this.scene = scene;
        this.graphicsContext = graphicsContext;
        this.item = item;
    }

    public ItemsState(Scene scene, GraphicsContext graphicsContext,String item,int score, String time) {
        this.scene = scene;
        this.graphicsContext = graphicsContext;
        this.item = item;
        this.score = score;
        this.time = time;
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
                System.out.println("Pressed Enter!");
                StateStack.pop();
                onExit();
            }
        });
    }

    @Override
    public void draw() {
        graphicsContext.setFill(Color.WHITE);

        if (item.equals("potion"))
            graphicsContext.fillText("You received a shield !",128,256,512);

        if (item.equals("key"))
            graphicsContext.fillText("You received a key !",128,256,512);

        if (item.equals("weapon"))
            graphicsContext.fillText("You received a weapon !",128,256,512);

        if (item.equals("endGame"))
            graphicsContext.fillText("Game over !",128,256,512);
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
