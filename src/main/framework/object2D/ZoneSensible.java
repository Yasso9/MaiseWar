package main.framework.object2D;

import java.util.ArrayList;


public class ZoneSensible extends Zone {
    /**
     * ====================== CONSTRUCTOR ============================
     *
     * @param nom
     * @param largeur
     * @param hauteur
     * @param x
     * @param y
     **/

    private ArrayList<PositionPersonnage> positionPersonnageAutorises = new ArrayList<>();

    public ZoneSensible(String nom, double largeur, double hauteur, double positionX, double positionY) {
        super(nom, largeur, hauteur, positionX, positionY);
    }

    public void ajoutPersonnageDeclencheur(PositionPersonnage positionPersonnage) {
        positionPersonnageAutorises.add(positionPersonnage);
    }

    public boolean personnageSurZoneSensible() {

        boolean declencherZone = false;

        for (PositionPersonnage positionPersonnage : positionPersonnageAutorises) {

            if( this.intersects(positionPersonnage.getLayoutBounds()) ) {
                // returns true if character2D touches the hotspot
                declencherZone = true;
            }
        }

        return declencherZone;
    }

}
