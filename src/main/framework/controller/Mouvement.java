package main.framework.controller;

import main.framework.object2D.MouvementPersonnage;

import java.util.ArrayList;

public class Mouvement {

    private boolean deplacementDroite = false;
    private boolean deplacementGauche = false;
    private boolean deplacementHaut = false;
    private boolean deplacementBas = false;

    private Controleur controleur;
    private MouvementPersonnage mouvementPersonnage;
    private ArrayList<String> entrees;

    public Mouvement(Controleur controleur, MouvementPersonnage mouvementPersonnage) {
        this.mouvementPersonnage = mouvementPersonnage;
        this.controleur = controleur;
    }

    public MouvementPersonnage getMouvementPersonnage() {
        return mouvementPersonnage;
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

        entrees = controleur.getEntrees();

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
            if (mouvementPersonnage.versLaDroite() && mouvementPersonnage.getCollisionHorizontale() == "NONE")
                mouvementPersonnage.setX(mouvementPersonnage.getX() + mouvementPersonnage.getVitesse());
            else
                mouvementPersonnage.setVersLaDroite();
        }

        else if(deplacementGauche){
            if (mouvementPersonnage.versLaGauche() && mouvementPersonnage.getCollisionHorizontale() == "NONE")
                mouvementPersonnage.setX(mouvementPersonnage.getX() - mouvementPersonnage.getVitesse());
            else
                mouvementPersonnage.setVersLaGauche();
        }

        else if(deplacementHaut){
            if (mouvementPersonnage.versLeHaut() && mouvementPersonnage.getCollisionVerticale() == "NONE")
                mouvementPersonnage.setY(mouvementPersonnage.getY() - mouvementPersonnage.getVitesse());
            else
                mouvementPersonnage.setVersLeHaut();
        }

        else if(deplacementBas){
            if (mouvementPersonnage.versLeBas() && mouvementPersonnage.getCollisionVerticale() == "NONE")
                mouvementPersonnage.setY(mouvementPersonnage.getY() + mouvementPersonnage.getVitesse());
            else
                mouvementPersonnage.setVersLeBas();
        }

    }

}
