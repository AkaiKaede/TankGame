package tank_game;

/**
 * @author Akai
 * @version 1.0
 */
public class AutoMove extends Thread {
    EnemyTank enemyTank;

    public AutoMove(EnemyTank enemyTank) {
        this.enemyTank = enemyTank;
    }

    @Override
    public void run() {
        int step = 0;
        while (!enemyTank.isDead()) {
            if (!enemyTank.isTouching())
                enemyTank.moveForward();
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            step++;
            if (step == 10) {
                enemyTank.setDirection((int) (Math.random() * 4));
                step = 0;
            }
        }
    }
}
