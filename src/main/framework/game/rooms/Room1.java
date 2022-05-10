package main.framework.game.rooms;

import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import main.framework.animation.ChangementSprite;
import main.framework.game.ProprietesJoueur;
import main.framework.object2D.MouvementPersonnage;
import main.framework.controller.Controleur;
import main.framework.controller.Mouvement;
import main.framework.object2D.Zone;
import main.framework.object2D.ZoneSensible;
import main.framework.state.IEtat;
import main.framework.state.PileEtat;

import java.util.ArrayList;

import static main.framework.game.Jeu.root;

/*
 * Sample game level
 */


public class Room1 implements IEtat {

    /**---------------------------------**/
    private final Scene scene;
    private final GraphicsContext contexteGraphique;
    private PerspectiveCamera cameraPerspective;
    /**---------------------------------**/

    /**--------------------------- game objects ----------------------------**/
    private MouvementPersonnage joueur;
    private ArrayList<Zone> mursDeCote;
    private Zone zonePNJ;

    // hotspots
    private ZoneSensible ennemi;
    private ZoneSensible pnj;

    // joueur properties
    private Controleur joueurControleur;
    private Mouvement joueurMouvement;
    private ImageView vue;

    // sprite
    Image designZone;
    Image joueurSprite; // joueur sprite joueurSprite
    Image autreSprites;
    ChangementSprite transition;
    /**---------------------------------------------------------------------**/

    public Room1(Scene scene, GraphicsContext contexteGraphique) {
        this.scene = scene;
        this.contexteGraphique = contexteGraphique;
    }

    @Override
    public void init() {

        contexteGraphique.setFill(Color.BLACK);
        contexteGraphique.fillRect(0, 0, 2048, 2048);

        // set up camera
        cameraPerspective = new PerspectiveCamera(true);
        cameraPerspective.setTranslateZ(-1000);
        cameraPerspective.setNearClip(0.1);
        cameraPerspective.setFarClip(2000.0);
        cameraPerspective.setFieldOfView(25);
        scene.setCamera(cameraPerspective);

        // change canvas size
        contexteGraphique.getCanvas().setHeight(1032);
        contexteGraphique.getCanvas().setWidth(1032);

        // Initialize game objects
        // 2d characters and controller
        joueur = new MouvementPersonnage("joueur", /*w*/20, /*h*/20, /*x*/768, /*y*/900, 2); // always make x and y even
        joueurControleur = new Controleur(scene);
        joueurMouvement = new Mouvement(joueurControleur, joueur);

        // 2d game objects (objects with no collisions)
        mursDeCote = new ArrayList<>();

        // invisible walls instantiation
        for(int x = 512; x <= 1000; x+= 32) {
            mursDeCote.add(new Zone("wall", 32, 32, x, 512));
        }
        for(int x = 512; x <= 1000; x+= 32) {
            mursDeCote.add(new Zone("wall", 32, 32, x, 1000));
        }
        for(int y = 512; y <= 1000; y+= 32) {
            mursDeCote.add(new Zone("wall", 32, 32, 512, y));
        }
        for(int y = 512; y <= 1000; y+= 32) {
            mursDeCote.add(new Zone("wall", 32, 32, 1000, y));
        }

        zonePNJ = new Zone("pnj", 32, 32, 768, 768);
        joueur.ajouterCollision(mursDeCote);
        joueur.ajouterCollision(zonePNJ); // add a collision to pnj

        // hotspots and triggers
        ennemi = new ZoneSensible("door", 32, 32 , 768, 832); // combat initiator
        pnj = new ZoneSensible("pnj", 64, 64, 752, 752); // dialogue initiator
        ennemi.ajoutPersonnageDeclencheur(joueur);
        pnj.ajoutPersonnageDeclencheur(joueur);

        // set up images
        joueurSprite = new Image(getClass().getResourceAsStream("../resources/sprites.png"));
        autreSprites = new Image(getClass().getResourceAsStream("../resources/EntitySet.png"));
        designZone = new Image(getClass().getResourceAsStream("../resources/tileset.png"));

        vue = new ImageView(joueurSprite);
        vue.setViewport(new Rectangle2D(0, 32, 32, 32));
        root.getChildren().add(vue);

        // instantiates sprite transition
        transition = new ChangementSprite( vue,
               Duration.millis(300), 3, 3, /* offsetX */ 0, /* offsetY */ 0, 32, 32
        );
        transition.setCycleCount(Animation.INDEFINITE);
    }

    @Override
    public void enEntree() {
        // on enter draw this
        contexteGraphique.setFill(Color.BLACK);
        contexteGraphique.fillRect(0, 0, 2048, 2048);

        joueur = ProprietesJoueur.Joueur1.getMouvementPersonnage();
        joueurControleur = new Controleur(scene);
        joueurMouvement = new Mouvement(joueurControleur, joueur);

        transition.play();
    }

    @Override
    public void modifier(long currentTime) {
        transition.modifier(this.joueurMouvement, 0, 32, 64, 96);
        transition.modifierVue(joueurMouvement);
        joueurMouvement.update();
        cameraPerspective.setTranslateY(joueur.getY());
        cameraPerspective.setTranslateX(joueur.getX());

        if (ennemi != null) {
            if (ennemi.personnageSurZoneSensible()) {
                System.out.println("Player is standing on door!");
                PileEtat.afficherEtat("combat");
                ennemi = null;
                enSortie();
            }
        }

        if(joueurControleur.getEntrees().contains("ESCAPE")) {
            enSortie();
            PileEtat.afficherEtat("gameMenu");
        }
    }

    @Override
    public void afficher() {

        // draw grass floor
       for (int y = 512; y <= 1536; y += 32) {
            for (int x = 512; x <= 1536; x += 32) {
                contexteGraphique.drawImage(designZone, 0, 0, 32, 32, x, y, 32, 32);
            }
        }

        // draw walls
        for (Zone wall : mursDeCote) {
            contexteGraphique.drawImage(designZone, 32, 0, 32, 32, wall.getX(), wall.getY(), wall.getHeight(), wall.getWidth());
        }

        // combat initiator hotspot
        if(ennemi != null)
            contexteGraphique.drawImage(autreSprites, 128, 0, 32, 32, ennemi.getX(), ennemi.getY(), ennemi.getWidth(), ennemi.getHeight());

        contexteGraphique.drawImage(autreSprites, 32, 0, 32, 32, zonePNJ.getX(), zonePNJ.getY(), zonePNJ.getWidth(), zonePNJ.getHeight());

        contexteGraphique.setFill((pnj.personnageSurZoneSensible()? new Color(1, 0, 0, 0.3) : new Color(1, 1, 0, 0.3)));
        contexteGraphique.fillRect(joueur.getX(), joueur.getY(), joueur.getWidth(), joueur.getHeight());
    }

    @Override
    public void enSortie() {
        scene.setOnKeyPressed(null);
        scene.setOnKeyReleased(null);
        transition.stop();
        // set global joueur properties
        ProprietesJoueur.Joueur1.setMouvementPersonnage(joueur);
    }

    @Override
    public void enFermeture() {
        vue.setImage(null);
        transition = null;
    }


}
