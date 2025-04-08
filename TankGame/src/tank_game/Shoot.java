package tank_game;

/**
 * @author Akai
 * @version 1.0
 */
public class Shoot extends Thread {
    Bullet bullet;

    public Shoot(Bullet bullet) {
        this.bullet = bullet;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (bullet.direction == 0) {//上
                bullet.y -= bullet.speed;
            } else if (bullet.direction == 1) {//下
                bullet.y += bullet.speed;
            } else if (bullet.direction == 2) {//左
                bullet.x -= bullet.speed;
            } else if (bullet.direction == 3) {//右
                bullet.x += bullet.speed;
            }

            //线程退出
            if (bullet.x == 0 || bullet.x == 1000 || bullet.y == 0 || bullet.y == 750 || !bullet.isAlive) {
                bullet.isAlive = false;
                break;
            }
        }
    }
}
