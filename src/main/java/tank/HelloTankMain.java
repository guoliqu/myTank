package tank;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class HelloTankMain {

    public static void main(String[] args) throws InterruptedException{
        // 创建战场
        TankFrame tankFrame = new TankFrame();

        // 创建敌方坦克
        int initEnemyTankNum = Integer.parseInt(Objects.requireNonNull(PropertyMgr.get("initTankCount")).toString());
        for(int i = 0; i < initEnemyTankNum; i ++){
            tankFrame.tanks.add(new Tank(50 + i*80, 200,true,  Dir.DOWN, tankFrame, Group.BAD));
        }

        new Thread(()->new Audio("audio/war1.wav").loop()).start();

        // 刷新地图数据
        while (true){
            TimeUnit.MILLISECONDS.sleep(25);
            tankFrame.repaint();
        }
    }

}
