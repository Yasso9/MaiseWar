package main.framework.state;


import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class EtatCombat implements IEtat {

    /**----------------------------------**/

    private Scene scene;
    private PerspectiveCamera camera;
    private GraphicsContext contexteGraphique;

    /**----------------------------------**/

    private Character joueurPersonnage;

    public EtatCombat(Scene scene, GraphicsContext contexteGraphique) {
        this.scene = scene;
        this.contexteGraphique = contexteGraphique;
    }

    @Override
    public void init() {
        scene.getCamera().setTranslateY(256);
        scene.getCamera().setTranslateX(256);

    }

    @Override
    public void enEntree() {

    }

    @Override
    public void modifier(long currentTime) {
        scene.setOnKeyPressed( e -> {
            if (e.getCode() == KeyCode.ENTER) {
                System.out.println("Pressed Enter!");
                PileEtat.precedentEtat();
                enSortie();
            }
        });
    }

    @Override
    public void afficher() {
        contexteGraphique.setFill(Color.BLUE);
        contexteGraphique.fillRect(0, 0, 512, 512);
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
