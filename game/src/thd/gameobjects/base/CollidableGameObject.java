package thd.gameobjects.base;


import thd.gameView.GameView;

import java.awt.*;


/** Game objects that are able to collide with something. */
public abstract class CollidableGameObject extends GameObject {
    private final Rectangle hitBox;
    protected double hitBoxOffsetX;
    protected double hitBoxOffsetY;
    protected double hitBoxWidth;
    protected double hitBoxHeight;


    /**
     * Crates a new GameObject.
     *
     * @param gameView        Window to show the GameObject on.
     */
    public CollidableGameObject(GameView gameView) {
        super(gameView);
        hitBox = new Rectangle(0, 0, 0, 0);
    }


    private void updateHitBoxPosition() {
        hitBox.x = (int) (position.x + hitBoxOffsetX);
        hitBox.y = (int) (position.y + hitBoxOffsetY);
        hitBox.width = (int) hitBoxWidth;
        hitBox.height = (int) hitBoxHeight;
        // Nur vorübergehend: Die Hitbox wird angezeigt. Bitte diese Zeile nach der Abgabe auskommentieren/löschen.
        // gameView.addRectangleToCanvas(hitBox.x, hitBox.y, hitBox.width, hitBox.height, 1, false, Color.RED);
    }


    /**
     * Determines if this game object is collided with the other game object.
     *
     * @param other The other game object.
     * @return <code>true</code> if the there was a collision.
     */
    public final boolean collidesWith(CollidableGameObject other) {
        updateHitBoxPosition();
        other.updateHitBoxPosition();
        return hitBox.intersects(other.hitBox);
    }

    /**
     * If a game object is collided with something, it is able to react to the collision.
     *
     * @param other The other GameObject that is involved in the collision.
     */
    public abstract void reactToCollision(CollidableGameObject other);


    /**
     * Verschiebung der Spielwelt.
     *
     * @param shiftX Verschiebung in X-Richtung
     */
    @Override
    public void worldHasMoved(double shiftX) {
        super.worldHasMoved(shiftX);
        hitBox.x -= shiftX;
    }

}
