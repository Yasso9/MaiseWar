package main.framework.state;


import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import main.framework.object2D.Zone;


public class EtatMenuJeu implements IEtat {

    private final Scene scene;
    private GraphicsContext contexteGraphique;

    // Marker for item focused
    private Zone divMarqueurChoix;
    private int choixMin = 1;
    private int choixActuel = 1;
    private int choixMax = 4;

    // in game menu box
    private Zone divMenu;

    public EtatMenuJeu(Scene scene, GraphicsContext contexteGraphique) {
        this.scene = scene;
        this.contexteGraphique = contexteGraphique;
    }

    @Override
    public void init() {

        scene.setOnKeyPressed(e -> {
            // return to game
            if (e.getCode() == KeyCode.ESCAPE) {
                PileEtat.precedentEtat();
                enSortie();
            }

            // choose menu option
            if (e.getCode() == KeyCode.DOWN) {
                if (choixActuel < choixMax) choixActuel++;
                else choixActuel = choixMin;
            } else if (e.getCode() == KeyCode.UP) {
                if (choixActuel > choixMin) choixActuel--;
                else choixActuel = choixMax;
            }

            // trigger
            if (e.getCode() == KeyCode.ENTER || e.getCode() == KeyCode.SPACE) {
                switch (choixActuel) {
                    case 1:
                        System.out.println("Save Game Selected!");
                        break;
                    case 2:
                        System.out.println("Load Game Selected!");
                        break;
                    case 3:
                        System.out.println("Exit to Main Menu Selected!");
                        PileEtat.popToMainMenu();
                        break;
                    case 4:
                        Platform.exit();
                        System.out.println("Close");
                        break;
                    default:
                        System.out.println("Invalid option");
                        break;
                }
            }

        });

        // instantiates menu 2D objects
        divMenu = new Zone("menu", 128, 160, scene.getCamera().getTranslateX() + 64, scene.getCamera().getTranslateY() - 192);
        divMarqueurChoix = new Zone("marker", 12, 12, divMenu.getX() + 16, divMenu.getY() + 32);
    }

    @Override
    public void enEntree() {

    }

    @Override
    public void modifier(long currentTime) {
        divMarqueurChoix.setY(divMenu.getY() + 32 * choixActuel);
    }

    @Override
    public void afficher() {

        contexteGraphique.setFill(Color.BLUE);
        contexteGraphique.fillRect(divMenu.getX(), divMenu.getY(), divMenu.getWidth(), divMenu.getHeight());

        contexteGraphique.setStroke(Color.WHITE);
        contexteGraphique.strokeRect(divMenu.getX() - 2, divMenu.getY() - 2, divMenu.getWidth() + 2, divMenu.getHeight() + 2);

        contexteGraphique.setFill(Color.WHITE);

        contexteGraphique.setFont(Font.font("Verdana", FontWeight.MEDIUM, 14));
        contexteGraphique.fillText("Save", divMenu.getX() + 32, divMenu.getY() + 32);
        contexteGraphique.fillText("Load", divMenu.getX() + 32, divMenu.getY() + 64);
        contexteGraphique.fillText("Main Menu", divMenu.getX() + 32, divMenu.getY() + 96);
        contexteGraphique.fillText("Quit Game", divMenu.getX() + 32, divMenu.getY() + 128);

        contexteGraphique.setFill(Color.WHITE);
        contexteGraphique.fillText(">", divMarqueurChoix.getX(), divMarqueurChoix.getY());
        // contexteGraphique.fillRect(divMarqueurChoix.getX(), divMarqueurChoix.getY(), divMarqueurChoix.getWidth(), divMarqueurChoix.getHeight());
    }

    @Override
    public void enSortie() {
        scene.setOnKeyPressed(null);
        PileEtat.getEtatActuel().enEntree();
    }

    @Override
    public void enFermeture() {

    }
}
