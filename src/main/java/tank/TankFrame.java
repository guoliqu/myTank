package tank;

import tank.gameEnum.Dir;
import tank.gameModel.GameModel;
import tank.gameObject.Tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {
    /**
     * 场景大小
     */
    public static final int GAME_WIDTH = 1000, GAME_HEIGHT = 960;
    /**
     * 背景图片
     */
    Image offScreenImage = null;

    public GameModel gameModel = new GameModel();


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
        gameModel.paint(graphics);
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
                    gameModel.getMyTank().fire();
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
            Tank myTank = gameModel.getMyTank();
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
