package tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {
    /**
     * 场景大小
     */
    static final int GAME_WIDTH = 1000, GAME_HEIGHT = 960;
    Image offScreenImage = null;
    /**
     * 子弹集合
     */
    public List<Bullet> bullets = new ArrayList<>();
    /**
     * 敌方坦克集合
     */
    List<Tank> tanks = new ArrayList<>();
    /**
     * 爆炸集合
     */
    List<Explode> explodes = new ArrayList<>();
    /**
     * 主坦克/我的坦克
     */
    Tank myTank = new Tank(200, 400, false, Dir.UP, this, Group.GOOD);


    /**
     * frame初始化
     */
    public TankFrame() {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setTitle("tar war");
        setVisible(true);

        this.addKeyListener(new MyKeyListener());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    /**
     * paint方法前会调用这个方法，用于解决图片闪烁
     * 通过把图片写入内存，再从内存写入frame
     * @param g
     */
    @Override
    public void update(Graphics g) {
        if(offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color color = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(color);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    /**
     * 画出场景内容
     * @param graphics
     */
    @Override
    public void paint(Graphics graphics) {
        Color c = graphics.getColor();
        graphics.setColor(Color.WHITE);
        graphics.drawString("子弹的数量:" + bullets.size(), 10, 60);
        graphics.drawString("敌人的数量:" + tanks.size(), 10, 80);
        graphics.drawString("爆炸的数量:" + explodes.size(), 10, 100);
        graphics.setColor(c);

        myTank.paint(graphics);

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(graphics);
        }

        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(graphics);
        }

        for(int i=0; i<bullets.size(); i++) {
            for(int j = 0; j<tanks.size(); j++)
                bullets.get(i).collideWith(tanks.get(j));
        }

        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(graphics);
        }
    }

    /**
     * 键盘监听
     */
    class MyKeyListener extends KeyAdapter{
        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
           int keyCode = e.getKeyCode();
           switch (keyCode){
               case KeyEvent.VK_LEFT:
                   bL = true;
                   break;
               case KeyEvent.VK_UP:
                   bU = true;
                   break;
               case KeyEvent.VK_RIGHT:
                   bR = true;
                   break;
               case KeyEvent.VK_DOWN:
                   bD = true;
                   break;
               default:
                   break;
           }
           // 设置主坦克方向
           setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode){
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                default:
                    break;
            }
            // 设置主坦克方向
            setMainTankDir();
        }

        /**
         *  设置主坦克方向
         */
        private void setMainTankDir(){
            if(!bL && !bU && !bR && !bD) {
                myTank.setMoving(false);
            }else {
                myTank.setMoving(true);
                if (bL){
                    myTank.setDir(Dir.LEFT);
                }
                if (bU){
                    myTank.setDir(Dir.UP);
                }
                if (bR){
                    myTank.setDir(Dir.RIGHT);
                }
                if (bD){
                    myTank.setDir(Dir.DOWN);
                }
            }
        }
    }
}
