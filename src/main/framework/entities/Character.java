package main.framework.entities;

import main.framework.object2D.GameObject2D;
import main.framework.object2D.Hotspot;

import java.util.ArrayList;

/**
 * Character est la classe qui définie un joueur.
 * Elle dérive de la classe Entity.
 * Cette classe est caractérisée par les informations suivantes :
 * <ul>
 * <li>healthPoints: Les points de vies restants du joueur</li>
 * <li>damagePoints: Le nombre de dégats du joueur</li>
 * <li>attackPeriod: Le temps d'attaque du joueur</li>
 * <li>inventory: Liste qui contient les éléments dans l'inventaire du joueur</li>
 * </ul>
 * </p>
 * </p>
 * @author PCEBL
 * @version 2.0
 * @see Entity
 */
public class Character extends Entity {

    /**
     * Nombre de points de vie du personnage
     */
    private double healthPoints;

    /**
     * Nombre de points de dégats du personnage
     */
    private double damagePoints;

    /**
     * Temps d'attaque du personnage (en secondes)
     */
    private int attackPeriod;

    /**
     * Liste qui contient les objets dans l'inventaire du joueur
     */
    private ArrayList<Item> inventory = new ArrayList<>();

    /**
     * <b>Constructeur de Character</b>
     *
     * @param name
     *      Nom du personnage
     * @param width
     *      Largeur de l'image du personnage
     * @param height
     *      Hauteur de l'image du personnage
     * @param positionX
     *      Position du personnage sur l'axe des abssysses
     * @param positionY
     *      Position du personnage sur l'axe des ordonnées
     * @param healthPoints
     *      Nombre de points de vie du personnage
     * @param damagePoints
     *      Nombre de points d'attaque du personnage
     * @param attackPeriod
     *      Temps d'attaque du personnage
     */
    public Character(String name, double width, double height, double positionX, double positionY,  double healthPoints, double damagePoints, int attackPeriod){
        super(name, width, height, positionX, positionY);
        this.healthPoints = healthPoints;
        this.damagePoints = damagePoints;
        this.attackPeriod = attackPeriod;
    }

    /**
     * <b>Constructeur de Character</b>
     * Ce constructeur crée un personnage à partir de la classe Entity
     *
     * @param name
     *      Nom du personnage
     * @param width
     *      Largeur de l'image du personnage
     * @param height
     *      Hauteur de l'image du personnage
     * @param positionX
     *      Position du personnage sur l'axe des abssysses
     * @param positionY
     *      Position du personnage sur l'axe des ordonnées
     */
    public Character(String name, double width, double height, double positionX, double positionY){
        super(name, width, height, positionX, positionY);
    }

    /**
     * <b>Constructeur de Character</b>
     * Ce constructeur crée un personnage à partir de la classe Entity
     *
     * @param name
     *      Nom du personnage
     * @param healthPoints
     *      Nombre de points de vie du personnage
     * @param damagePoints
     *      Nombre de points d'attaque du personnage
     * @param attackPeriod
     *      Temps d'attaque du personnage
     */
    public Character(String name,  double healthPoints, double damagePoints, int attackPeriod){
        super(name);
        this.healthPoints = healthPoints;
        this.damagePoints = damagePoints;
        this.attackPeriod = attackPeriod;
    }

    /**
     * Quand un personnage est attaqué, cette méthode permet de réduire les points de vies du personnage attaqué.
     *
     * @param personnageVictime
     *     Personnage ciblé par l'attaque
     */
    public void attack(Character personnageVictime){
        personnageVictime.healthPoints = personnageVictime.healthPoints - this.damagePoints;
        System.out.println(personnageVictime.healthPoints + " de " + personnageVictime.getName());
    }

    /**
     * @return Le temps d'attaque du personnage
     */
    public int getAttackPeriod(){
        return this.attackPeriod;
    }

    /**
     * Cette méthode permet de savoir si le personnage est vivant ou mort.
     *
     * @return True si le personnage est vivant, False sinon.
     */
    public boolean isAlive(){
        boolean alive = false;
        if(this.healthPoints > 0){
            alive = true;
        }
        return alive;
    }

    /**
     * Cette méthode permet de redéfinir le nombre de points de vie du personnage.
     *
     * @param healthPoints
     *      Nombre de points de vie du personnage
     */
    public void setHealthPoints(double healthPoints) {
        this.healthPoints = healthPoints;
    }

    /**
     * Cette méthode permet de redéfinir le nombre de points d'attaque du personnage.
     *
     * @param damagePoints
     *      Nombre de points d'attaque du personnage
     */
    public void setDamagePoints(double damagePoints) {
        this.damagePoints = damagePoints;
    }

    /**
     * @return Le nombre de points de vie du personnage
     */
    public double getHealthPoints() {
        return this.healthPoints;
    }

    /**
     * Cette méthode permet d'ajouter un item à l'inventaire du personnage.
     *
     * @param item
     *      Objet à ajouter à l'inventaire
     */
    public void addToInventory(Item item){
        this.inventory.add(item);
    }

    /**
     * @return La liste des éléments dans l'inventaire du personnage
     */
    public ArrayList<Item> getInventory(){
        return this.inventory;
    }
}