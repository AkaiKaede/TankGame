package tank_game;

/**
 * @author Akai
 * @version 1.0
 */
public class Bomb {
    int x, y;    //炸弹的坐标
    int health = 9;   //炸弹的生命周期
    boolean isalive = true;//是否还存活

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void healthDown(){
        if(health > 0){
            health--;
        }else {
            isalive = false;
        }
    }
}
