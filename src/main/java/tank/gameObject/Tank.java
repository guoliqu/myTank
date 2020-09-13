package tank.gameObject;

import tank.utils.PropertyMgr;
import tank.utils.ResourceMgr;
import tank.TankFrame;
import tank.fireStrategy.FireStrategy;
import tank.fireStrategy.imple.DefaultFireStrategy;
import tank.gameEnum.Dir;
import tank.gameEnum.Group;
import tank.gameModel.GameModel;

import java.awt.*;
import java.util.Random;

/**
 * 坦卡对象
 */
public class Tank {
    /**
     * 坦克宽度
     */
    public static int WIDTH = ResourceMgr.goodTankU.getWidth();
    /**
     * 坦卡长度
     */
    public static int HEIGHT = ResourceMgr.goodTankU.getHeight();
    /**
     * 坦卡速度
     */
    public static final int SPEED = 2;
    /**
     * 随机数
     */
    private Random random = new Random();
    /**
     * 坦克所在的矩形
     */
    Rectangle rect = new Rectangle();

    private FireStrategy fireStrategy;

    private int x;
    private int y;
    private boolean moving;
    private Dir dir;
    private GameModel gameModel;
    private Group group;
    private boolean living = true;

    public Tank(int x, int y, boolean moving, Dir dir, GameModel gameModel, Group group) {
        this.x = x;
        this.y = y;
        this.moving = moving;
        this.dir = dir;
        this.gameModel = gameModel;
        this.group = group;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        if(group == Group.GOOD) {
            String goodFSName = (String) PropertyMgr.get("goodFS");

            try {
                fireStrategy = (FireStrategy)Class.forName(goodFSName).getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            fireStrategy = new DefaultFireStrategy();
        }
    }

    public void paint(Graphics g) {
        if(!living) {
            gameModel.getTanks().remove(this);
        }
        switch(dir) {
            case LEFT:
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
                break;
        }

        tankMove();
    }

    private void tankMove() {
        if (this.group == Group.BAD){
            if(random.nextInt(100) > 96) this.fire();
            if(random.nextInt(1000) > 988) this.randomDir();
        }
        if(!moving) {
            return;
        }
        switch (dir){
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            default:
                break;
        }

        boundsCheck();

        //update rect
        rect.x = this.x;
        rect.y = this.y;

    }

    /**
     * 边界校验
     */
    private void boundsCheck(){
        if (this.x < 0){
            x = 0;
        }
        if (this.y < 30){
            y = 30;
        }
        if (this.x > (TankFrame.GAME_WIDTH - Tank.WIDTH - 2)){
            x = TankFrame.GAME_WIDTH - Tank.WIDTH -2;
        }
        if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT -2 ) {
            y = TankFrame.GAME_HEIGHT -Tank.HEIGHT -2;
        }

    }

    /**
     * 随机方向
     */
    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    /**
     * 发射子弹
     */
    public void fire(){
        fireStrategy.fire(this);
    }

    public void die() {
        this.living = false;
    }

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

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isLiving() {
        return living;
    }

    public void setLiving(boolean living) {
        this.living = living;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public GameModel getGameModel() {
        return gameModel;
    }

    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public FireStrategy getFireStrategy() {
        return fireStrategy;
    }

    public void setFireStrategy(FireStrategy fireStrategy) {
        this.fireStrategy = fireStrategy;
    }
}
