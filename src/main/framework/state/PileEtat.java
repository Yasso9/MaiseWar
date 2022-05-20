package main.framework.state;

import java.util.HashMap;
import java.util.Stack;


public class PileEtat {

    private static HashMap<String, IEtat> historiqueEtat = new HashMap<>();
    private static Stack<IEtat> pileEtat = new Stack<>();

    public static void ajouterEtat(String key ,IEtat state) {
        historiqueEtat.put(key, state);
    }

    public static void afficherEtat(String key) {
        pileEtat.push(historiqueEtat.get(key));
        pileEtat.lastElement().init();
    }

    public static IEtat precedentEtat() {
        pileEtat.lastElement().enSortie();
        return pileEtat.pop();
    }


    public static void popToMainMenu() {
        System.out.println("initializing popper");
        int compteur = 0;
        for (IEtat etat : pileEtat) {
            compteur++;
        }
        System.out.println("Counter = " + compteur);
        for(int i = 1; i < compteur; i++) {
            precedentEtat().enFermeture();
            System.out.println("popping");
        }
        getEtatActuel().enEntree();
    }

    public static IEtat getEtatActuel() {
        return pileEtat.lastElement();
    }

}
