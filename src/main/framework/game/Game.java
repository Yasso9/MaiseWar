package main.framework.game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import main.framework.entities.Entity;
import main.framework.state.*;
import main.framework.game.rooms.Room1;

import java.awt.*;

/**
 * Game est la classe qui définit la boucle du jeu.
 * Elle dérive de la classe Application.
 * Cette classe est caractérisée par les informations suivantes :
 * </p>
 * </p>
 * @author PCEBL
 * @version 2.0
 */
public class Game extends Application {

    public static AnimationTimer gameLoop;
    public static Group root;
    private Scene scene;
    private Canvas canvas;
    private GraphicsContext gc;

    /**
     * @throws Exception
     */
    @Override
    public void init() throws Exception {

        root = new Group();
        scene = new Scene(root);
        canvas = new Canvas(612, 612);
        root.getChildren().addAll(canvas);
        gc = canvas.getGraphicsContext2D();
        scene.setFill(Color.BLACK);

        StateStack.addState("mainmenu", new MainMenuState(scene, gc));
        StateStack.addState("room", new Room1(scene, gc));
        StateStack.addState("gameMenu", new GameMenuState(scene, gc));
        StateStack.addState("combat", new CombatState(scene, gc));
        StateStack.addState("potion", new ItemsState(scene, gc,"potion"));
        StateStack.addState("key", new ItemsState(scene,gc,"key"));
        StateStack.addState("weapon", new ItemsState(scene,gc,"weapon"));
        StateStack.addState("endGame", new ItemsState(scene,gc,"endGame"));

        StateStack.push("mainmenu");
    }

    /**
     * Démarre le jeu au premier niveau
     * @param primaryStage
     *      Le premier niveau
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setScene(scene);
        primaryStage.setTitle("Maze War");
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.show();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                Platform.exit();
                System.exit(0);
            }
        });

        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                StateStack.getCurrentState().update(now);
                StateStack.getCurrentState().draw();
            }
        };

        gameLoop.start();

    }

    /**
     * @param args Entrées de l'utilisateur
     */
    public static void main(String[] args) {
        launch(args);
    }

}
