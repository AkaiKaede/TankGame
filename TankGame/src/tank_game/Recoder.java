package tank_game;

import java.io.*;
import java.util.Vector;

/**
 * @author Akai
 * @version 1.0
 */
public class Recoder {
    private static int killedTanks = 0;
    private static Vector<EnemyTank> enemyTanks;
    private static MyTank myTank;
    //    private static FileWriter fw;
    private static BufferedWriter bw;
    private static BufferedReader br;
    private static String recoderFile = "myRecoder.txt";
    //Node向量
    private static Vector<Node> nodes = new Vector<>();

    //获取node对象给enemyTanks对象
    public static void getNodesFromFile() throws IOException {
        br = new BufferedReader(new FileReader(recoderFile));
        killedTanks = Integer.parseInt(br.readLine());
        String line;
        while ((line = br.readLine()) != null) {
            String[] tokens = line.split(" ");
            nodes.add(new Node(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2])));
        }
    }

    //保存方法
    public static void save() throws IOException {
        bw = new BufferedWriter(new FileWriter(recoderFile));
        bw.write(killedTanks + "\n");
        for (EnemyTank enemyTank : enemyTanks) {
            bw.write(enemyTank.getX() + " " + enemyTank.getY() + " " + enemyTank.getDirection() + "\n");
        }
        if (bw != null) {
            bw.close();
        }
    }

    public static Vector<Node> getNodes() {
        return nodes;
    }

    public static void setNodes(Vector<Node> nodes) {
        Recoder.nodes = nodes;
    }

    public static Vector<EnemyTank> getEnemyTanks() {
        return enemyTanks;
    }

    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recoder.enemyTanks = enemyTanks;
    }

    public static int getKilledTanks() {
        return killedTanks;
    }

    public static void setKilledTanks(int killedTanks) {
        Recoder.killedTanks = killedTanks;
    }

    public static void addKilledTanks() {
        Recoder.killedTanks++;
    }
}
