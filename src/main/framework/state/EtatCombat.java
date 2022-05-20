package main.framework.state;


import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import main.framework.object2D.ZoneSensible;

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
                System.out.println(PileEtat.precedentEtat().getEnnemi().getNom());
                enSortie();
            }
        });
    }

    @Override
    public void afficher(long currentTime) {
        contexteGraphique.setFill(Color.GREEN);
        contexteGraphique.fillRect(0, 0, 512, 512);
        contexteGraphique.setFill(Color.WHITE);
        contexteGraphique.setFont(Font.font("Verdana", FontWeight.MEDIUM, 14));
        contexteGraphique.fillText("New Game", 64, 384);
    }

    @Override
    public void enSortie() {
        scene.setOnKeyPressed(null);
        PileEtat.getEtatActuel().enEntree();
    }

    @Override
    public void enFermeture() {

    }

    @Override
    public ZoneSensible getEnnemi() {
        return null;
    }

}
