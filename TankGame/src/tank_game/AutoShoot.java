package tank_game;

/**
 * @author Akai
 * @version 1.0
 */
public class AutoShoot extends Thread {
    EnemyTank tank;

    public AutoShoot(EnemyTank tank) {
        this.tank = tank;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(tank.isDead()){
                break;
            }
            tank.shoot();
        }
    }
}
