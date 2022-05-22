package main.framework.animation;


import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import main.framework.controller.Mover;

/**
 * SpriteAnimator est la classe qui définie tout ce qui est autour de l'animation d'un sprite.
 * Elle dérive de la classe Transition.
 * <ul>
 * <li>imageView est le composant qui permet d'afficher des images sur l'application JavaFX.</li>
 * </ul>
 *
 * </p>
 * </p>
 * @author nom de l'auteur
 * @version numéro de version
 */
public class SpriteAnimator extends Transition{

    /**
     * Composant qui permet d'afficher des images sur l'application JavaFX
     */
    private ImageView imageView;
    private int count;
    private final int columns;
    private int offsetX;
    private int offsetY;
    private final int width;
    private final int height;

    /**
     * <b>Constructeur de Character</b>
     *
     * @param imageView
     *      Composant qui permet d'afficher des images sur l'application JavaFX
     * @param width
     *      Largeur de l'image
     * @param height
     *      Hauteur de l'image
     * @param duration
     *      Définit la durée de l'animation
     * @param offsetX
     *      Position de l'animation sur l'axe des absysses
     * @param offsetY
     *      Position de l'animation sur l'axe des ordonnées
     */
    public SpriteAnimator(ImageView imageView, Duration duration, int count, int columns, int offsetX, int offsetY, int width, int height) {
        this.imageView = imageView;
        this.count = count;
        this.columns = columns;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;
        setCycleDuration(duration);
    }

    /**
     * Methode permettant le changement de l'icone du joueur lors des mouvements
     *
     * @param mover
     * @param offsetYDown
     * @param offsetYLeft
     * @param offsetYRight
     * @param offsetYUp
     *
     * @return Void
     */
    public void update(Mover mover, int offsetYDown, int offsetYLeft, int offsetYRight, int offsetYUp) {
        if(mover.isMoving()) {
            play();
            count = 3;
            offsetX = 0;
            offsetY = offsetYDown;
        } else {
            count = 1;
            offsetX = 32;
        }
    }

    /**
     * @param mover
     */
    public void updateView(Mover mover) {
        imageView.setTranslateX(mover.getCharacter2D().getX() - 6);
        imageView.setTranslateY(mover.getCharacter2D().getY() - 16);
    }

    @Override
    protected void interpolate(double frac) {
        final int index = Math.min((int) Math.floor(frac * count), count - 1);
        final int x = (index % columns) * width + offsetX;
        final int y = (index / columns) * height + offsetY;
        imageView.setViewport(new Rectangle2D(x, y, width, height));
    }

}
