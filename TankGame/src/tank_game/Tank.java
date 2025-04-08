package tank_game;

import java.util.Vector;

/**
 * @author Akai
 * @version 1.0
 */
public class Tank {
    private int x; //坦克的横坐标
    private int y; //坦克的纵坐标
    private int direction;
    private int Type;
    private int speed = 10;
    private Vector<Bullet> bullets = new Vector<>();
    private boolean dead = false;

    //构造器
    public Tank(int x, int y, int direction, int type) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        Type = type;
    }

    //getter,setter
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Vector<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(Vector<Bullet> bullets) {
        this.bullets = bullets;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    //射击方法
    public void shoot() {
        Bullet bullet = null;
        if (direction == 0) {
            bullet = new Bullet(x + 20, y, direction, 10, true);
        } else if (direction == 1) {
            bullet = new Bullet(x + 20, y + 60, direction, 10, true);
        } else if (direction == 2) {
            bullet = new Bullet(x, y + 20, direction, 10, true);
        } else if (direction == 3) {
            bullet = new Bullet(x + 60, y + 20, direction, 10, true);
        }
        if (bullet != null) {
            bullets.add(bullet);
            new Shoot(bullet).start();
        }
    }

    //向前移动
    public void moveForward() {
        switch (direction) {
            case 0:
                if (y >= speed) {
                    y -= speed;
                }
                break;
            case 1:
                if (y + 60 <= 750 - speed) {
                    y += speed;
                }
                break;
            case 2:
                if (x >= speed) {
                    x -= speed;
                }
                break;
            case 3:
                if (x + 60 <= 1000 - speed) {
                    x += speed;
                }
                break;
            default:
                break;
        }
    }

    //判断是否将与其他坦克相撞
    public boolean isTouching(Vector<EnemyTank> enemies, MyTank myTank) {
        switch (direction) {
            case 0://上
                //和坦克集比较
                for (EnemyTank enemyTank : enemies) {
                    if (enemyTank!=this){
                        if(enemyTank.getDirection() == 0||enemyTank.getDirection() == 1){//上下
                            if(this.x>enemyTank.getX()-40&&this.x<enemyTank.getX()+40&&this.y>enemyTank.getY()&&this.y<enemyTank.getY()+60+speed)
                                return true;
                        }else if(enemyTank.getDirection() == 2||enemyTank.getDirection() == 3){//左右
                            if(this.x>enemyTank.getX()-40&&this.x<enemyTank.getX()+60&&this.y>enemyTank.getY()&&this.y<enemyTank.getY()+40+speed)
                                return true;
                        }
                    }
                }
                //和myTank比较
                if(myTank != this){
                    if(myTank.getDirection() == 0||myTank.getDirection() == 1){//上下
                        if(this.x>myTank.getX()-40&&this.x<myTank.getX()+40&&this.y>myTank.getY()&&this.y<myTank.getY()+60+speed)
                            return true;
                    }else if(myTank.getDirection() == 2||myTank.getDirection() == 3){//左右
                        if(this.x>myTank.getX()-40&&this.x<myTank.getX()+60&&this.y>myTank.getY()&&this.y<myTank.getY()+40+speed)
                            return true;
                    }
                }
                return false;
            case 1://下
                //和坦克集比较
                for (EnemyTank enemyTank : enemies) {
                    if (enemyTank!=this){
                        if(enemyTank.getDirection() == 0||enemyTank.getDirection() == 1){//上下
                            if(this.x>enemyTank.getX()-40&&this.x<enemyTank.getX()+40&&this.y>enemyTank.getY()-speed-60&&this.y<enemyTank.getY())
                                return true;
                        }else if(enemyTank.getDirection() == 2||enemyTank.getDirection() == 3){//左右
                            if(this.x>enemyTank.getX()-40&&this.x<enemyTank.getX()+60&&this.y>enemyTank.getY()-speed-60&&this.y<enemyTank.getY()-20)
                                return true;
                        }
                    }
                }
                //和myTank比较
                if(myTank != this){
                    if(myTank.getDirection() == 0||myTank.getDirection() == 1){//上下
                        if(this.x>myTank.getX()-40&&this.x<myTank.getX()+40&&this.y>myTank.getY()-speed-60&&this.y<myTank.getY())
                            return true;
                    }else if(myTank.getDirection() == 2||myTank.getDirection() == 3){//左右
                        if(this.x>myTank.getX()-40&&this.x<myTank.getX()+60&&this.y>myTank.getY()-speed-60&&this.y<myTank.getY()-20)
                            return true;
                    }
                }
                return false;
            case 2://左
                //和坦克集比较
                for (EnemyTank enemyTank : enemies) {
                    if (enemyTank!=this){
                        if(enemyTank.getDirection() == 0||enemyTank.getDirection() == 1){//上下
                            if(this.x>enemyTank.getX()&&this.x<enemyTank.getX()+40+speed&&this.y>enemyTank.getY()-40&&this.y<enemyTank.getY()+60)
                                return true;
                        }else if(enemyTank.getDirection() == 2||enemyTank.getDirection() == 3){//左右
                            if(this.x>enemyTank.getX()&&this.x<enemyTank.getX()+60+speed&&this.y>enemyTank.getY()-40&&this.y<enemyTank.getY()+40)
                                return true;
                        }
                    }
                }
                //和myTank比较
                if(myTank != this){
                    if(myTank.getDirection() == 0||myTank.getDirection() == 1){//上下
                        if(this.x>myTank.getX()&&this.x<myTank.getX()+40+speed&&this.y>myTank.getY()-40&&this.y<myTank.getY()+60)
                            return true;
                    }else if(myTank.getDirection() == 2||myTank.getDirection() == 3){//左右
                        if(this.x>myTank.getX()&&this.x<myTank.getX()+60+speed&&this.y>myTank.getY()-40&&this.y<myTank.getY()+40)
                            return true;
                    }
                }
                return false;
            case 3://右
                //和坦克集比较
                for (EnemyTank enemyTank : enemies) {
                    if (enemyTank!=this){
                        if(enemyTank.getDirection() == 0||enemyTank.getDirection() == 1){//上下
                            if(this.x>enemyTank.getX()-speed-60&&this.x<enemyTank.getX()-20&&this.y>enemyTank.getY()-40&&this.y<enemyTank.getY()+60)
                                return true;
                        }else if(enemyTank.getDirection() == 2||enemyTank.getDirection() == 3){//左右
                            if(this.x>enemyTank.getX()-speed-60&&this.x<enemyTank.getX()&&this.y>enemyTank.getY()-40&&this.y<enemyTank.getY()+40)
                                return true;
                        }
                    }
                }
                //和myTank比较
                if(myTank != this){
                    if(myTank.getDirection() == 0||myTank.getDirection() == 1){//上下
                        if(this.x>myTank.getX()-speed-60&&this.x<myTank.getX()-20&&this.y>myTank.getY()-40&&this.y<myTank.getY()+60)
                            return true;
                    }else if(myTank.getDirection() == 2||myTank.getDirection() == 3){//左右
                        if(this.x>myTank.getX()-speed-60&&this.x<myTank.getX()&&this.y>myTank.getY()-40&&this.y<myTank.getY()+40)
                            return true;
                    }
                }
                return false;
            default:
                return false;
        }
    }
}
