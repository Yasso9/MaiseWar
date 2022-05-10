package main.framework.animation;


import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import main.framework.controller.Mouvement;

public class ChangementSprite extends Transition{

    private ImageView vue;
    private int count;
    private final int colonnes;
    private int differenceX;
    private int differenceY;
    private final int largeur;
    private final int hauteur;

    public ChangementSprite(ImageView vue, Duration duration, int count, int colonnes, int differenceX, int differenceY, int largeur, int hauteur) {
        this.vue = vue;
        this.count = count;
        this.colonnes = colonnes;
        this.differenceX = differenceX;
        this.differenceY = differenceY;
        this.largeur = largeur;
        this.hauteur = hauteur;
        setCycleDuration(duration);
    }

    // Methode permettant le changement de l'icone du joueur lors des mouvements
    public void modifier(Mouvement mouvement, int differenceYBas, int differenceYGauche, int differenceYDroite, int differenceYHaut) {
        if(mouvement.seDeplace()) {
            play();
            count = 3;
            differenceX = 0;

            if (mouvement.getMouvementPersonnage().versLeBas()) differenceY = differenceYBas;
            else if (mouvement.getMouvementPersonnage().versLaGauche()) differenceY = differenceYBas;
            else if (mouvement.getMouvementPersonnage().versLaDroite()) differenceY = differenceYBas;
            else if (mouvement.getMouvementPersonnage().versLeHaut()) differenceY = differenceYBas;
        } else {
            count = 1;
            differenceX = 32;
        }
    }

    public void modifierVue(Mouvement mouvement) {
        // updating
        vue.setTranslateX(mouvement.getMouvementPersonnage().getX() - 6); // default is without the minus modifier
        vue.setTranslateY(mouvement.getMouvementPersonnage().getY() - 16); // default is without the minus modifier
    }

    @Override
    protected void interpolate(double frac) {
        final int index = Math.min((int) Math.floor(frac * count), count - 1);
        final int x = (index % colonnes) * largeur + differenceX;
        final int y = (index / colonnes) * hauteur + differenceY;
        vue.setViewport(new Rectangle2D(x, y, largeur, hauteur));
    }

}
