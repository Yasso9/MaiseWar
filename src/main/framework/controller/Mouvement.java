package main.framework.controller;

import main.framework.object2D.PositionPersonnage;

import java.util.ArrayList;

public class Mouvement {

    private boolean deplacementDroite = false;
    private boolean deplacementGauche = false;
    private boolean deplacementHaut = false;
    private boolean deplacementBas = false;

    private Manette manette;
    private PositionPersonnage positionPersonnage;
    private ArrayList<String> entrees;

    public Mouvement(Manette manette, PositionPersonnage positionPersonnage) {
        this.positionPersonnage = positionPersonnage;
        this.manette = manette;
    }

    public PositionPersonnage getCharacter2D() {
        return positionPersonnage;
    }

    public boolean seDeplace() {
        boolean retval = false;
        if (deplacementGauche || deplacementHaut || deplacementDroite || deplacementBas) retval = true;
        return retval;
    }

    public boolean seDeplaceVersLeBas() {
        return deplacementBas;
    }

    public boolean seDeplaceVersLeHaut() {
        return deplacementHaut;
    }

    public boolean seDeplaceVersLaDroite() {
        return deplacementDroite;
    }

    public boolean seDeplaceVersLaGauche() {
        return deplacementGauche;
    }

    public void update() {

        entrees = manette.getEntrees();

        if(entrees.contains("RIGHT"))
            deplacementDroite = true;
        else
            deplacementDroite = false;


        if(entrees.contains("LEFT"))
            deplacementGauche = true;
        else
            deplacementGauche = false;


        if(entrees.contains("UP"))
            deplacementHaut = true;
        else
            deplacementHaut = false;


        if(entrees.contains("DOWN"))
            deplacementBas = true;
        else
            deplacementBas = false;

        modifierPosition();
    }


    public void modifierPosition() throws NullPointerException {

        // straight movement
        if(deplacementDroite){
            if (positionPersonnage.versLaDroite() && positionPersonnage.getCollisionHorizontale() == "NONE")
                positionPersonnage.setX(positionPersonnage.getX() + positionPersonnage.getVitesse());
            else
                positionPersonnage.setVersLaDroite();
        }

        else if(deplacementGauche){
            if (positionPersonnage.versLaGauche() && positionPersonnage.getCollisionHorizontale() == "NONE")
                positionPersonnage.setX(positionPersonnage.getX() - positionPersonnage.getVitesse());
            else
                positionPersonnage.setVersLaGauche();
        }

        else if(deplacementHaut){
            if (positionPersonnage.versLeHaut() && positionPersonnage.getCollisionVerticale() == "NONE")
                positionPersonnage.setY(positionPersonnage.getY() - positionPersonnage.getVitesse());
            else
                positionPersonnage.setVersLeHaut();
        }

        else if(deplacementBas){
            if (positionPersonnage.versLeBas() && positionPersonnage.getCollisionVerticale() == "NONE")
                positionPersonnage.setY(positionPersonnage.getY() + positionPersonnage.getVitesse());
            else
                positionPersonnage.setVersLeBas();
        }

    }

}
