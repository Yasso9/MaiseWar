package main.framework.state;

import javafx.application.Platform;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import main.framework.object2D.Zone;
import main.framework.object2D.ZoneSensible;


public class EtatMenu implements IEtat {

    /**------------------------------------**/

    private final Scene scene;
    private final GraphicsContext contexteGraphique;
    private PerspectiveCamera camera;
    /**------------------------------------**/

    private int choixActuel = 1;
    private int choixMin = 1;
    private int choixMax = 3;

    private Zone bouton;

    public EtatMenu(Scene scene, GraphicsContext contexteGraphique) {
        this.contexteGraphique = contexteGraphique;
        this.scene = scene;
    }

    @Override
    public void init() {
        //camera
        camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-1000);
        camera.setNearClip(0.1);
        camera.setFarClip(2000.0);
        camera.setFieldOfView(28);
        scene.setCamera(camera);

        scene.getCamera().setTranslateX(256);
        scene.getCamera().setTranslateY(256);

        scene.setOnKeyPressed(event -> {

            // choose menu option
            if (event.getCode() == KeyCode.DOWN) {
                if (choixActuel < choixMax) choixActuel++;
                else choixActuel = choixMin;
            } else if (event.getCode() == KeyCode.UP) {
                if (choixActuel > choixMin) choixActuel--;
                else choixActuel = choixMax;
            }

            // trigger
            if (event.getCode() == KeyCode.ENTER) {
                switch (choixActuel) {
                    case 1:
                        System.out.println("New Game Selected!");
                        PileEtat.afficherEtat("room");
                        break;
                    case 2:
                        System.out.println("Load Game Selected!");
                        break;
                    case 3:
                        System.out.println("Exit Selected!");
                        Platform.exit();
                        break;
                    default:
                        System.out.println("Invalid option");
                        break;
                }
            }
        });
    }

    @Override
    public void enEntree() {

        choixActuel = 1;

        //camera
        scene.setCamera(camera);

        scene.getCamera().setTranslateX(256);
        scene.getCamera().setTranslateY(256);

        scene.setOnKeyPressed(event -> {

            // choose menu option
            if (event.getCode() == KeyCode.DOWN) {
                if (choixActuel < choixMax) choixActuel++;
                else choixActuel = choixMin;
            } else if (event.getCode() == KeyCode.UP) {
                if (choixActuel > choixMin) choixActuel--;
                else choixActuel = choixMax;
            }

            // trigger
            if (event.getCode() == KeyCode.ENTER) {
                switch (choixActuel) {
                    case 1:
                        System.out.println("New Game Selected!");
                        PileEtat.afficherEtat("room");
                        break;
                    case 2:
                        System.out.println("Load Game Selected!");
                        break;
                    case 3:
                        System.out.println("Exit Selected!");
                        Platform.exit();
                        break;
                    default:
                        System.out.println("Invalid option");
                        break;
                }
            }
        });

    }

    @Override
    public void modifier(long currentTime) {
        // main menu
    }

    @Override
    public void afficher(long currentTime) {

        contexteGraphique.setFill(Color.WHITE);
        contexteGraphique.fillRect(0,0,512,512);

        contexteGraphique.setFont(Font.font("Verdana", FontWeight.BOLD, 18));

        contexteGraphique.setFill((choixActuel == 1? Color.BLUE : Color.BLACK));
        contexteGraphique.fillText("New Game", 64, 384);

        contexteGraphique.setFill((choixActuel == 2? Color.BLUE : Color.BLACK));
        contexteGraphique.fillText("Load Game", 64, 416);

        contexteGraphique.setFill((choixActuel == 3? Color.BLUE : Color.BLACK));
        contexteGraphique.fillText("Exit", 64, 448);
    }

    @Override
    public void enSortie() {
        scene.setOnKeyPressed(null);
        scene.setOnKeyReleased(null);
        // pushes room 1 to the stack
        PileEtat.afficherEtat("room");
        System.out.println("Exited Main Menu State. Now going to room");
    }

    @Override
    public void enFermeture() {

    }

    @Override
    public ZoneSensible getEnnemi() {
        return null;
    }
}
