package main.framework.entities;

import main.framework.object2D.GameObject2D;
import main.framework.object2D.Hotspot;

public class Character {
    
    private String name;
    private double width;
    private double height;
    private double positionX;
    private double positionY;

    private double healthPoints;
    private double damagePoints;
    private int attackPeriod; //in seconds

    public Character(String name, double width, double height, double positionX, double positionY,  double healthPoints, double damagePoints, int attackPeriod){
        this.name = name;
        this.healthPoints = healthPoints;
        this.damagePoints = damagePoints;
        this.attackPeriod = attackPeriod;
        this.width = width;
        this.height = height;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public Character(String name, double width, double height, double positionX, double positionY){
        this.name = name;
        this.width = width;
        this.height = height;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public Character(String name,  double healthPoints, double damagePoints, int attackPeriod){
        this.name = name;
        this.healthPoints = healthPoints;
        this.damagePoints = damagePoints;
        this.attackPeriod = attackPeriod;
    }

    public void attack(Character personnageVictime){
        personnageVictime.healthPoints = personnageVictime.healthPoints - this.damagePoints;
        System.out.println(personnageVictime.healthPoints + " de " + personnageVictime.name);
    }

    public int getAttackPeriod(){
        return this.attackPeriod;
    }

    public Hotspot createCharacterHotSpot(){
        return new Hotspot(this.name, this.width, this.height, this.positionX, this.positionY);
    }

    public GameObject2D createCharacterGameObject2D(){
        return new GameObject2D(this.name, this.width, this.height, this.positionX, this.positionY);
    }

    public boolean isAlive(){
        boolean alive = false;
        if(this.healthPoints > 0){
            alive = true;
        }
        return alive;
    }

}