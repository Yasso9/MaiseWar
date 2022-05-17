package main.framework.entities;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.framework.object2D.Zone;
import main.framework.object2D.ZoneSensible;

import java.util.concurrent.TimeUnit;

public class Personnage {

    // attributes
    double attributPhysique;
    double attributMental;

    // stats
    double prouesse;
    double endurance;

    private String nom;
    private double largeur;
    private double hauteur;
    private double positionX;
    private double positionY;

    private double vie;
    private double attaque;
    private int delaiAttaque; //en secondes

    public Personnage(String nom, double largeur, double hauteur, double positionX, double positionY,  double vie, double attaque, int delaiAttaque){
        this.nom = nom;
        this.vie = vie;
        this.attaque = attaque;
        this.delaiAttaque = delaiAttaque;
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public Personnage(String nom, double largeur, double hauteur, double positionX, double positionY){
        this.nom = nom;
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public Personnage(String nom,  double vie, double attaque, int delaiAttaque){
        this.nom = nom;
        this.vie = vie;
        this.attaque = attaque;
        this.delaiAttaque = delaiAttaque;
    }

    public void attaque(Personnage personnageVictime){
        personnageVictime.vie = personnageVictime.vie - this.attaque;
        System.out.println(personnageVictime.vie + " de " + personnageVictime.nom);
    }

    public int getDelaiAttaque(){
        return this.delaiAttaque;
    }

    public void drawPersonnage(GraphicsContext contexteGraphique, Image image){

    }

    public ZoneSensible creerZoneSensiblePersonnage(){
        return new ZoneSensible(this.nom, this.largeur, this.hauteur, this.positionX, this.positionY);
    }

    public Zone creerZonePersonnage(){
        return new Zone(this.nom, this.largeur, this.hauteur, this.positionX, this.positionY);
    }


    public void supprimerZonePersonnage(){

    }

    public boolean estEnVie(){
        boolean enVie = false;
        if(this.vie > 0){
            enVie = true;
        }
        return enVie;
    }

}
