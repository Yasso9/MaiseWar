package main.framework.object2D;

import java.util.ArrayList;

public class MouvementPersonnage extends Zone {

    private boolean versLaDroite = false;
    private boolean versLeBas = true;
    private boolean versLeHaut = false;
    private boolean versLaGauche = false;

    private ArrayList<Zone> collisions = new ArrayList<>();

    /**
     * ====================== CONSTRUCTOR ============================
     *
     * @param nom
     * @param largeur
     * @param hauteur
     * @param positionX
     * @param positionY
     **/

    public MouvementPersonnage(String nom, double largeur, double hauteur, double positionX, double positionY) {
        super(nom, largeur, hauteur, positionX, positionY);
    }

    public MouvementPersonnage(String nom, double largeur, double hauteur, double positionX, double positionY, double vitesse) {
        super(nom, largeur, hauteur, positionX, positionY);
        this.setVitesse(vitesse);
    }


    /** ============================= GETTERS ============================== **/

    public boolean versLaDroite() {
        boolean enFace = false;
        if (versLaDroite && !versLeBas && !versLeHaut && !versLaGauche) enFace = true;
        return enFace;
    }

    public boolean versLaGauche() {
        boolean enFace = false;
        if (!versLaDroite && !versLeBas && !versLeHaut && versLaGauche) enFace = true;
        return enFace;
    }

    public boolean versLeBas() {
        boolean enFace = false;
        if (!versLaDroite && versLeBas && !versLeHaut && !versLaGauche) enFace = true;
        return enFace;
    }

    public boolean versLeHaut() {
        boolean enFace = false;
        if (!versLaDroite && !versLeBas && versLeHaut && !versLaGauche) enFace = true;
        return enFace;
    }


    /** =========================== SETTERS =========================== **/

    // face setters
    public void setVersLaDroite() {
        versLaDroite = true;
        versLeBas = false;
        versLeHaut = false;
        versLaGauche = false;
    }

    public void setVersLaGauche() {
        versLaDroite = false;
        versLeBas = false;
        versLeHaut = false;
        versLaGauche = true;
    }

    public void setVersLeBas() {
        versLaDroite = false;
        versLeBas = true;
        versLeHaut = false;
        versLaGauche = false;
    }

    public void setVersLeHaut() {
        versLaDroite = false;
        versLeBas = false;
        versLeHaut = true;
        versLaGauche = false;
    }

    /** ==================== COLLISION ============================ **/

    public void ajouterCollision(Zone zone) {
        collisions.add(zone);
    }

    public void ajouterCollision(ArrayList<Zone> zone) {
        collisions.addAll(zone);
    }

    public String getCollisionVerticale() {
        String collision = "NONE";

        // look through the list of collisions for this entities
        for(Zone c : collisions) {

            // vertical check
            if(this.versLeHaut) {
                if (c.getY() + c.getHeight() >= this.getY() && this.getY() + this.getHeight() > c.getY() + c.getHeight()
                        && ((this.getX() >= c.getX() && this.getX() + this.getWidth() <= c.getX() + c.getWidth()) ||
                        (this.getX() > c.getX() && this.getX() < c.getX() + c.getWidth()) ||
                        (this.getX() > c.getX() && this.getX() + this.getWidth() < c.getX() + c.getWidth()) ||
                        (this.getX() + this.getWidth() > c.getX() && this.getX() + this.getWidth() < c.getX() + c.getWidth()) )) {

                    collision = "UP";
                    System.out.println(this.getNom() + ": Collided Upward");
                }
            }

            else if(this.versLeBas) {
                if ( this.getY() + this.getHeight() >= c.getY() && this.getY() < c.getY()
                        && ((this.getX() >= c.getX() && this.getX() + this.getWidth() <= c.getX() + c.getWidth()) ||
                        (this.getX() > c.getX() && this.getX() < c.getX() + c.getWidth()) ||
                        (this.getX() > c.getX() && this.getX() + this.getWidth() < c.getX() + c.getWidth()) ||
                        (this.getX() + this.getWidth() > c.getX()&& this.getX() + this.getWidth() < c.getX() + c.getWidth()) )) {

                    collision = "DOWN";
                    System.out.println(this.getNom() + ": Collided downward");
                }
            }
        }
        return collision;
    }

    public String getCollisionHorizontale() {
        // horizontal check
        String collision = "NONE";

        // look through the list of collisions for this entities
        for (Zone c : collisions) {
            if (this.versLaDroite) {
                if (this.getX() + this.getWidth() >= c.getX() && c.getX() > this.getX()
                        && ((this.getY() >= c.getY() && this.getY() + this.getHeight() < c.getY() + c.getHeight()) ||
                        (this.getY() > c.getY() && this.getY() < c.getY() + c.getHeight()) ||
                        (this.getY() > c.getY() && this.getY() + this.getHeight() < c.getY() + c.getHeight()) ||
                        (this.getY() + this.getHeight() > c.getY() && this.getY() + this.getHeight() < c.getY() + c.getHeight()))) {

                    collision = "RIGHT";
                    System.out.println(this.getNom() + ": Collided rightward");
                }
            } else if (this.versLaGauche) {
                if (this.getX() <= c.getX() + c.getWidth() && c.getX() + c.getWidth() < this.getX() + this.getWidth()
                        && ((this.getY() >= c.getY() && this.getY() + this.getHeight() <= c.getY() + c.getHeight()) ||
                        (this.getY() > c.getY() && this.getY() < c.getY() + c.getHeight()) ||
                        (this.getY() > c.getY() && this.getY() + this.getHeight() < c.getY() + c.getHeight()) ||
                        (this.getY() + this.getHeight() > c.getY() && this.getY() + this.getHeight() < c.getY() + c.getHeight()))) {

                    collision = "LEFT";
                    System.out.println(this.getNom() + ": Collided leftward");
                }
            }
        }
        return collision;
    }


    /** ============================== ACTION EVENT HANDLER ===================== **/

    public void actionBy(MouvementPersonnage c) {
        // get action of Character2D c for this entities
    }

}
