package main.framework.object2D;

import main.framework.entities.Entity;

import java.util.ArrayList;

/**
 * Character2D est la classe qui définie le personnage en deux dimensions.
 * Elle dérive de la classe GameObject2D.
 * Cette classe est caractérisée par les informations suivantes :
 * <ul>
 * <li>isFacingRight: Booléen qui indique si le personnage est dirigé vers la droite</li>
 * <li>isFacingDown: Booléen qui indique si le personnage est dirigé vers le bas</li>
 * <li>isFacingUp: Booléen qui indique si le personnage est dirigé vers le haut</li>
 * <li>isFacingLeft: Booléen qui indique si le personnage est dirigé vers la gauche</li>
 * </ul>
 * </p>
 * </p>
 * @author PCEBL
 * @version 2.0
 * @see GameObject2D
 */
public class Character2D extends GameObject2D {

    /**
     * Booléen qui indique si le personnage est dirigé vers la droite
     */
    private boolean isFacingRight = false;
    /**
     * Booléen qui indique si le personnage est dirigé vers le bas
     */
    private boolean isFacingDown = true;
    /**
     * Booléen qui indique si le personnage est dirigé vers le haut
     */
    private boolean isFacingUp = false;
    /**
     * Booléen qui indique si le personnage est dirigé vers la gauche
     */
    private boolean isFacingLeft = false;

    /**
     * Liste des collisions
     */
    private ArrayList<GameObject2D> collisions = new ArrayList<>();

    /**
     * <b>Constructeur de Character2D</b>
     *
     * @param name
     *      Nom du personnage
     * @param width
     *      Largeur de l'image du personnage
     * @param height
     *      Hauteur de l'image du personnage
     * @param x
     *      Position du charactère
     * @param y
     *      Position du charactère
     */
    public Character2D(String name, double width, double height, double x, double y) {
        super(name, width, height, x, y);
    }
    /**
     * <b>Constructeur de Character2D</b>
     *
     * @param name
     *      Nom du personnage
     * @param width
     *      Largeur de l'image du personnage
     * @param height
     *      Hauteur de l'image du personnage
     * @param x
     *      Position du charactère
     * @param y
     *      Position du charactère
     * @param velocity
     *      Vélocité de l'animation
     */
    public Character2D(String name, double width, double height, double x, double y, double velocity) {
        super(name, width, height, x, y);
        this.setVelocity(velocity);
    }

    /**
     * @return True si le personnage est orienté vers la droite, False sinon
     */
    public boolean isFacingRight() {
        boolean facing = false;
        if (isFacingRight && !isFacingDown && !isFacingUp && !isFacingLeft) facing = true;
        return facing;
    }

    /**
     * @return True si le personnage est orienté vers la gauche, False sinon
     */
    public boolean isFacingLeft() {
        boolean facing = false;
        if (!isFacingRight && !isFacingDown && !isFacingUp && isFacingLeft) facing = true;
        return facing;
    }

    /**
     * @return True si le personnage est orienté vers le bas, False sinon
     */
    public boolean isFacingDown() {
        boolean facing = false;
        if (!isFacingRight && isFacingDown && !isFacingUp && !isFacingLeft) facing = true;
        return facing;
    }

    /**
     * @return True si le personnage est orienté vers le haut, False sinon
     */
    public boolean isFacingUp() {
        boolean facing = false;
        if (!isFacingRight && !isFacingDown && isFacingUp && !isFacingLeft) facing = true;
        return facing;
    }

    /**
     * Oriente le personnage vers la droite
     */
    public void setFaceRight() {
        isFacingRight = true;
        isFacingDown = false;
        isFacingUp = false;
        isFacingLeft = false;
    }

    /**
     * Oriente le personnage vers la gauche
     */
    public void setFaceLeft() {
        isFacingRight = false;
        isFacingDown = false;
        isFacingUp = false;
        isFacingLeft = true;
    }

    /**
     * Oriente le personnage vers le bas
     */
    public void setFaceDown() {
        isFacingRight = false;
        isFacingDown = true;
        isFacingUp = false;
        isFacingLeft = false;
    }

    /**
     * Oriente le personnage vers le haut
     */
    public void setFaceUp() {
        isFacingRight = false;
        isFacingDown = false;
        isFacingUp = true;
        isFacingLeft = false;
    }

