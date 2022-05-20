package main.framework.state;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import main.framework.object2D.ZoneSensible;

/**
 * serves as the template of other game states or rooms
 */

public interface IEtat {

    void init();

    void enEntree();

    void modifier(long currentTime);

    ZoneSensible getEnnemi();

    void afficher(long currentTime);

    void enSortie();

    void enFermeture();

}
