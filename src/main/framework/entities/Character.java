package main.framework.entities;

import main.framework.object2D.GameObject2D;
import main.framework.object2D.Hotspot;

import java.util.ArrayList;

public class Character extends Entity {
    
    private double healthPoints;
    private double damagePoints;
    private int attackPeriod; //in seconds

    private ArrayList<Item> inventory = new ArrayList<>();


    public Character(String name, double width, double height, double positionX, double positionY,  double healthPoints, double damagePoints, int attackPeriod){
        super(name, width, height, positionX, positionY);
        this.healthPoints = healthPoints;
        this.damagePoints = damagePoints;
        this.attackPeriod = attackPeriod;
    }

    public Character(String name, double width, double height, double positionX, double positionY){
        super(name, width, height, positionX, positionY);
    }

    public Character(String name,  double healthPoints, double damagePoints, int attackPeriod){
        super(name);
        this.healthPoints = healthPoints;
        this.damagePoints = damagePoints;
        this.attackPeriod = attackPeriod;
    }

    public void attack(Character personnageVictime){
        personnageVictime.healthPoints = personnageVictime.healthPoints - this.damagePoints;
        System.out.println(personnageVictime.healthPoints + " de " + personnageVictime.getName());
    }

    public int getAttackPeriod(){
        return this.attackPeriod;
    }

    public boolean isAlive(){
        boolean alive = false;
        if(this.healthPoints > 0){
            alive = true;
        }
        return alive;
    }

    public void setHealthPoints(double healthPoints) {
        this.healthPoints = healthPoints;
    }

    public void setDamagePoints(double damagePoints) {
        this.damagePoints = damagePoints;
    }

    public double getHealthPoints() {
        return this.healthPoints;
    }

    public void addToInventory(Item item){
        this.inventory.add(item);
    }

    public ArrayList<Item> getInventory(){
        return this.inventory;
    }


}