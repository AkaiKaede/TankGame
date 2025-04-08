package tank_game;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Akai
 * @version 1.0
 */
public class TankGame extends JFrame {
    MyPanel myPanel;
    public static void main(String[] args) {
        new TankGame();
    }

    public TankGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入选择 1:新游戏 2:继续上局");
        String key = scanner.nextLine();
        myPanel = new MyPanel(key);
        this.add(myPanel);
        addKeyListener(myPanel);
        new Thread(myPanel).start();
        setTitle("Tank Game");
        setSize(1250, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        //增加关闭窗口监听器
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                    Recoder.save();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                System.exit(0);
            }
        });
    }
}
