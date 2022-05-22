package main.framework.entities;

import main.framework.object2D.GameObject2D;
import main.framework.object2D.Hotspot;

/**
 * Item est la classe qui définie un objet.
 * Elle dérive de la classe Entity.
 * </p>
 * </p>
 * @author PCEBL
 * @version 2.0
 */
public class Item extends Entity{

    /**
     * <b>Constructeur de Item</b>
     *
     * @param name
     *      Nom de l'item
     * @param width
     *      Largeur de l'image
     * @param height
     *      Hauteur de l'image
     * @param positionX
     *      Position de l'item sur l'axe des abssysses
     * @param positionY
     *      Position de l'item sur l'axe des ordonnées
     */
    public Item(String name, double width, double height, double positionX, double positionY){
        super(name, width, height, positionX, positionY);
    }

}
