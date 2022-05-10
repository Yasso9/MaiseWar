package main.framework.controller;


import javafx.scene.Scene;

import java.util.ArrayList;

public class Manette {

    private ArrayList<String> entrees;
    private boolean desactive = false;

    // constructor

    public Manette(Scene scene) {

        entrees = new ArrayList<>();

        scene.setOnKeyPressed(e -> {
            System.out.println("Pressed " + e.getCode().toString());
            String codeTouche = e.getCode().toString();
            if (!desactive && !entrees.contains(codeTouche))
                entrees.add(codeTouche);
        });

        scene.setOnKeyReleased(e -> {
            System.out.println("Released " + e.getCode().toString());
            String codeTouche = e.getCode().toString();
            if (entrees.contains(codeTouche))
                entrees.remove(codeTouche);
        });
    }

    // public methods

    public ArrayList<String> getEntrees() {
        return entrees;
    }

    public void setDesactive(boolean b) {
        this.desactive = b;
    }

    public boolean estDesactive() {
        return desactive;
    }

}
