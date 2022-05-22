package main.framework.entities;

import main.framework.object2D.GameObject2D;
import main.framework.object2D.Hotspot;

/**
 * Entity est la classe qui définie une entitée.
 *
 * Cette classe est caractérisée par les informations suivantes :
 * <ul>
 * <li>name: Le nom de l'entité/li>
 * <li>widht, height: La taille de l'image de l'entité</li>
 * <li>positionX, positionY: Position en absysse et ordonée de l'entité sur l'écran</li>
 * </ul>
 * </p>
 * </p>
 * @author PCEBL
 * @version 2.0
 */
public class Entity {

    /**
     * Nom de l'entité
     */
    private String name;

    /**
     * Largeur de l'image de l'entité
     */
    private double width;

    /**
     * Hauteur de l'image de l'entité
     */
    private double height;

    /**
     * Position sur l'axe des absysses de l'entité
     */
    private double positionX;
    /**
     * Position sur l'axe des ordonnées de l'entité
     */
    private double positionY;

    /**
     * <b>Constructeur de Entity</b>
     * @param name
     *      Nom de l'entité
     * @param width
     *      Largeur de l'image de l'entité
     * @param height
     *      Hauteur de l'image de l'entité
     * @param positionX
     *      Position de l'entité sur l'axe des abssysses
     * @param positionY
     *      Position de l'entité sur l'axe des ordonnées
     */
    public Entity(String name, double width, double height, double positionX, double positionY){
        this.name = name;
        this.width = width;
        this.height = height;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    /**
     * Constructeur de Entity
     * @param name
     *      Le nom de l'entité.
     */
    public Entity(String name){
        this.name = name;
    }

    /**
     * @return Le nom de l'entité
     */
    public String getName() {
        return name;
    }

    public Hotspot createHotSpot(){
        return new Hotspot(this.name, this.width, this.height, this.positionX, this.positionY);
    }

    public GameObject2D createGameObject2D(){
        return new GameObject2D(this.name, this.width, this.height, this.positionX, this.positionY);
    }

}
