package tank_game;

import java.util.Vector;

/**
 * @author Akai
 * @version 1.0
 */
public class EnemyTank extends Tank {

    Vector<EnemyTank> enemyTanks = new Vector<>();
    MyTank myTank;

    public EnemyTank(int x, int y, int direction) {
        super(x, y, direction, 0);
    }

    public Vector<EnemyTank> getEnemyTanks() {
        return enemyTanks;
    }

    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    public MyTank getMyTank() {
        return myTank;
    }

    public void setMyTank(MyTank myTank) {
        this.myTank = myTank;
    }

    //判断是否将与其他坦克相撞
    public boolean isTouching() {
        Vector<EnemyTank> enemies = enemyTanks;
        MyTank myTank = this.myTank;
        switch (getDirection()) {
            case 0://上
                //和坦克集比较
                for (EnemyTank enemyTank : enemies) {
                    if (enemyTank != this) {
                        if (enemyTank.getDirection() == 0 || enemyTank.getDirection() == 1) {//上下
                            if (getX() > enemyTank.getX() - 40 && getX() < enemyTank.getX() + 40 && getY() > enemyTank.getY() && getY() < enemyTank.getY() + 60 + getSpeed())
                                return true;
                        } else if (enemyTank.getDirection() == 2 || enemyTank.getDirection() == 3) {//左右
                            if (getX() > enemyTank.getX() - 40 && getX() < enemyTank.getX() + 60 && getY() > enemyTank.getY() && getY() < enemyTank.getY() + 40 + getSpeed())
                                return true;
                        }
                    }
                }
                //和myTank比较
                if (myTank.getDirection() == 0 || myTank.getDirection() == 1) {//上下
                    if (getX() > myTank.getX() - 40 && getX() < myTank.getX() + 40 && getY() > myTank.getY() && getY() < myTank.getY() + 60 + getSpeed())
                        return true;
                } else if (myTank.getDirection() == 2 || myTank.getDirection() == 3) {//左右
                    if (getX() > myTank.getX() - 40 && getX() < myTank.getX() + 60 && getY() > myTank.getY() && getY() < myTank.getY() + 40 + getSpeed())
                        return true;
                }
                return false;
            case 1://下
                //和坦克集比较
                for (EnemyTank enemyTank : enemies) {
                    if (enemyTank != this) {
                        if (enemyTank.getDirection() == 0 || enemyTank.getDirection() == 1) {//上下
                            if (getX() > enemyTank.getX() - 40 && getX() < enemyTank.getX() + 40 && getY() > enemyTank.getY() - getSpeed() - 60 && getY() < enemyTank.getY())
                                return true;
                        } else if (enemyTank.getDirection() == 2 || enemyTank.getDirection() == 3) {//左右
                            if (getX() > enemyTank.getX() - 40 && getX() < enemyTank.getX() + 60 && getY() > enemyTank.getY() - getSpeed() - 60 && getY() < enemyTank.getY() - 20)
                                return true;
                        }
                    }
                }
                //和myTank比较
                if (myTank.getDirection() == 0 || myTank.getDirection() == 1) {//上下
                    if (getX() > myTank.getX() - 40 && getX() < myTank.getX() + 40 && getY() > myTank.getY() - getSpeed() - 60 && getY() < myTank.getY())
                        return true;
                } else if (myTank.getDirection() == 2 || myTank.getDirection() == 3) {//左右
                    if (getX() > myTank.getX() - 40 && getX() < myTank.getX() + 60 && getY() > myTank.getY() - getSpeed() - 60 && getY() < myTank.getY() - 20)
                        return true;
                }
                return false;
            case 2://左
                //和坦克集比较
                for (EnemyTank enemyTank : enemies) {
                    if (enemyTank != this) {
                        if (enemyTank.getDirection() == 0 || enemyTank.getDirection() == 1) {//上下
                            if (getX() > enemyTank.getX() && getX() < enemyTank.getX() + 40 + getSpeed() && getY() > enemyTank.getY() - 40 && getY() < enemyTank.getY() + 60)
                                return true;
                        } else if (enemyTank.getDirection() == 2 || enemyTank.getDirection() == 3) {//左右
                            if (getX() > enemyTank.getX() && getX() < enemyTank.getX() + 60 + getSpeed() && getY() > enemyTank.getY() - 40 && getY() < enemyTank.getY() + 40)
                                return true;
                        }
                    }
                }
                //和myTank比较
                if (myTank.getDirection() == 0 || myTank.getDirection() == 1) {//上下
                    if (getX() > myTank.getX() && getX() < myTank.getX() + 40 + getSpeed() && getY() > myTank.getY() - 40 && getY() < myTank.getY() + 60)
                        return true;
                } else if (myTank.getDirection() == 2 || myTank.getDirection() == 3) {//左右
                    if (getX() > myTank.getX() && getX() < myTank.getX() + 60 + getSpeed() && getY() > myTank.getY() - 40 && getY() < myTank.getY() + 40)
                        return true;
                }
                return false;
            case 3://右
                //和坦克集比较
                for (EnemyTank enemyTank : enemies) {
                    if (enemyTank != this) {
                        if (enemyTank.getDirection() == 0 || enemyTank.getDirection() == 1) {//上下
                            if (getX() > enemyTank.getX() - getSpeed() - 60 && getX() < enemyTank.getX() - 20 && getY() > enemyTank.getY() - 40 && getY() < enemyTank.getY() + 60)
                                return true;
                        } else if (enemyTank.getDirection() == 2 || enemyTank.getDirection() == 3) {//左右
                            if (getX() > enemyTank.getX() - getSpeed() - 60 && getX() < enemyTank.getX() && getY() > enemyTank.getY() - 40 && getY() < enemyTank.getY() + 40)
                                return true;
                        }
                    }
                }
                //和myTank比较
                if (myTank.getDirection() == 0 || myTank.getDirection() == 1) {//上下
                    if (getX() > myTank.getX() - getSpeed() - 60 && getX() < myTank.getX() - 20 && getY() > myTank.getY() - 40 && getY() < myTank.getY() + 60)
                        return true;
                } else if (myTank.getDirection() == 2 || myTank.getDirection() == 3) {//左右
                    if (getX() > myTank.getX() - getSpeed() - 60 && getX() < myTank.getX() && getY() > myTank.getY() - 40 && getY() < myTank.getY() + 40)
                        return true;
                }
                return false;
            default:
                return false;
        }
    }

    //开启自动射击
    public void autoShoot() {
        new AutoShoot(this).start();
    }

    public void autoMove() {
        new AutoMove(this).start();
    }
}
