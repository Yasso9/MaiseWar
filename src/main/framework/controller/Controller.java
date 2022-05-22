package main.framework.controller;


import javafx.scene.Scene;

import java.util.ArrayList;

/**
 * Controller est la classe qui contient la logique concernant les actions effectuées par l'utilisateur..
 * </p>
 * </p>
 * @author PCEBL
 * @version 2.0
 */
public class Controller {

    /**
     * Liste d'entrées de l'utilisateur
     */
    private ArrayList<String> input;
    private boolean disabled = false;

    /**
     * <b>Constructeur de Character</b>
     *
     * @param scene
     */
    public Controller(Scene scene) {

        input = new ArrayList<>();

        scene.setOnKeyPressed(e -> {
            System.out.println("Pressed " + e.getCode().toString());
            String keyCode = e.getCode().toString();
            if (!disabled && !input.contains(keyCode))
                input.add(keyCode);
        });

        scene.setOnKeyReleased(e -> {
            System.out.println("Released " + e.getCode().toString());
            String keyCode = e.getCode().toString();
            if (input.contains(keyCode))
                input.remove(keyCode);
        });
    }

    /**
     * @return Les entrées de l'utilisateur
     */
    public ArrayList<String> getInputs() {
        return input;
    }

    public void setDisabled(boolean b) {
        this.disabled = b;
    }

    public boolean isDisabled() {
        return disabled;
    }

}
