package main.framework.entities;

import main.framework.object2D.GameObject2D;
import main.framework.object2D.Hotspot;

public class Entity {

    private String name;
    private double width;
    private double height;
    private double positionX;
    private double positionY;

    public Entity(String name, double width, double height, double positionX, double positionY){
        this.name = name;
        this.width = width;
        this.height = height;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public Entity(String name){
        this.name = name;
    }

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