    /**
     * Définit une collision sur un object
     * @param gameObject2D
     *      L'objet sur lequel ajouter une collision
     */
    public void addCollision(GameObject2D gameObject2D) {
        collisions.add(gameObject2D);
    }

    /**
     * Définit une collision sur un object
     * @param gameObject2D
     *      L'objet sur lequel ajouter une collision
     */
    public void addCollision(ArrayList<GameObject2D> gameObject2D) {
        collisions.addAll(gameObject2D);
    }

    /**
     * Supprime une collision sur un object
     * @param gameObject2D
     *      L'objet sur lequel retirer la collision
     */
    public void deleteCollision(GameObject2D gameObject2D){
        collisions.remove(gameObject2D);
    }

    /**
     * Supprime une collision sur un object
     * @param gameObject2D
     *      L'objet sur lequel retirer la collision
     */
    public void deleteCollision(ArrayList<GameObject2D> gameObject2D){
        collisions.removeAll(gameObject2D);
    }

    /**
     * @return Si il y'a une collision vers le haut ou vers le bas, retourne de quel coté il y'a collision
     */
    public String getVerticalCollision() {
        String collided = "NONE";

        for(GameObject2D c : collisions) {
            if(this.isFacingUp) {
                if (c.getY() + c.getHeight() >= this.getY() && this.getY() + this.getHeight() > c.getY() + c.getHeight()
                        && ((this.getX() >= c.getX() && this.getX() + this.getWidth() <= c.getX() + c.getWidth()) ||
                        (this.getX() > c.getX() && this.getX() < c.getX() + c.getWidth()) ||
                        (this.getX() > c.getX() && this.getX() + this.getWidth() < c.getX() + c.getWidth()) ||
                        (this.getX() + this.getWidth() > c.getX() && this.getX() + this.getWidth() < c.getX() + c.getWidth()) )) {

                    collided = "UP";
                    System.out.println(this.getName() + ": Collided Upward");
                }
            }

            else if(this.isFacingDown) {
                if ( this.getY() + this.getHeight() >= c.getY() && this.getY() < c.getY()
                        && ((this.getX() >= c.getX() && this.getX() + this.getWidth() <= c.getX() + c.getWidth()) ||
                        (this.getX() > c.getX() && this.getX() < c.getX() + c.getWidth()) ||
                        (this.getX() > c.getX() && this.getX() + this.getWidth() < c.getX() + c.getWidth()) ||
                        (this.getX() + this.getWidth() > c.getX()&& this.getX() + this.getWidth() < c.getX() + c.getWidth()) )) {

                    collided = "DOWN";
                    System.out.println(this.getName() + ": Collided downward");
                }
            }
        }
        return collided;
    }

    /**
     * @return Si il y'a une collision vers la gauche ou la droite, retourne de quel coté il y'a collision
     */
    public String getHorizontalCollision() {
        String collided = "NONE";

        for (GameObject2D c : collisions) {
            if (this.isFacingRight) {
                if (this.getX() + this.getWidth() >= c.getX() && c.getX() > this.getX()
                        && ((this.getY() >= c.getY() && this.getY() + this.getHeight() < c.getY() + c.getHeight()) ||
                        (this.getY() > c.getY() && this.getY() < c.getY() + c.getHeight()) ||
                        (this.getY() > c.getY() && this.getY() + this.getHeight() < c.getY() + c.getHeight()) ||
                        (this.getY() + this.getHeight() > c.getY() && this.getY() + this.getHeight() < c.getY() + c.getHeight()))) {

                    collided = "RIGHT";
                    System.out.println(this.getName() + ": Collided rightward");
                }
            } else if (this.isFacingLeft) {
                if (this.getX() <= c.getX() + c.getWidth() && c.getX() + c.getWidth() < this.getX() + this.getWidth()
                        && ((this.getY() >= c.getY() && this.getY() + this.getHeight() <= c.getY() + c.getHeight()) ||
                        (this.getY() > c.getY() && this.getY() < c.getY() + c.getHeight()) ||
                        (this.getY() > c.getY() && this.getY() + this.getHeight() < c.getY() + c.getHeight()) ||
                        (this.getY() + this.getHeight() > c.getY() && this.getY() + this.getHeight() < c.getY() + c.getHeight()))) {

                    collided = "LEFT";
                    System.out.println(this.getName() + ": Collided leftward");
                }
            }
        }
        return collided;
    }

}
