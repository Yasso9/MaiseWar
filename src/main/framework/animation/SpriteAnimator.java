package main.framework.animation;


import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import main.framework.controller.Mouvement;

public class SpriteAnimator extends Transition{

    private ImageView imageView;
    private int count;
    private final int columns;
    private int offsetX;
    private int offsetY;
    private final int width;
    private final int height;

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

    // Methode permettant le changement de l'icone du joueur lors des mouvements
    public void update(Mouvement mouvement, int offsetYDown, int offsetYLeft, int offsetYRight, int offsetYUp) {
        if(mouvement.seDeplace()) {
            play();
            count = 3;
            offsetX = 0;

            if (mouvement.getCharacter2D().versLeBas()) offsetY = offsetYDown;
            else if (mouvement.getCharacter2D().versLaGauche()) offsetY = offsetYDown;
            else if (mouvement.getCharacter2D().versLaDroite()) offsetY = offsetYDown;
            else if (mouvement.getCharacter2D().versLeHaut()) offsetY = offsetYDown;
        } else {
            count = 1;
            offsetX = 32;
        }
    }

    public void updateView(Mouvement mouvement) {
        // updating
        imageView.setTranslateX(mouvement.getCharacter2D().getX() - 6); // default is without the minus modifier
        imageView.setTranslateY(mouvement.getCharacter2D().getY() - 16); // default is without the minus modifier
    }

    @Override
    protected void interpolate(double frac) {
        final int index = Math.min((int) Math.floor(frac * count), count - 1);
        final int x = (index % columns) * width + offsetX;
        final int y = (index / columns) * height + offsetY;
        imageView.setViewport(new Rectangle2D(x, y, width, height));
    }

}
