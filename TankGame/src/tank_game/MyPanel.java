package tank_game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

/**
 * @author Akai
 * @version 1.0
 * 我们坦克大战的绘图区域
 */
public class MyPanel extends JPanel implements KeyListener, Runnable {
    //定义我的坦克
    MyTank myTank = null;
    //敌人坦克属性
    Vector<EnemyTank> enemyTanks = new Vector<>();
    //定义炸弹集合
    Vector<Bomb> bombs = new Vector<>();
    //Node向量
    private static Vector<Node> nodes = new Vector<>();
    int enemyTanksSize = 3;

    //定义三张炸弹图片
    Image image1;
    Image image2;
    Image image3;

    public MyPanel(String key) {
        //将敌人坦克对象设置给Recoder类的属性
        Recoder.setEnemyTanks(enemyTanks);
        myTank = new MyTank(600, 100, 0);
        switch (key) {
            case "1":
                //初始化敌人坦克
                for (int i = 0; i < enemyTanksSize; i++) {
                    EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 0, 1);
                    enemyTank.setEnemyTanks(enemyTanks);
                    enemyTank.setMyTank(myTank);
                    enemyTank.autoShoot();
                    enemyTank.autoMove();
                    enemyTanks.add(enemyTank);
                }
                break;
            case "2":
                try {
                    Recoder.getNodesFromFile();
                    nodes = Recoder.getNodes();
                    for(Node node : nodes) {
                        EnemyTank enemyTank = new EnemyTank(node.getX(), node.getY(),node.getDirection());
                        enemyTank.setEnemyTanks(enemyTanks);
                        enemyTank.setMyTank(myTank);
                        enemyTank.autoShoot();
                        enemyTank.autoMove();
                        enemyTanks.add(enemyTank);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                System.exit(0);
        }
        //初始化图片对象
        image1 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/bomb1.png"));
        image2 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/bomb2.png"));
        image3 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/bomb3.png"));

    }

    //paint方法
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);
        //画出坦克-封装方法
        if (!myTank.isDead()) {
            drawTank(myTank.getX(), myTank.getY(), g, myTank.getDirection(), myTank.getType());
            //画我方坦克的子弹
            drawBullet(myTank.getBullets(), g);
        } else {
            System.out.println("Game Over");
            System.exit(0);
        }
        Iterator<EnemyTank> enemyTankIterator = enemyTanks.iterator();
        while (enemyTankIterator.hasNext()) {
            EnemyTank enemyTank = enemyTankIterator.next();
            if (!enemyTank.isDead()) {
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirection(), enemyTank.getType());
                drawBullet(enemyTank.getBullets(), g);
            } else if (enemyTank.isDead()) {
                enemyTankIterator.remove();
                Recoder.addKilledTanks();
            }
        }
        showInfo(g);
        Iterator<Bomb> bombIterator = bombs.iterator();
        while (bombIterator.hasNext()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Bomb bomb = bombIterator.next();
            if (bomb.health > 6) {
                g.drawImage(image1, bomb.x, bomb.y, 60, 60, this);
            } else if (bomb.health > 3) {
                g.drawImage(image2, bomb.x, bomb.y, 60, 60, this);
            } else {
                g.drawImage(image3, bomb.x, bomb.y, 60, 60, this);
            }
            bomb.healthDown();
            if (!bomb.isalive) {
                bombIterator.remove();
            }
        }
    }

    public MyTank getMyTank() {
        return myTank;
    }

    public void setMyTank(MyTank myTank) {
        this.myTank = myTank;
    }

    //子弹绘画方法
    public void drawBullet(Vector<Bullet> bullets, Graphics g) {
        Iterator<Bullet> iterator = bullets.iterator();
        while (iterator.hasNext()) {
            Bullet bullet = iterator.next();
            if (bullet.isAlive == false) {//子弹死亡
                iterator.remove();
            } else if (bullet.isAlive == true) {//子弹存活
                g.draw3DRect(bullet.x, bullet.y, 3, 3, false);
            }
        }
    }

    //坦克绘画方法

    /**
     * @param x         坦克左上角x坐标
     * @param y         坦克左上角y坐标
     * @param g         画笔
     * @param direction 坦克方向(上下左右)
     * @param type      坦克类型
     */
    public void drawTank(int x, int y, Graphics g, int direction, int type) {
        switch (type) {//根据类型设置颜色
            case 0://我们的坦克
                g.setColor(Color.cyan);
                break;
            case 1://敌方的坦克
                g.setColor(Color.yellow);
                break;
        }

        switch (direction) {
            case 0://朝上
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y, x + 20, y + 30);
                break;
            case 1://朝下
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 30, x + 20, y + 60);
                break;
            case 2://朝左
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x, y + 20, x + 30, y + 20);
                break;
            case 3://朝右
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x + 60, y + 20);
                break;
            default:
                System.out.println("暂时没有处理");
        }
    }

    //画坦克信息
    public void showInfo(Graphics g) {
        g.setColor(Color.BLACK);
        Font font = new Font("宋体", Font.BOLD, 25);
        g.setFont(font);
        g.drawString("累计击毁敌方坦克", 1020, 30);
        drawTank(1020, 60, g, 0, 0);
        g.setColor(Color.BLACK);
        g.drawString(Recoder.getKilledTanks() + "", 1080, 100);
    }

    //检查我方坦克子弹是否击中坦克方法
    public void myIsShoot(Vector<Bullet> bullets, Vector<EnemyTank> enemyTanks) {
        for (Bullet bullet : bullets) {
            if (bullet.isAlive == true) {
                for (EnemyTank enemyTank : enemyTanks) {
                    switch (enemyTank.getDirection()) {
                        case 0:
                        case 1:
                            if (bullet.x >= enemyTank.getX() && bullet.x <= enemyTank.getX() + 40 && bullet.y >= enemyTank.getY() && bullet.y <= enemyTank.getY() + 60) {
                                bullet.isAlive = false;
                                for (Bullet bullet2 : enemyTank.getBullets()) {
                                    bullet2.isAlive = false;
                                }
                                enemyTank.setDead(true);
                                //创建Bomb对象,加入到bombs集合
                                Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                                bombs.add(bomb);
                            }
                            break;
                        case 2:
                        case 3:
                            if (bullet.x >= enemyTank.getX() && bullet.x <= enemyTank.getX() + 60 && bullet.y >= enemyTank.getY() && bullet.y <= enemyTank.getY() + 40) {
                                bullet.isAlive = false;
                                for (Bullet bullet2 : enemyTank.getBullets()) {
                                    bullet2.isAlive = false;
                                }
                                enemyTank.setDead(true);
                                Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                                bombs.add(bomb);
                            }
                    }
                }
            }
        }
    }

    //检查我方坦克子弹是否击中坦克方法
    public void enemyIsShoot(Vector<Bullet> bullets, MyTank enemyTank) {
        for (Bullet bullet : bullets) {
            if (bullet.isAlive == true) {
                switch (enemyTank.getDirection()) {
                    case 0:
                    case 1:
                        if (bullet.x >= enemyTank.getX() && bullet.x <= enemyTank.getX() + 40 && bullet.y >= enemyTank.getY() && bullet.y <= enemyTank.getY() + 60) {
                            bullet.isAlive = false;
                            for (Bullet bullet2 : enemyTank.getBullets()) {
                                bullet2.isAlive = false;
                            }
                            enemyTank.setDead(true);
                            //创建Bomb对象,加入到bombs集合
                            Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                            bombs.add(bomb);
                        }
                        break;
                    case 2:
                    case 3:
                        if (bullet.x >= enemyTank.getX() && bullet.x <= enemyTank.getX() + 60 && bullet.y >= enemyTank.getY() && bullet.y <= enemyTank.getY() + 40) {
                            bullet.isAlive = false;
                            for (Bullet bullet2 : enemyTank.getBullets()) {
                                bullet2.isAlive = false;
                            }
                            enemyTank.setDead(true);
                            Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                            bombs.add(bomb);
                        }

                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                myTank.setDirection(0);
                if (myTank.getY() >= myTank.getSpeed() && !myTank.isTouching(enemyTanks, myTank)) {
                    myTank.setY(myTank.getY() - myTank.getSpeed());
                }
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                myTank.setDirection(1);
                if (myTank.getY() + 60 <= 750 - myTank.getSpeed() && !myTank.isTouching(enemyTanks, myTank)) {
                    myTank.setY(myTank.getY() + myTank.getSpeed());
                }
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                myTank.setDirection(2);
                if (myTank.getX() >= myTank.getSpeed() && !myTank.isTouching(enemyTanks, myTank)) {
                    myTank.setX(myTank.getX() - myTank.getSpeed());
                }
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                myTank.setDirection(3);
                if (myTank.getX() + 60 <= 1000 - myTank.getSpeed() && !myTank.isTouching(enemyTanks, myTank)) {
                    myTank.setX(myTank.getX() + myTank.getSpeed());
                }
                break;
            case KeyEvent.VK_J:
                myTank.shoot();
            default:
                break;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            myIsShoot(myTank.getBullets(), enemyTanks);
            for (EnemyTank enemyTank : enemyTanks) {
                enemyIsShoot(enemyTank.getBullets(), myTank);
            }
            repaint();
        }
    }
}
