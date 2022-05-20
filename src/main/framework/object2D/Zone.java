package main.framework.object2D;

import javafx.scene.shape.Rectangle;

public class Zone extends Rectangle {

    private String nom;

    // movement and placement properties
    /*private double largeur;
    private double hauteur;
    private double positionX;
    private double positionY;*/
    private double vitesse = 0;

    /** ====================== CONSTRUCTOR ============================ **/

    public Zone(String nom, double largeur, double hauteur, double positionX, double positionY) {
        this.nom = nom;
        setWidth(largeur);
        setHeight(hauteur);
        setX(positionX);
        setY(positionY);
    }

    /** ============================ GETTERS ================================ **/

    public String getNom() {
        return nom;
    }


    public double getVitesse() {
        return vitesse;
    }

    /** =========================== SETTERS ============================= **/

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setVitesse(double v) {
        vitesse = v;
    }


}
