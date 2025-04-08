package tank_game;

/**
 * @author Akai
 * @version 1.0
 */
public class Bullet {
    int x;
    int y;
    int direction;
    int speed;
    boolean isAlive = true;

    public Bullet(int x, int y, int direction, int speed, boolean isAlive) {
        this.y = y;
        this.x = x;
        this.direction = direction;
        this.speed = speed;
        this.isAlive = isAlive;
    }
}
