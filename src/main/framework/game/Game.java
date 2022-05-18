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
import main.framework.state.*;
import main.framework.game.rooms.Room1;

import java.awt.*;

public class Game extends Application {

    public static AnimationTimer gameLoop;
    public static Group root;
    private Scene scene;
    private Canvas canvas;
    private GraphicsContext gc;

    @Override
    public void init() throws Exception {

        // initialize javafx nodes
        root = new Group();
        scene = new Scene(root);
        canvas = new Canvas(612, 612);
        root.getChildren().addAll(canvas);
        gc = canvas.getGraphicsContext2D();
        scene.setFill(Color.BLACK);
        // add initial states
        StateStack.addState("mainmenu", new MainMenuState(scene, gc));
        StateStack.addState("room", new Room1(scene, gc));
        StateStack.addState("gameMenu", new GameMenuState(scene, gc));
        StateStack.addState("combat", new CombatState(scene, gc));
        StateStack.addState("potion", new ItemsState(scene, gc,"potion"));
        StateStack.addState("key", new ItemsState(scene,gc,"key"));


        StateStack.push("mainmenu");

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setScene(scene);
        primaryStage.setTitle("Maze war"); // title displayed on game window
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


        // main game loop
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // finds the last pushed state in the stack then puts it in the game loop
                StateStack.getCurrentState().update(now);
                StateStack.getCurrentState().draw();
            }
        };

        gameLoop.start();

    }

    public static void main(String[] args) {
        launch(args);
    }


}
