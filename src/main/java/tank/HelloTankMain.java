package tank;

import tank.utils.Audio;

import java.util.concurrent.TimeUnit;

public class HelloTankMain {

    public static void main(String[] args) throws InterruptedException{
        // 创建战场
        TankFrame tankFrame = new TankFrame();

        new Thread(()->new Audio("audio/war1.wav").loop()).start();

        // 刷新地图数据
        while (true){
            TimeUnit.MILLISECONDS.sleep(25);
            tankFrame.repaint();
        }
    }

}
