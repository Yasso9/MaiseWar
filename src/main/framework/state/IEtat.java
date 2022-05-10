package main.framework.state;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;

/**
 * serves as the template of other game states or rooms
 */

public interface IEtat {

    void init();

    void enEntree();

    void modifier(long currentTime);

    void afficher();

    void enSortie();

    void enFermeture();

}
