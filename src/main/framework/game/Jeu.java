package main.framework.game;

import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.framework.state.EtatCombat;
import main.framework.state.EtatMenuJeu;
import main.framework.state.EtatMenu;
import main.framework.state.PileEtat;
import main.framework.game.rooms.Room1;

import java.util.concurrent.TimeUnit;

public class Jeu extends Application {

    public static AnimationTimer boucleJeu;
    public static Group root;
    private Scene scene;
    private Canvas canvas;
    private GraphicsContext contexteGraphique;

    // Image image = new Image(getClass().getResourceAsStream("EntitySet.png"));

    // SpriteAnimator sa;

    @Override
    public void init() throws Exception {
        // initialize javafx nodes
        root = new Group();
        scene = new Scene(root);
        canvas = new Canvas(512, 512);
        root.getChildren().addAll(canvas);
        contexteGraphique = canvas.getGraphicsContext2D();
        scene.setFill(Color.BLACK);
        // add initial states
        PileEtat.ajouterEtat("mainmenu", new EtatMenu(scene, contexteGraphique));
        PileEtat.ajouterEtat("room", new Room1(scene, contexteGraphique));
        PileEtat.ajouterEtat("gameMenu", new EtatMenuJeu(scene, contexteGraphique));
        PileEtat.ajouterEtat("combat", new EtatCombat(scene, contexteGraphique));

        PileEtat.afficherEtat("mainmenu");
        canvas = new Canvas(2048, 2048);
    }

    @Override
    public void start(Stage fenetreJeu) throws Exception {

        fenetreJeu.setScene(scene);
        fenetreJeu.setTitle("RPG Game"); // title displayed on game window
        fenetreJeu.setResizable(false);
        fenetreJeu.centerOnScreen();
        fenetreJeu.show();

        // main game loop
        boucleJeu = new AnimationTimer() {
            @Override
            public void handle(long maintenant) {
                // finds the last pushed state in the stack then puts it in the game loop
                PileEtat.getEtatActuel().modifier(maintenant);
                PileEtat.getEtatActuel().afficher(maintenant);
            }
        };

        boucleJeu.start();

    }

    public static void main(String[] args) {
        launch(args);
    }


}
