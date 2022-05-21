package main.framework.entities;

import main.framework.object2D.GameObject2D;
import main.framework.object2D.Hotspot;

public class Item extends Entity{

    private String name;
    private double width;
    private double height;
    private double positionX;
    private double positionY;

    public Item(String name, double width, double height, double positionX, double positionY){
        super(name, width, height, positionX, positionY);
    }


}
